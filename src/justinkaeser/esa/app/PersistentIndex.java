package justinkaeser.esa.app;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

import justinkaeser.esa.app.IOUtil.ESAData;
import de.tudarmstadt.ukp.wikipedia.api.exception.WikiApiException;
import de.tudarmstadt.ukp.wikipedia.api.exception.WikiTitleParsingException;

/**
 * Build an index and persist it
 * 
 * @author Justin Kaeser
 */
public class PersistentIndex {

	public static void main(String[] args) throws WikiTitleParsingException, WikiApiException, FileNotFoundException, IOException {
		
		ESAData data =  IOUtil.buildIndex();

		File indexFile = new File("/home/jast/bdauh/esa/index");
		
		ObjectOutputStream indexOut = new ObjectOutputStream(new FileOutputStream(indexFile));
		
		indexOut.writeObject(data.index);
	}
}
