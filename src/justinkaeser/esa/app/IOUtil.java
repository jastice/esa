package justinkaeser.esa.app;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

import justinkaeser.esa.Corpus;
import justinkaeser.esa.Document;
import justinkaeser.esa.Index;
import de.tudarmstadt.ukp.wikipedia.api.DatabaseConfiguration;
import de.tudarmstadt.ukp.wikipedia.api.Page;
import de.tudarmstadt.ukp.wikipedia.api.Wikipedia;
import de.tudarmstadt.ukp.wikipedia.api.WikiConstants.Language;
import de.tudarmstadt.ukp.wikipedia.api.exception.WikiApiException;
import de.tudarmstadt.ukp.wikipedia.api.exception.WikiTitleParsingException;

/**
 * Utility methods for dealing with the mundane io stuff.
 * 
 * @author Justin Kaeser
 *
 */
public class IOUtil {
	
	/** Maximum size of an ESA index. Used to limit index creation time. */
	public static final int MAX_INDEX_SIZE = 1000;

	/**
	 * Create document object from file.
	 * @param file
	 * @return
	 * @throws IOException
	 */
	public static Document fromFile(File file) throws IOException {
		return fromReader(new FileReader(file));
	}
	
	/**
	 * Create document object from a reader.
	 * @param reader
	 * @return
	 * @throws IOException
	 */
	public static Document fromReader(Reader reader) throws IOException {
		BufferedReader buffalo = new BufferedReader(reader);
		StringBuilder builder = new StringBuilder();
		
		for (String line = ""; (line = buffalo.readLine()) != null;)
			builder.append(line);
		
		return new Document("bdauh", builder.toString());
	}
	

	/**
	 * Builds an Explicit Semantic Analysis index from the database configured in DBConnection.
	 * @return
	 * @throws WikiApiException 
	 * @throws WikiTitleParsingException 
	 */
	public static Index buildIndex() throws WikiTitleParsingException, WikiApiException {
		// configure the database connection parameters
		DatabaseConfiguration dbConfig = new DatabaseConfiguration();
		dbConfig.setHost(Properties.HOST);
		dbConfig.setDatabase(Properties.DATABASE);
		dbConfig.setUser(Properties.USER);
		dbConfig.setPassword(Properties.PASSWORD);
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
			
			if (i++ >= MAX_INDEX_SIZE)
				break;
		}
		
		return index;
	}

}
