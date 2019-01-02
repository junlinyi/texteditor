package util;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		
		
		/*BloomFilter<String> bloom= new BloomFilter<String>(null, 1024, 5);
		
		bloom.insert("one");
		bloom.insert("two");
		bloom.insert("three");
		bloom.insert("four");
		
		System.out.println(bloom.mightContain("one"));
		System.out.println(bloom.mightContain("two"));
		System.out.println(bloom.mightContain("five"));
		System.out.println(bloom.mightContain("three"));
		System.out.println(bloom.mightContain("four"));
		System.out.println(bloom.mightContain("oner"));
		System.exit(0);*/
		
		
		Trie tree = new Trie();
		tree.insert("hi");
		tree.insert("hit");
		//tree.insert("hita");
		tree.insert("hitab");
		tree.insert("hitac");
		tree.insert("hitabc");
		tree.insert("another");
		tree.insert("abcdef");
		tree.insert("let's");
		
		System.out.println(tree.contains("lets"));
		/*System.out.println(tree.contains("h"));
		System.out.println(tree.contains("hi"));
		System.out.println(tree.contains("hit"));
		System.out.println(tree.contains("hitt"));
		System.out.println(tree.contains("hint"));
		System.out.println(tree.contains("hita"));
		System.out.println(tree.contains("another"));
		System.out.println(tree.closestWordToPrefix("an"));
		tree.delete("hi");
		tree.delete("hit");
		tree.delete("hitt");
		
		
		System.out.println(tree.contains("hi"));
		System.out.println(tree.contains("hit"));
		System.out.println(tree.contains("hitt"));
		System.out.println(tree.contains("hint"));
		System.out.println(tree.contains("hita"));
		System.out.println(tree.contains("another"));
		*/
		
		System.exit(0);
		HashTable<Integer, Object> myTable = new HashTable<>(20);
		System.out.println(myTable.isEmpty());
		//System.out.println(Integer.valueOf(2)==2);
		
		Integer zeroKey = 0;
		Integer oneKey = 1;
		Integer twoKey = 2;
		Integer threeKey = 3;
		Integer fourKey = 4;
		
		
		Integer one = 1;
		Integer two = 2;
		Integer three = 3;
		Integer four = 4;
		Integer notin = 9;
		
		myTable.put(0, 1);
		System.out.println(myTable.isEmpty());
		myTable.put(one, 3);
		myTable.put(2, 1);
		myTable.put(4, 2);
		System.out.println(myTable.size());
		System.out.println(myTable.get(0));
		System.out.println(myTable.get(1));
		System.out.println(myTable.get(2));
		System.out.println(myTable.get(3));
		System.out.println(myTable.get(4));
		System.out.println(myTable.containsKey(3));
		System.out.println(myTable.containsKey(4));
		System.out.println(myTable.remove(4));
		System.out.println(myTable.get(4));
		System.out.println(myTable.remove(4));
		System.out.println(myTable.get(4));
		System.out.println(myTable.containsValue(1));
		System.out.println(myTable.containsValue(one));
		System.out.println(myTable.remove(one));
		System.out.println(myTable.containsValue(notin));
		
		
	}

}
