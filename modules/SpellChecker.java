package modules;

import util.Trie;

/**
 * Class that implements SpellCheckModule using Trie.
 * @author jamal.hashim, junlin.yi
 *
 */
public class SpellChecker implements SpellCheckModule {
   
	Trie tree = new Trie();
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void addWord(String word) {
		// TODO Auto-generated method stub
		tree.insert(word);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean isValidWord(String word) {
		// TODO Auto-generated method stub
		return tree.contains(word);
	}

}
