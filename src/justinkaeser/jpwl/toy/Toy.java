package justinkaeser.jpwl.toy;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.nio.CharBuffer;
import java.util.Map;

import justinkaeser.esa.Corpus;
import justinkaeser.esa.Document;
import justinkaeser.esa.Index;
import justinkaeser.esa.Util;
import de.tudarmstadt.ukp.wikipedia.api.DatabaseConfiguration;
import de.tudarmstadt.ukp.wikipedia.api.Page;
import de.tudarmstadt.ukp.wikipedia.api.Wikipedia;
import de.tudarmstadt.ukp.wikipedia.api.WikiConstants.Language;
import de.tudarmstadt.ukp.wikipedia.api.exception.WikiApiException;

public class Toy {
	

	public static void main(String[] args) throws WikiApiException, IOException {

		// configure the database connection parameters
		DatabaseConfiguration dbConfig = new DatabaseConfiguration();
		dbConfig.setHost("localhost");
		dbConfig.setDatabase("wp_de_07");
		dbConfig.setUser("root");
		dbConfig.setPassword("root");
		dbConfig.setLanguage(Language.german);

		// Create the Wikipedia object
		Wikipedia wiki = new Wikipedia(dbConfig);
		
		Corpus corpus = new Corpus();
		Index index = new Index(corpus);
		int i=0;
		for (Page page : wiki.getArticles()) {
			Document doc = new Document(page.getTitle().getPlainTitle(), page.getPlainText());
			corpus.addDocument(doc);
			index.add(doc);
			
			if (i++>=1000)
				break;
		}
		
		File in = new File("/home/jast/windocs/office/studium/Wikipedia/corpus/adolf koch.txt");
		BufferedReader reader = new BufferedReader(new FileReader(in));
		StringBuilder builder = new StringBuilder();
		
		for (String line = ""; (line = reader.readLine()) != null;)
			builder.append(line);
		
		Document bdauh = new Document("bdauh", builder.toString());
		Map<Document, Double> bdauhConcepts = index.conceptVector(bdauh);
		
		for (Document concept : Util.sortedConcepts(bdauhConcepts))
			System.out.println(concept.title);
		
		
//		System.out.println("----- Corpus:");
//		System.out.println(corpus);
//		
//		
//		System.out.println("----- Index:");
//		System.out.println(index);
		        
//		String title = "Mahatma Gandhi";
//		Page page = wiki.getPage(title);
		        
		// the title of the page
//		System.out.println("Queried string       : " + title);
//		System.out.println("Title                : " + page.getTitle());
//
//		// whether the page is a disambiguation page
//		System.out.println("IsDisambiguationPage : " + page.isDisambiguation());
//		        
//		// whether the page is a redirect
//		// If a page is a redirect, we can use it like a normal page.
//		// The other infos in this example are transparently served by the page that the redirect points to. 
//		System.out.println("redirect page query  : " + page.isRedirect());
//		        
//		// the number of links pointing to this page
//		System.out.println("# of ingoing links   : " + page.getNumberOfInlinks());
//		        
//		// the number of links in this page pointing to other pages
//		System.out.println("# of outgoing links  : " + page.getNumberOfOutlinks());
//
//		// the number of categories that are assigned to this page
//		System.out.println("# of categories      : " + page.getNumberOfCategories());


	}
}
