package justinkaeser.esa;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;


/**
 * Represents a document. The document is parsed and 
 * represented as set of ngrams with associated frequencies.
 */
public class Document {

	public final String title;
	public final String text;
	
	
	/** Absolute ngram freuencies. */
	final Map<NGram,Integer> ngrams = new HashMap<NGram, Integer>();;
	/** Total amount of ngrams. */
	private int size = 0;
	
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
		this.text = text;
		buildNGrams(n, tokenize(text));
	}
	
	/**
	 * Create document with given text, empty title and ngram size 1.
	 * @param text
	 */
	public Document(String text) {
		this("",text);
	}
	
	/**
	 * Aboslute frequency of an ngram.
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
	

	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((ngrams == null) ? 0 : ngrams.hashCode());
		result = prime * result + size;
		result = prime * result + ((text == null) ? 0 : text.hashCode());
		result = prime * result + ((title == null) ? 0 : title.hashCode());
		return result;
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
		if (ngrams == null) {
			if (other.ngrams != null)
				return false;
		} else if (!ngrams.equals(other.ngrams))
			return false;
		if (size != other.size)
			return false;
		if (text == null) {
			if (other.text != null)
				return false;
		} else if (!text.equals(other.text))
			return false;
		if (title == null) {
			if (other.title != null)
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
	 * @param nMax maximum length of ngrams.
	 * @param tokens
	 * @return
	 */
	private void buildNGrams(int nMax, List<String> tokens) {
		int tsize = tokens.size();
		for (int i = 0; i < tsize; i++) {
			for (int n=1; n<=nMax; n++) {
				String[] ngram = new String[n];
				if (i+n-1 < tsize) {
					for (int t=0; t<n; t++)
						ngram[t] = tokens.get(i+t);
				}
				addNGram(new NGram(ngram));
			}
		}
	}
	
	/**
	 * Add an n-gram to the ngram map if it does not exist or increment 
	 * its frequency count if it does
	 * @param ngram an ngram
	 */
	private void addNGram(NGram ngram) {
		if (ngrams.containsKey(ngram))
			ngrams.put(ngram, ngrams.get(ngram)+1);
		else
			ngrams.put(ngram, 1);
		
		++size;
	}
}
