package test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Collection;
import java.util.HashSet;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import util.BloomFilter;

public class BloomFilterTest {
   
   private BloomFilter b;
   private Collection<String> collection = new HashSet<String>();
   private Collection<Integer> collection2 = new HashSet<Integer>();
   
   private int initNumBytes = 15;
   private int initNumHashFunctions = 3;
   
   
   @BeforeEach
   public void setUp() {
      b = new BloomFilter(collection, initNumBytes, initNumHashFunctions);
   }
   
   @Test
   public void InsertTwoStringsCheckThatContainsBoth() {
      b.insert("hello");
      assertTrue(b.mightContain("hello"));
      b.insert("world");
      assertTrue(b.mightContain("world"));
   }
   
   @Test
   public void InsertTwoIntegersCheckThatContainsBoth() {
      b = new BloomFilter(collection2, initNumBytes, initNumHashFunctions);
      b.insert(4);
      assertTrue(b.mightContain(4));
      b.insert(6);
      assertTrue(b.mightContain(6));
   }
}
