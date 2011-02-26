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
 * Finds the top n concepts related to a given input text.
 * 
 *  Usage: java TopConcepts inputfile [n]
 * 
 * @author Justin Kaeser
 *
 */
public class TopConcepts {
	
	public static void main(String[] args) throws WikiTitleParsingException, WikiApiException, IOException {
		String inputText = args[0];
		Integer n = args.length > 1 ? new Integer(args[1]) : 10;
		
		Document doc = IOUtil.fromFile(new File(inputText));
		Index index =  IOUtil.buildIndex().index;
		
		Map<Document, Double> concepts = index.conceptVector(doc);
		
		for (Document concept : Util.sortedConcepts(concepts).subList(0, n))
			System.out.println(concept.title);

	}
}
