package justinkaeser.esa;

import java.util.Arrays;
import java.util.List;

public class NGram {
	final int n;
	final List<String> grams;
	
	public NGram(String... strings) {
		this.n = strings.length;
		this.grams = Arrays.asList(strings);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((grams == null) ? 0 : grams.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		NGram other = (NGram) obj;
		if (grams == null) {
			if (other.grams != null)
				return false;
		} else if (!grams.equals(other.grams))
			return false;
		return true;
	}
	
	
}
