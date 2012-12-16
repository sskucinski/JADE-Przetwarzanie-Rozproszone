package pl.sskucinski.jade.utils;

import jade.content.Predicate;

public class Cost implements Predicate {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3150288384914485711L;
	private int prize;
	private AutoMobile auto;
	
	public Cost(Integer price) {
		this.prize = price;
	}
	public Cost(AutoMobile car) {
		auto = car;
	}
	public Cost(Integer price, AutoMobile car) {
		this.prize = price;
		this.auto = car;
	}
	
	// Gets
	
	public int getPrize() {
		
		return prize;
	}
	
	public AutoMobile getAuto() {
		return auto;
	}
	
	// Sets
	
	public void setPrize( int value ) {
		
		prize = value;
	}
	
	public void setAuto(AutoMobile auto) {
		this.auto = auto;
	}

}
