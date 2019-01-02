package test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({
   BloomFilterTest.class,
   HashTableTest.class,
   SearchTest.class,
   TrieTest.class
})
public class AllTests {
   
}
