package justinkaeser.esa;

import java.io.Serializable;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Corpus implements Serializable {
	
	private static final long serialVersionUID = 5088814556571052126L;
	
	private final Map<NGram,Integer> ngramFrequency = new HashMap<NGram, Integer>();
	private final Map<NGram,Set<Document>> ngramDocuments = new HashMap<NGram, Set<Document>>();
	
	private int totalNGrams = 0;
	
	/** Number of documents in corpus. */
	private int totalDocuments = 0;
	
	public void addDocument(Document document) {
		for (Map.Entry<NGram, Integer> entry : document.ngrams.entrySet()) {
			NGram key = entry.getKey();
			Integer value = entry.getValue();
			totalNGrams += value;
			if (ngramFrequency.containsKey(key)) {
				ngramFrequency.put(key, ngramFrequency.get(key) + value);
			} else {
				ngramFrequency.put(key, value);
				ngramDocuments.put(key, new HashSet<Document>());
			}
			ngramDocuments.get(key).add(document);
		}		
	}
	
	/**
	 * Inverse document frequency of an ngram.
	 * @param ngram ngram to calculate inverse document frequency for.
	 * @return inverse document frequency of given term
	 */
	public double idf(NGram ngram) {
		Set<Document> ngramDocs = ngramDocuments.get(ngram);
		return Math.log(
				((double)totalDocuments) / 
				(ngramDocs==null? 0 : ngramDocs.size()) + 1); // add 1 to avoid division by 0
	}
	
	/**
	 * Relative frequency of an ngram, compared to the number of all ngrams in the corpus.
	 * @param ngram
	 * @return
	 */
	public double relativeFrequency(NGram ngram) {
		return totalNGrams > 0? (double)ngramFrequency.get(ngram) / totalNGrams : 0;
	}
	
	@Override
	public String toString() {
		StringBuilder result = new StringBuilder();
		result
			.append("Total documents: ").append(totalDocuments)
			.append("Total n-grams: ").append(totalNGrams).append("\n")
			.append("Unique n-grams: ").append(ngramFrequency.size()).append("\n");
		
		return result.toString();
	}
}
