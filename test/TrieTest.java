package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import util.Trie;

public class TrieTest {
   
   private Trie t;
   private String s1 = "Hello";
   private String s2 = "World";
   private String s3 = "";
   
   @BeforeEach
   public void setUp() {
      t = new Trie();
   }
   
   @Test
   public void InsertEmptyStringCheckThatContains() {
      t.insert(s3);
      assertTrue(t.contains(s3));
   }
   
   @Test
   public void InsertTwoDistinctWordsIntoTrieCheckThatContainsBoth() {
      t.insert(s1);
      t.insert(s2);
      assertTrue(t.contains(s1));
      assertTrue(t.contains(s2));
   }
   
   @Test
   public void InsertWordIntoTrieGetWord() {
      t.insert(s1);
      assertEquals(s1, t.closestWordToPrefix(s1));
   }
   
   @Test
   public void InsertWordAndDeleteIt() {
      t.insert(s1);
      assertTrue(t.contains(s1));
      t.delete(s1);
      assertFalse(t.contains(s1));
   }
   
   @Test
   public void InsertWordNotInTrieAndGetClosestPrefix() {
      assertEquals(s1, t.closestWordToPrefix("hell"));
   }
}
