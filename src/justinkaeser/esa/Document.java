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
	
	/**
	 * Tokenize the given text, add tokens to a list.
	 * @param text
	 * @return The list of tokens.
	 */
	private static List<String> tokenize(String text) {
		List<String> tokens = new ArrayList<String>();
		
		StringTokenizer tokenizer = new StringTokenizer(text," ,.;:!?-");
		
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
