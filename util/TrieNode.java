package util;

/**
 * Node that is used in Trie class. 
 * @author jamal.hashim, junlin.yi
 *
 */
public class TrieNode {
	HashTable<Character, TrieNode> children = new HashTable(26);
	private boolean contained = false;
	
	/**
	 * Constructor for TrieNode class.
	 * @param contained True if contains a user-defined string.
	 */
	public TrieNode(boolean contained) {
		this.contained=contained;
	}
	
	/**
	 * Adds a child with key c to the Trie.
	 * @param c The key to add.
	 * @param contains True if contains a user-defined string.
	 */
	public void addChild(Character c, boolean contains) {
		if(children.containsKey(c)) {
			children.get(c).setContained(contains);
		}
		else {
			children.put(c, new TrieNode(contains));
		}
	}
	
	/**
	 * Used to retrieving a child from the Trie.
	 * @param c Specifies the child to retrieve.
	 * @return The TrieNode containing the key c.
	 */
	public TrieNode getChild(Character c) {
		return children.get(c);
	}
	
	/**
	 * Changes the boolean value of contained.
	 * @param contained The value to which this.contained will be changed.
	 */
	public void setContained(boolean contained) {
		this.contained=contained;
	}
	
	/**
	 * Used to get the boolean value of contained.
	 * @return The boolean value of contained.
	 */
	public boolean getContained() {
		return contained;
	}
	
}
