package util;

import java.util.Collection;

/**
 * A collection of elements of type E for which the only operation is a
 * probabilistic membership test.
 */
public class BloomFilter<E> {

	boolean[] array;
	int hashNum = 0;
	
   /**
    * Create a new Bloom filter with {@code elems} inside. The bit array is of
    * length 8 * numBytes. The Bloom filter uses the specified number of hash
    * functions.
    * 
    * @param elems
    *           The collection of elements to be added to this filter
    * @param numBytes
    *           The length of the byte array representing bit array
    * @param numHashFunctions
    *           The number of hash functions to be used in this filter
    */
   public BloomFilter(Collection<E> elems, int numBytes, int numHashFunctions) {
	   array = new boolean[numBytes*8];
	   hashNum = numHashFunctions;
	   if(elems==null) {
		   return;
	   }
	   for(Object e: elems.toArray()) {
		   String string = e.toString();
		   for(int i = 0;i<numHashFunctions;i++) {
			   putHash(string, i);
		   }
		   
	   }
	
   }

   /**
    * Add {@code elem} to the Bloom filter.
    */
   public void insert(E elem) {
      // TODO implement
	   String string = elem.toString();
	   for(int i = 0;i<hashNum;i++) {
		   putHash(string, i);
	   }
   }
   private void putHash(String string, int hashNum) {
	   int hash = hash(string, hashNum);
	   array[hash] = true;
   }
   private int hash(String string, int hashNum) {
	   int hash = (string+((char)hashNum)).hashCode() % array.length;
	   if(hash<0) {
		   hash=-hash;
	   }
	   return hash;
   }
   /**
    * Check whether {@code elem} might be in the collection.
    */
   public boolean mightContain(E elem) {
      // TODO implement
	   String string = elem.toString();
	  for(int i = 0;i<hashNum;i++) {
		  if(!array[hash(string, i)]){
			 return false; 
		  }
	  }
      return true;
   }

}
