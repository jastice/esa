

package justinkaeser.esa;

import java.util.HashMap;
import java.util.Map;

public class Index {
	
	/** Minimal weight a concept should have to be saved into the index. */
	private final double minWeight = 0.001;

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
			Double weight = Util.tfidf(corpus, document, ngram);
			
			if (weight >= minWeight) {
				Map<Document,Double> docweights = getWeights(ngram);
				docweights.put(document, weight);
			}
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
	
	/**
	 * Build sparse weighted concept vector for a given document. 
	 * @param document to build concept vector for
	 * @return sparse weighted concept vector
	 */
	public Map<Document,Double> conceptVector(Document doc) {
		Map<Document,Double> concepts = new HashMap<Document, Double>();
		
		// for every token, multiply the token weight in document with concept weight for token 
		// and add it to resulting concept vector - effectively, this is a scalar product for each concept
		for (NGram ngram : doc.ngrams.keySet()) {
			double weight = Util.tfidf(corpus, doc, ngram);
			Map<Document,Double> ngramWeights = index.get(ngram);
			for (Map.Entry<Document,Double> concept : ngramWeights!=null? ngramWeights.entrySet() : new HashMap<Document,Double>().entrySet()) {
				Document condoc = concept.getKey();
				Double conweight = concepts.get(condoc);
				concepts.put(condoc, (conweight==null? 0 : conweight) + weight*concept.getValue());
			}
		}
		
		return concepts;
	}
	
	
	
	@Override
	public String toString() {
		StringBuilder result = new StringBuilder();
		for (Map.Entry<NGram, Map<Document,Double>> ngram : index.entrySet()) {
			result.append(ngram.getKey()).append("\t\t\t");
			
			for (Map.Entry<Document, Double> docweight : ngram.getValue().entrySet())
				result
					.append(docweight.getKey().title)
					.append(": ").append(docweight.getValue())
					.append("; ");
			
			result.append("\n");
		}
		result.append("\n");
		
		return result.toString();
	}
	
}
