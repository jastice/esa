package justinkaeser.esa;

import java.io.Serializable;


/**
 * Representation of an n-gram of strings. 
 * Uses internalized strings for memory and comparison efficiency.
 * 
 * @author Justin Kaeser
 */
public class NGram implements Serializable {
	
	private static final long serialVersionUID = 1649057499102338101L;
	
	private final String[] grams;
	private final int hash; // precompute hash for a little extra efficiency.
	
	/**
	 * Construct an ngram from a number of strings.
	 * @param strings
	 */
	public NGram(String... strings) {
		this.grams = new String[strings.length];
		
		// add strings to ngram array and compute hash, all in one!
		final int prime = 31;
		int result = 1;
		for (int i=0; i<strings.length; i++) {
			grams[i] = strings[i].intern();
			result = prime * result + grams[i].hashCode();
		}
		
		this.hash = result;
	}
	
	@Override
	public String toString() {
		StringBuilder result = new StringBuilder();
		for (String s : grams)
			result.append(s).append(" ");
		return result.toString();
	}

	@Override
	public int hashCode() {
		return hash;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		
		NGram other = (NGram) obj;
		if (this.grams.length != other.grams.length)
			return false;
		
		return refCompare(other.grams);
	}
	
	/**
	 * Compare array contents of this objects ngrams with another's by reference. 
	 * We can do this because the Strings are internalized.
	 * @param otherGrams 
	 * @return
	 */
	private boolean refCompare(String[] otherGrams) {
		for (int i=0; i<grams.length; i++)
			if (grams[i] != otherGrams[i]) 
				return false;
		
		return true;
	}
	
	
}
