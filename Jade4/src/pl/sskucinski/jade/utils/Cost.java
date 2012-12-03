package pl.sskucinski.jade.utils;

import jade.content.Predicate;

public class Cost implements Predicate {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3150288384914485711L;
	public static int prize;
	
	// Gets
	
	public int getPrize() {
		
		return prize;
	}
	
	// Sets
	
	public void setPrize( int value ) {
		
		prize = value;
	}

}
