package justinkaeser.esa.app;

import de.tudarmstadt.ukp.wikipedia.api.WikiConstants.Language;

/**
 * This is like a properties file, but for programmers only. Use to set DB connection data.
 * 
 * @author Justin Kaeser
 *
 */
public interface Properties {
	
	String HOST = "localhost";
	String DATABASE = "wp_de_07";
	String USER = "root";
	String PASSWORD = "root";
	
	Language LANGUAGE = Language.german;

}
