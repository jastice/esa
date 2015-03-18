package justinkaeser.esa;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class NGramTest {

	/**
	 * Test if the equals method works as expected.
	 * A little fiddling to make sure the compiler doesn't internalize the literals.
	 */
	@Test 
	public void equality() {
		int n = 0;
		NGram ngram1 = new NGram("Pink","bartender","mash", n+"");
		++n;
		--n;
		NGram ngram2 = new NGram(new String("Pink"),"bar"+"tender","mash", new Integer(n).toString());
		
		assertEquals(ngram1, ngram2);
	}
}
