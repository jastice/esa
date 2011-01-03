package justinkaeser.esa;

import java.util.HashMap;
import java.util.Map;

public class Corpus {
	
	private final Map<NGram,Integer> ngrams = new HashMap<NGram, Integer>();
	private int totalNGrams = 0;
	
	public void addDocument(Document document) {
		
	}
	
	public double relativeFrequency(NGram ngram) {
		return totalNGrams > 0? (double)ngrams.get(ngram) / totalNGrams : 0;
	}
}
