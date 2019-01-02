package util;

import java.util.Collection;
import java.util.Iterator;
import java.util.Set;

/**
 * An implementation of the Set interface to retrieve/store the keys from HashTable.
 * @author jamal.hashim, junlin.yi
 * @param <K> Generic parameter containing the key.
 */
public class MyHashSet<K> implements Set<K>{
	
	Object[] keys;
	
	/**
	 * For creating a HashSet containing all the keys in the specified HashTable.
	 * @param table The HashTable to retrieve the keys from.
	 */
	public MyHashSet(HashTable<K, ?> table) {
		keys = new Object[table.size()];
		Object[][] bucket = table.getBucket();
		int counter = 0;
		for(int i = 0;i<bucket.length;i++) {
			  for(int j = 0;j<bucket[i].length;j++) {
				  if(bucket[i][j]==null) {
					  continue;
				  }
				  keys[counter] = ((HashStore) bucket[i][j]).getKey();
				  counter++;
				  
			  }
		  }
	}
	
	/**
	 * Returns the number of keys.
	 */
	@Override
	public int size() {
		return keys.length;
	}
	
	/**
	 * Returns true if the keys array is empty (there are no keys stored).
	 */
	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return keys.length == 0;
	}
	
	/**
	 * Returns true if a key with value o exists.
	 * @param o The parameter to check the keys array for.
	 */
	@Override
	public boolean contains(Object o) {
		// TODO Auto-generated method stub
		for(int i = 0;i<keys.length;i++) {
			if(keys[i]==o) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Retrieves the keys array.
	 */
	@Override
	public Object[] toArray() {
		return keys;
	}

	@Override
	public Iterator<K> iterator() {
		// TODO Auto-generated method stub
		 throw new UnsupportedOperationException();
	}


	@Override
	public <T> T[] toArray(T[] a) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException();	}

	@Override
	public boolean add(K e) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException();	}

	@Override
	public boolean remove(Object o) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException();	}

	@Override
	public boolean containsAll(Collection<?> c) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException();	}

	@Override
	public boolean addAll(Collection<? extends K> c) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException();	}

	@Override
	public boolean retainAll(Collection<?> c) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException();	}

	@Override
	public boolean removeAll(Collection<?> c) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException();	}

	@Override
	public void clear() {
		// TODO Auto-generated method stub
		
	}

}
