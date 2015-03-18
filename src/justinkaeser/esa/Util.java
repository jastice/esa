package justinkaeser.esa;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

public class Util {

	/**
	 * Calculate tf-idf value for an ngram in given corpus and document.
	 * @param corpus
	 * @param text
	 * @param ngram
	 * @return
	 */
	public static double tfidf(Corpus corpus, Document document, NGram ngram) {
		return document.tf(ngram) * corpus.idf(ngram);
	}
	
	/**
	 * Compute cosine similarity of two sparse Double vectors.
	 * @param v1 first vector
	 * @param v2 second vector
	 * @return cosine simliarity of the two vectors
	 */
	public static double cosineSimilarity(Map<?,Double> v1, Map<?,Double> v2) {
		return dot(v1,v2) / (norm(v1.values()) * norm(v2.values()));
	}
	
	/**
	 * Vector norm of a collection of Doubles
	 * @param vector the vector
	 * @return
	 */
	public static double norm(Iterable<Double> vector) {
		double sum = 0;
		for (Double val : vector)
			sum += val*val;
		
		return Math.sqrt(sum);
	}
	
	/**
	 * Dot product of two sparse Double vectors
	 * @param v1 first vector
	 * @param v2 second vector
	 * @return dot product of the two vectors
	 */
	public static double dot(Map<?,Double> v1, Map<?,Double> v2) {
		double product = 0; 
		for(Object key : v1.keySet()) {
			if (v2.containsKey(key))
				product += v1.get(key)*v2.get(key);
		}
		return product;
	}
	
	/**
	 * Add the concepts to a list sorted by the weight of the concepts, descending.
	 * @param concepts sparse vector of concepts
	 * @return sorted list of concepts
	 */
	public static List<Document> sortedConcepts(Map<Document, Double> concepts) {
		List<Document> sorted = new ArrayList<Document>(concepts.keySet());
		Collections.sort(sorted, new ValueComparator(concepts));
		return sorted;
	}	
	
	
	/**
	 * Value comparator for concept vectors, descending
	 */
	private static class ValueComparator implements Comparator<Document> {
		private Map<Document,Double> map;
		public ValueComparator(Map<Document,Double> map) {
			this.map = map;
		}
		@Override
		public int compare(Document o1, Document o2) {
			return map.get(o2).compareTo(map.get(o1));
		}
	}
}
