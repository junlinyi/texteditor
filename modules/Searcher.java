package modules;

/**
 * Class that implements SearchModule.
 * @author jamal.hashim, junlin.yi
 *
 */
public class Searcher implements SearchModule{

   /**
    * {@inheritDoc}
    */
	@Override
	public int find(String query, String text) {
		// TODO Auto-generated method stub
		if(!text.contains(query)) {
			return -1;
		}
		
		for(int i = 0 ;i<text.length()-query.length();i++) {
			if(text.substring(i, i+query.length()).equals(query)) {
				return i;
			}
		}
		return 0;
	}

}
