package editor;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import modules.AutoCompleteModule;
import modules.ModuleFactory;
import modules.SearchModule;
import modules.SpellCheckModule;

/**
 * Does a lot of the "behind-the-scenes" work to set up Editor, such as
 * creating the modules or running text editor functions.
 * @author junlin.yi
 *
 */
public class EditorModel {

   private final AutoCompleteModule autoComplete;
   private final SpellCheckModule spellCheck;
   private final SearchModule search;
   private long spellCheckTime;

   /**
    * Initializes the auto-complete, spell-check, and search modules.
    */
   public EditorModel() {
      autoComplete = ModuleFactory.getAutoComplete();
      spellCheck = ModuleFactory.getSpellCheck();
      search = ModuleFactory.getSearchModule();
   }
   
   /**
    * Loads the dictionary to be used in Editor from a file.
    * @param dict The dictionary of words stored in a file.
    * @return Returns true if the file could be successfully read.
    */
   public boolean loadDictionary(File dict) {
      if (dict == null) return false;

      try (BufferedReader br = new BufferedReader(new FileReader(dict))) {
         for (String line; (line = br.readLine()) != null;) {
        	 
            autoComplete.addWord(line);
            spellCheck.addWord(line);
         }
         System.out.println(spellCheck.isValidWord("i"));
      } catch (IOException ioe) {
         System.err.println("Error reading provided dictionary file.");
         return false;
      }
      return true;
   }
   
   /**
    * Checks the spelling of the specified String.
    * @param markupText The String to be checked for spelling.
    * @return Returns the corrected String.
    */
   public String spellCheck(String markupText) {
      String plainText = toPlainText(markupText);
      String preamble = markupText.substring(0, firstIndexOfBody(markupText));
      String conclusion = markupText.substring(lastIndexOfBody(markupText) + 1);
      StringBuilder sb = new StringBuilder();
      sb.append(preamble);
      long start = System.nanoTime();
      for (String word : plainText.split("\\s+")) {
         if (!spellCheck.isValidWord(word.toLowerCase().replaceAll("[^\\w]", ""))) {
            sb.append("<u><font color=\"red\">");
            sb.append(word);
            sb.append("</font></u> ");
         } else {
            sb.append(word);
            sb.append(" ");
         }
      }
      spellCheckTime = (System.nanoTime() - start) / 1000;
      sb.append(conclusion);
      return sb.toString();
   }

   /**
    * Returns the amount of time spent on spell-checking.
    */
   public long getSpellCheckTime() {
      return spellCheckTime;
   }
   
   /**
    * Suggests auto-complete for the specified String by specifying the word closest
    * to the incomplete word.
    * @param markupText The String to be auto-completed.
    * @return The closest word to the specified String.
    */
   public String autocomplete(String markupText) {
      int startIndex = Math.max(Math.max(markupText.lastIndexOf(' ') + 1, firstIndexOfBody(markupText)),
               markupText.lastIndexOf("&nbsp;") + 6);
      String pref = toPlainText(markupText.substring(startIndex, lastIndexOfBody(markupText) + 1));
      return autoComplete.getWordForPrefix(pref);
   }
   
   /**
    * Returns the resulting String of a search.
    */
   public String search(String query, String markupText) {
      // clear formatting before we start
      markupText = clearFormatting(markupText);
      if (markupText.length() == 0 || query.length() == 0) {
         return markupText;
      }
      List<String> tags = new ArrayList<>();
      List<String> searchText = new ArrayList<>();
      StringBuilder curr = new StringBuilder();
      // we start with text, even though the first element is probably '<'
      int tagCount = 0;
      for (int i = 0; i < markupText.length(); i++) {
         char c = markupText.charAt(i);
         if (c == '<') {
            if (tagCount == 0) {
               searchText.add(curr.toString());
               // clear sb
               curr.setLength(0);
            }
            curr.append('<');
            tagCount++;
         } else if (c == '>') {
            curr.append('>');
            tagCount--;
            if (tagCount == 0) {
               tags.add(curr.toString());
               curr.setLength(0);
            }
         } else {
            curr.append(c);
         }
      }
      if (tagCount == 0) {
         searchText.add(curr.toString());
      } else {
         tags.add(curr.toString());
      }
      StringBuilder res = new StringBuilder();
      // keep track of whether we've added any text, to make sure we put a
      // space before the very first element of the body (other things rely on
      // that assumption)
      boolean addedText = false;
      // keep track of whether we've found and highlighted the first instance
      // of the query
      boolean foundQuery = false;
      for (int idx = 0; idx < searchText.size(); idx++) {
         String text = searchText.get(idx);
         if (!foundQuery) {
            // only search if we haven't yet found the query
            int searchIdx = search.find(query, text);
            if (searchIdx != -1) {
               foundQuery = true;
               res.append(text.substring(0, searchIdx));
               if (searchIdx == 0 && !addedText) {
                  res.append(' ');
               }
               res.append("<u><font color=\"blue\">");
               res.append(text.substring(searchIdx, searchIdx + query.length()));
               res.append("</font></u>");
               res.append(text.substring(searchIdx + query.length()));
            } else {
               // if we can't find the query in the text, just append
               res.append(text);
            }
         } else {
            // if we've already found the query, just append
            res.append(text);
         }
         if (text.length() > 0) {
            addedText = true;
         }
         if (idx < tags.size()) {
            res.append(tags.get(idx));
         }
      }
      return res.toString();
   }
   
   /**
    * Clears any formatting of a String.
    */
   public String clearFormatting(String markupText) {
      return markupText.replaceAll("<u><font color=\"(red|blue)\">", "").replaceAll("</font></u>", "");
   }
   
   /**
    * Converts to plain text formatting from the specified String.
    */
   private String toPlainText(String markupText) {
      return markupText.replaceAll("<p>", " ").replaceAll("<[^>]+>", "").replace("&nbsp;", "");
   }
   
   /**
    * Returns the index of the first appearance of {@code_markupText}.
    */
   private int firstIndexOfBody(String markupText) {
      int index = 0;
      int max = lastIndexOfBody(markupText);
      while (index < max - 1 && markupText.charAt(index) == '<') {
         index++;
         while (markupText.charAt(index) != '>')
            index++;
         index++;
      }
      return index;
   }
   
   /**
    * Returns the index of the last appearance of {@code_markupText}.
    * @param markupText
    * @return
    */
   private int lastIndexOfBody(String markupText) {
      return markupText.lastIndexOf("</body>") - 1;
   }

}
