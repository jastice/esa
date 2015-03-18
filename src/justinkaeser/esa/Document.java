package justinkaeser.esa;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;


/**
 * Represents a document. The document is parsed and 
 * represented as set of ngrams with associated frequencies.
 */
public class Document implements Serializable {

	private static final long serialVersionUID = -7155767113963755530L;

	/** Document title. */
	public final String title;	
	
	/** Absolute ngram freuencies. This map may not be modified. */
	final Map<NGram,Integer> ngrams;
	/** Total amount of ngrams. */
	private final int size;
	
	/** Precomputed hash. Possible since the object is immutable. */
	private final int hash;
	
	/**
	 * Create document with given title, text and ngram size of 1.
	 * @param title
	 * @param text
	 */
	public Document(String title, String text) {
		this(title,text,1);
	}
	
	/**
	 * Create document with given title, text and ngram size.
	 * @param title
	 * @param text
	 * @param n ngram size
	 */
	public Document(String title, String text, int n) {
		this.title = title;
		
		BuiltNGrams b = buildNGrams(n, tokenize(text));
		this.ngrams = Collections.unmodifiableMap(b.map);
		this.size = b.size;
		
		this.hash = calculateHash();
	}
	
	/**
	 * Create document with given text, empty title and ngram size 1.
	 * @param text
	 */
	public Document(String text) {
		this("",text);
	}
	
	/**
	 * Absolute frequency of an ngram.
	 * @param ngram
	 * @return
	 */
	public int frequency(NGram ngram) {
		return ngrams.get(ngram);
	}
	
	
	/**
	 * Term frequency of an ngram.
	 * @param ngram
	 * @return
	 */
	public double tf(NGram ngram) {
		return (double)frequency(ngram) / size;
	}
	
	/**
	 * Calculates hash code for this document.
	 * @return
	 */
	private int calculateHash() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((ngrams == null) ? 0 : ngrams.hashCode());
		result = prime * result + size;
		result = prime * result + ((title == null) ? 0 : title.hashCode());
		return result;
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
		
		Document other = (Document) obj;

		if (size != other.size)
			return false;
		
		if (!ngrams.equals(other.ngrams))
			return false;

		if (title == null && other.title != null) {
			return false;
		} else if (!title.equals(other.title))
			return false;
		return true;
	}

	/**
	 * Tokenize the given text, add tokens to a list.
	 * @param text
	 * @return The list of tokens.
	 */
	private static List<String> tokenize(String text) {
		List<String> tokens = new ArrayList<String>();
		
		StringTokenizer tokenizer = new StringTokenizer(text," \t\n,.;:!?-_()[]{}<>|+*/='\"\\");
		
		while (tokenizer.hasMoreTokens())
			tokens.add(tokenizer.nextToken().toLowerCase());
		
		return tokens;
	}
	
	/**
	 * Build n-grams of a list of tokens. 
	 * Builds ngrams of length 1 up to nMax.
	 * @param nMax maximum length of ngrams.
	 * @param tokens
	 * @return
	 */
	private static BuiltNGrams buildNGrams(int nMax, List<String> tokens) {
		
		Map<NGram,Integer> map = new HashMap<NGram,Integer>();
		int sizeCount = 0;
		
		int tsize = tokens.size();
		for (int i = 0; i < tsize; i++) {
			for (int n=1; n<=nMax; n++) {
				String[] ngram = new String[n];
				if (i+n-1 < tsize) {
					for (int t=0; t<n; t++)
						ngram[t] = tokens.get(i+t);
				}
				addNGram(map, new NGram(ngram));
				
				++sizeCount;
			}
		}
		
		return new BuiltNGrams(map, sizeCount);
	}
	
	/**
	 * Add an n-gram to an ngram map if it does not exist or increment 
	 * its frequency count if it does.
	 * @param ngram an ngram
	 */
	private static void addNGram(Map<NGram,Integer> map, NGram ngram) {
		if (map.containsKey(ngram))
			map.put(ngram, map.get(ngram)+1);
		else
			map.put(ngram, 1);
	}
	
	private static class BuiltNGrams {
		public final Map<NGram,Integer> map;
		public final int size;
		
		BuiltNGrams(Map<NGram,Integer> map, int size) {
			this.map = map;
			this.size = size;
		}
	}
}
