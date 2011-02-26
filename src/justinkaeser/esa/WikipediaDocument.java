package justinkaeser.esa;

/**
 * Document with attributes specific to JWPL/Wikipedia.
 * 
 * @author Justin Kaeser
 *
 */
public class WikipediaDocument extends Document {
	
	public final int id;
	
	public WikipediaDocument(String title, String text, int n, int id) {
		super(title, text, n);
		this.id = id;
	}
	
}
