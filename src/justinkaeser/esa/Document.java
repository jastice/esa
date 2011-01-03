package justinkaeser.esa;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

/**
 * Represents a text.
 */
public class Document {

	public final String title;
	public final String text;
	
	/** Absolute ngram freuencies. */
	private final Map<NGram,Integer> ngrams;
	
	public Document(String title, String text) {
		this.title = title;
		this.text = text;
		this.ngrams = buildNGrams(tokenize(text));
	}
	
	private static List<String> tokenize(String text) {
		List<String> tokens = new ArrayList<String>();
		
		StringTokenizer tokenizer = new StringTokenizer(text," ,.;:!?-");
		return null;
	}
	
	private static Map<NGram,Integer> buildNGrams(List<String> tokens) {
		return null;
		// TODO implement
	}
}
