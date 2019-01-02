package util;

/**
 * A data structure containing a mutable collection of strings
 * stored as string prefixes.
 * @author jamal.hashim, junlin.yi
 */
public class Trie {
   
	TrieNode root;

	/** Create an empty trie. */
	public Trie() {
		root = new TrieNode(false);
	}

	/** Add {@code elem} to the collection. */
	public void insert(String elem) {
		if(elem == null) {
			return;
		}
		if(elem.equals("")) {
			root.setContained(true);
			return;
		}
		boolean skip = false;
		TrieNode pointer = root;
		for (int i = 0; i < elem.length(); i++) {
			//System.out.println(elem);
			try {pointer.getChild(elem.charAt(i));}catch (Exception e) {System.out.println(elem);skip=true;continue;}
			TrieNode next = pointer.getChild(elem.charAt(i));
			if (next == null) {
				pointer.addChild(elem.charAt(i), false);
				next = pointer.getChild(elem.charAt(i));
			}
			pointer = next;
		}
		
		if(pointer==null) {
			System.out.println("\n\n\n"+elem+"\n\n\n");
			return;
		}
		pointer.setContained(true);
		//}
	}

	/** Remove {@code elem} from the collection, if it is there. */
	public void delete(String elem) {
		// TODO implement
		TrieNode pointer = root;
		for (int i = 0; i < elem.length(); i++) {
			TrieNode next = pointer.getChild(elem.charAt(i));
			if (next == null) {
				return;
			}
			pointer = next;
		}
		pointer.setContained(false);
	}

	/** Return true if this trie contains {@code elem}, false otherwise. */
	public boolean contains(String elem) {
		// TODO implement
		TrieNode pointer = root;
		for (int i = 0; i < elem.length(); i++) {
			TrieNode next = pointer.getChild(elem.charAt(i));
			if (next == null) {
				return false;
			}
			pointer = next;
		}
		return pointer.getContained();
	}
	
	/**
	 * Retrieves the TrieNode containing {@code_elem}.
	 * @param elem The string to search the Trie for and retrieve.
	 * @return Returns the TrieNode containing {@code_elem}
	 */
	private TrieNode get(String elem) {
		// TODO implement
		TrieNode pointer = root;
		for (int i = 0; i < elem.length(); i++) {
			TrieNode next = pointer.getChild(elem.charAt(i));
			if (next == null) {
				return null;
			}
			pointer = next;
		}
		return pointer;
	}

	/**
	 * Return a word contained in the trie of minimal length with {@code prefix}. If
	 * no such word exists, return null.
	 */
	public String closestWordToPrefix(String prefix) {
		// TODO implement
		TrieNode start = get(prefix);
		if (start == null) {
			return null;
		}
		if (start.getContained() == true) {
			return prefix;
		}

		return findShort(start,prefix);
	}
	
	/**
	 * Retrieves the shortest String containing value prefix from the Trie.
	 * @param start The TrieNode to start searching from.
	 * @param prefix The String value containing what to search the Trie for.
	 * @return Returns the shortest String containing value prefix from the Trie.
	 */
	private String findShort(TrieNode start, String prefix) {
		HashTable<String, TrieNode> current = new HashTable<String, TrieNode>(25);
		current.put(prefix, start);
		
		while (true) {
			if(current.isEmpty()) {
				return null;
			}
			HashStore<String, TrieNode>[] values = current.getHashStores();
			
			for (int i = 0; i < values.length; i++) {
				if(values[i].getVal().getContained()) {
					return values[i].getKey();
				}
			}
			
			
			HashTable<String, TrieNode> newTable = new HashTable<String, TrieNode>(25);
			
			for(int i = 0;i<values.length;i++) {
				HashTable<Character, TrieNode> children = values[i].getVal().children;
				Object[] keys = children.keySet().toArray();
				for(int j = 0;j<keys.length;j++) {
					newTable.put(values[i].getKey()+keys[j], children.get(keys[j]));
				}
			}
			current = newTable;
		}
	}

}
