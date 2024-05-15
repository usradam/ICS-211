package h12;

import java.io.Serializable;

/**
 * Represents a 2-object tuple.
 * 
 * @author Benjy Strauss
 *
 * @param <X>
 * @param <Y>
 */

public class Pair<X, Y> implements Serializable {
	private static final long serialVersionUID = 1L;
	public X x;
	public Y y;
	
	public Pair() { }
	
	public Pair(X x) { this.x = x; }
	
	public Pair(X x, Y y) {
		this.x = x;
		this.y = y;
	}
	
	/**
	 * Clones the pair, but not the data
	 */
	public Pair<X, Y> pseudoclone() {
		Pair<X, Y> retVal = new Pair<X, Y>();
		retVal.x = x;
		retVal.y = y;
		return retVal;
	}
	
	public boolean equals(Object other) {
		if(!(other instanceof Pair)) {
			return false;
		} else {
			Pair<?,?> otherPair = (Pair<?,?>) other;
			return (x.equals(otherPair.x) && y.equals(otherPair.y));
		}
	}
	
	@SuppressWarnings("deprecation")
	public void deconstruct() {
		try {
			finalize();
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}
	
	public String toString() { return x + "," + y; }
}
