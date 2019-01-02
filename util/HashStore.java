package util;

/**
 * Data structure containing key-value pairs. To be used in the HashTable implementation.
 * @author jamal.hashim, junlin.yi
 *
 * @param <K> Generic parameter containing the key.
 * @param <V> Generic parameter containing the value to be stored.
 */
public class HashStore <K, V>{
   
	private K key;
	private V val;
	
	/**
	 * To create a HashStore object with the specified key and value.
	 */
	public HashStore(K key, V val) {
		this.key=key;
		this.val=val;
	}
	
	/**
	 * Returns the key.
	 */
	public K getKey() {
		return key;
	}
	
	/**
	 * Returns the stored value.
	 */
	public V getVal() {
		return val;
	}
}
