package justinkaeser.esa;

import static org.junit.Assert.*;

import org.junit.Test;

public class DocumentTest {

	String title = "Hans";
	String text = "Hans is a masculine given name. In German, Danish, Dutch, Norwegian, Icelandic and Swedish, originally it is short for Johannes (John) but is also recognized in Sweden, Germany, Denmark and the Netherlands as a name in its own right for official purposes. Hansel (German Hänsel) is a variant, meaning little Hans. Another variant with the same meaning is Hänschen, found in the German proverb Was Hänschen nicht lernt, lernt Hans nimmer mehr, which translates roughly as: What [little] Johnny doesn't learn, [grown-up] John will never learn. Other variants include: Hanns, Hannes, Hansi (also female), Hansele, Hansal, Hensal, Hanserl, Hännschen, Hennes, Hännes, Hänneschen, Henning, Henner, Honsa, Johan, Johann, Jan, Jannes, Jo, Joha, Hanselmann, Hansje.";
	
	@Test
	public void doc1gram() {
		Document doc = new Document(title, text);
		
		assertTrue(doc.ngrams.containsKey(new NGram("hans")));
		assertTrue(doc.ngrams.containsKey(new NGram("german")));
		
		assertFalse(doc.ngrams.containsKey(new NGram("wigalois")));
	}
	
	@Test
	public void doc2gram() {
		Document doc = new Document(title, text, 2);
		
		assertTrue(doc.ngrams.containsKey(new NGram("hans","is")));
		assertTrue(doc.ngrams.containsKey(new NGram("german","danish")));
		assertTrue(doc.ngrams.containsKey(new NGram("hans")));
		
		assertFalse(doc.ngrams.containsKey(new NGram("hans","wigalois")));
	}
}
