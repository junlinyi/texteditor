package test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.HashMap;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import util.HashTable;

public class HashTableTest {
   
   private HashTable h;
   private int initSize = 10;
   
   @BeforeEach
   public void setUp() {
      h = new HashTable(initSize);
   }
   
   public void CheckInitialSizeMatches() {
      assertEquals(initSize, h.size());
   }
   
   public void CheckMethodisEmptyIsTrueInitially() {
      assertTrue(h.isEmpty());
   }
   
   @Test
   public void InsertKeyValuePairCheckThatContainsBoth() {
      h.put(3, "hello");
      assertTrue(h.containsKey(3));
      assertTrue(h.containsValue("hello"));
   }
   
   @Test
   public void InsertKeyValuePairAndDeleteIt() {
      h.put('c', "c");
      assertTrue(h.containsKey('c'));
      h.remove('c');
      assertFalse(h.containsKey('c'));
      assertFalse(h.containsValue("c"));
   }
   
   @Test
   public void CopyMapIntoTableAndCheckElementsMatch() {
      HashMap<Integer, String> hm = new HashMap<Integer, String>();
      hm.put(1, "first");
      hm.put(2, "second");
      hm.put(3, "third");
      h.putAll(hm);
      assertTrue(h.containsKey(1));
      assertTrue(h.containsKey(2));
      assertTrue(h.containsKey(3));
      assertTrue(h.containsValue("first"));
      assertTrue(h.containsValue("second"));
      assertTrue(h.containsValue("third"));
      
   }
      
}
