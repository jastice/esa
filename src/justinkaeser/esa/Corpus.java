package justinkaeser.esa;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Corpus {
	
	private final Map<NGram,Integer> ngramFrequency = new HashMap<NGram, Integer>();
	private final Map<NGram,Set<Document>> ngramDocuments = new HashMap<NGram, Set<Document>>();
	
	/** Set of documents in corpus. */
	private final Set<Document> documents = new HashSet<Document>();
	
	private int totalNGrams = 0;
	
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
			documents.add(document);
		}
		
	}
	
	/**
	 * Inverse document frequency of an ngram.
	 * @return inverse document frequency of given term
	 */
	public double idf(NGram ngram) {
		return Math.log((double)(documents.size()) / ngramDocuments.get(ngram).size());
	}
	
	public double relativeFrequency(NGram ngram) {
		return totalNGrams > 0? (double)ngramFrequency.get(ngram) / totalNGrams : 0;
	}
	
	@Override
	public String toString() {
		StringBuilder result = new StringBuilder();
		for (Document doc : documents)
			result.append(doc.title).append("; ");
		
		return result.toString();
			
	}
}
