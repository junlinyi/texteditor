package test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Random;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import modules.Searcher;
import util.BloomFilter;

public class SearchTest {
   
   private Searcher s;
   Random rand = new Random();
   private String[] strings = {"To", "be", "or", "not", "to", "be", "that", "is", "the",
            "question", "whether", "tis", "nobler", "in", "the", "mind", "to", "suffer",
            "the", "slings", "and", "arrows", "of", "outrageous", "fortune", "or", "to",
            "take", "arms", "against", "a", "sea", "of", "troubles"};
   private String fullString = "Tobeornottobethatisthequestionwhethertisnoblerinthe"
            + "mindtosuffertheslingsandarrowsofoutrageousfortuneortotakearmsagainsta"
            + "seaoftroubles";
   private String[] ints = {"3", "1", "4", "1", "5", "9", "2", "6", "5"};
   private String intString = "314159265";
   
   @BeforeEach
   public void setUp() {
      s = new Searcher();
   }
   
   @Test
   public void RandomlyGenerateStringsAndCheckIfCanFind() {
      int index = rand.nextInt(strings.length);
      System.out.println("strings[index]: " + strings[index]);
      System.out.println("Value returned: " + s.find(strings[index], fullString));
      assertNotEquals(-1, s.find(strings[index], fullString));
      index = rand.nextInt(strings.length);;
      assertNotEquals(-1, s.find(strings[index], fullString));
      index = rand.nextInt(strings.length);;
      assertNotEquals(-1, s.find(strings[index], fullString));
      
      index = rand.nextInt(ints.length);
      assertNotEquals(-1, s.find(ints[index], intString));
      index = rand.nextInt(ints.length);
      assertNotEquals(-1, s.find(ints[index], intString));
      index = rand.nextInt(ints.length);;
      assertNotEquals(-1, s.find(ints[index], intString));
   }
   
   @Test
   public void RandomlyGenerateStringsAndCheckNotFound() {
      int index = rand.nextInt(strings.length);
      assertEquals(-1, s.find(strings[index], intString));
      
      index = rand.nextInt(ints.length);;
      assertEquals(-1, s.find(ints[index], fullString));
   }
}
