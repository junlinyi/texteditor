package util;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

/**
 * A data structure containing generic key-value pairs. Implemented 
 * with "chaining" buckets."
 * @author jamal.hashim, junlin.yi
 *
 * @param <K> Generic parameter containing the key.
 * @param <V> Generic parameter containing the value to be stored.
 */
public class HashTable<K, V> implements Map<K, V> {
   
	private Object[][] bucket;
	
   /**
    * Initialize a hash table with the specified number of buckets.
    * @param initNumBuckets
    */
   public HashTable(int initNumBuckets) {
	   bucket = new Object[initNumBuckets][1];
   }
   
   /**
    * Returns a count of the number of values stored in the structure.
    */
   @Override
   public int size() {
      // TODO Auto-generated method stub
	   int count = 0;
	   for(int i = 0;i<bucket.length;i++) {
			  for(int j = 0;j<bucket[i].length;j++) {
				  if(bucket[i][j]!=null) {
					  count++;
				  }
			  }
		  }
      return count;
   }
   
   /**
    * Checks if the structure is empty.
    */
   @Override
   public boolean isEmpty() {
      // TODO Auto-generated method stub
	  for(int i = 0;i<bucket.length;i++) {
		  for(int j = 0;j<bucket[i].length;j++) {
			  if(bucket[i][j]!=null) {
				  return false;
			  }
		  }
	  }
      return true;
   }
      
   /**
    * Checks if the specified key is contained in the table.
    * @param key The key to check for.
    * @return Returns true if the specified value is contained in the structure.
    */
   @Override
   public boolean containsKey(Object key) {
      // TODO Auto-generated method stub
	  if(get(key)==null) {
		  return false;
	  }
      return true;
   }
   
   /**
    * Checks if the specified value is contained in the table.
    * @param value The value to check for.
    * @return Returns true if the specified value is contained in the structure.
    */
   @Override
   public boolean containsValue(Object value) {
      // TODO Auto-generated method stub
	  for(int i = 0;i<bucket.length;i++) {
		  for(int j = 0;j<bucket[i].length;j++) {
			  if(bucket[i][j]==null) {
				  continue;
			  }
			  if(((HashStore) bucket[i][j]).getVal()==value) {
				  return true;
			  }
		  }
	  }
      return false;
   }
   
   /**
    * Gets the value stored at the specified key.
    */
   @Override
   public V get(Object key) {
      // TODO Auto-generated method stub
      int hash = hash(key);
      Object[] chain = bucket[hash];
      for(int i = 0;i<chain.length;i++) {
    	  if(chain[i]==null) {
    		  continue;
    	  }
    	  if(((HashStore) chain[i]).getKey()==key) {
    		  return (V) ((HashStore) chain[i]).getVal();
    	  }
      }
      return null;
      
   }
   
   /**
    * Puts the specified value at the specified key in the table.
    */
   @Override
   public V put(K key, V value) {
      // TODO Auto-generated method stub
	   
	  if(containsKey(key)) {
		  remove(key);
	  }
	  int hash = hash(key);
	  
	  for(int i = 0;i<bucket[hash].length;i++) {
		  if(bucket[hash][i]==null) {
			  bucket[hash][i]= new HashStore<K, V>(key, value);
			  return value;
		  }
	  }
	  
	  int newSize = bucket[hash].length*2;
	  Object[] newChain = new Object[newSize];
	  for(int i = 0;i<bucket[hash].length;i++) {
		  newChain[i] = bucket[hash][i];
	  }
	  newChain[bucket[hash].length] = new HashStore<K, V>(key, value);
	  bucket[hash] = newChain;
      return value;
   }
   
   /**
    * Removes the key-value pair from the structure.
    */
   @Override
   public V remove(Object key) {
      // TODO Auto-generated method stub
	  int hash = hash(key);
	  Object[] chain = bucket[hash];
      for(int i = 0;i<chain.length;i++) {
    	  if(chain[i]==null) {
    		  continue;
    	  }
    	  if(((HashStore) chain[i]).getKey()==key) {
    		  V returnVal = (V) ((HashStore) chain[i]).getVal();
    		  bucket[hash][i] = null;
    		  return returnVal;
    	  }
      }
      return null;
   }
   
   /**
    * Transfers all key-value pairs from the specified map to the structure.
    */
   @Override
   public void putAll(Map<? extends K, ? extends V> m) {
      // TODO Auto-generated method stub
	   
	   Object[] keys = m.keySet().toArray();
	   for(int i = 0;i<keys.length;i++) {
		   this.put((K) keys[i], m.get(keys[i]));
	   }
   }
   
   /**
    * Resets the structure to an empty one.
    */
   @Override
   public void clear() {
      // TODO Auto-generated method stub
	   bucket = new Object[bucket.length][1];
   }
   
   /**
    * Creates a Set containing the keys in the structure.
    * @return a Set containing the keys in the structure.
    */
   @Override
   public Set<K> keySet() {
      // TODO Auto-generated method stub
	   
	  MyHashSet<K> set = new MyHashSet<K>(this);
      return set;
   }
   
   /**
    * Hashes the object's location with a modular hash function.
    * @param key The key corresponding the the object to be hashed.
    * @return Returns the result of the hash function.
    */
   private int hash(Object key) {
	   int hash = (key.hashCode()/4)%bucket.length;
	   if(hash<0) {
		   return -hash;
	   }
	   return hash;
   }
   
   /**
    * Retrieves the bucket.
    */
   Object[][] getBucket(){
	   return bucket;
   }
   
   
   /**
    * Returns an array of HashStores which contain key-value pairs.
    */
   HashStore<K, V>[] getHashStores() {
	   HashStore[] vals = new HashStore[size()];
	   Object[] keys = this.keySet().toArray();
	   for(int i = 0;i<keys.length;i++) {
		   vals[i] = getHashStore(keys[i]);
	   }
	   return vals;
   }
   
   /**
    * Returns a HashStore with the specified key.
    */
   public HashStore<K, V> getHashStore(Object key) {
	      // TODO Auto-generated method stub
	      int hash = hash(key);
	      Object[] chain = bucket[hash];
	      for(int i = 0;i<chain.length;i++) {
	    	  if(chain[i]==null) {
	    		  continue;
	    	  }
	    	  if(((HashStore) chain[i]).getKey()==key) {
	    		  return ((HashStore) chain[i]);
	    	  }
	      }
	      return null;
	      
	   }
   
   @Override
   public Collection<V> values() {
      throw new UnsupportedOperationException();
   }

   @Override
   public Set<Map.Entry<K, V>> entrySet() {
      throw new UnsupportedOperationException();
   }

}
