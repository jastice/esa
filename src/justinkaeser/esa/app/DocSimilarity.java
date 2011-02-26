package justinkaeser.esa.app;

import java.io.File;
import java.io.IOException;
import java.util.Map;

import de.tudarmstadt.ukp.wikipedia.api.exception.WikiApiException;
import de.tudarmstadt.ukp.wikipedia.api.exception.WikiTitleParsingException;

import justinkaeser.esa.Document;
import justinkaeser.esa.Index;
import justinkaeser.esa.Util;

/**
 * Calculates the semantic similarity between two documents, based on explicit semantic analysis.
 *  
 * @author Justin Kaeser
 *
 */
public class DocSimilarity {
	
	public static void main(String[] args) throws IOException, WikiTitleParsingException, WikiApiException {
		String input0 = args[0];
		String input1 = args[1];
		
		Document doc0 = IOUtil.fromFile(new File(input0));
		Document doc1 = IOUtil.fromFile(new File(input1));
		
		Index index =  IOUtil.buildIndex().index;
		
		Map<Document, Double> concepts0 = index.conceptVector(doc0);
		Map<Document, Double> concepts1 = index.conceptVector(doc1);
		
		double similarity = Util.cosineSimilarity(concepts0, concepts1);
		
		System.out.println("Calculated relatedness: " + similarity);
	}

}
