

package justinkaeser.esa;

import java.util.HashMap;
import java.util.Map;

public class Index {

	/**
	 * Weighted list of "concepts" aka documents.
	 * ngram -> (concept -> weight)
	 */
	private final Map<NGram,Map<Document,Double>> index = new HashMap<NGram, Map<Document,Double>>();
	
	/** Corpus this index refers to. */
	private final Corpus corpus;
	
	public Index(Corpus corpus) {
		this.corpus = corpus;
	}
	
	/**
	 * Add ngrams in a document to index, weighting them against the corpus via tf-idf.
	 * @param document
	 */
	public void add(Document document) {
		for (NGram ngram : document.ngrams.keySet()) {
			Map<Document,Double> docweights = getWeights(ngram);
			Double weight = Util.tfidf(corpus, document, ngram);
			docweights.put(document, weight);
		}
	}
	
	/**
	 * Get map of weights for an ngram. 
	 * @param ngram
	 */
	private Map<Document,Double> getWeights(NGram ngram) {
		if (!index.containsKey(ngram))
			index.put(ngram, new HashMap<Document, Double>());
		
		return index.get(ngram);
	}
}
