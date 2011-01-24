package justinkaeser.esa;

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
	
	
}
