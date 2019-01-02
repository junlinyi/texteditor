package modules;
import util.Trie;

/**
 * Class that implements AutoCompleteModule using a Trie.
 * @author jamal.hashim, junlin.yi
 *
 */
public class AutoCompleter implements AutoCompleteModule {
   
	Trie trie = new Trie();
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void addWord(String word) {
		// TODO Auto-generated method stub
		
		trie.insert(word);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getWordForPrefix(String prefix) {
		// TODO Auto-generated method stub
		return trie.closestWordToPrefix(prefix);
	}

}
