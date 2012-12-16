package pl.sskucinski.jade.utils;

import jade.content.Concept;

public class Engine implements Concept {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1489144829020103489L;
	private int capacity;
	private int horsepower;
	private int mileage;
	
	public Engine() {
		
	}
	
	// Gets
	
	public int getCapacity() {
		
		return capacity;
	}
	
	public int getHorsePower() {
		
		return horsepower;
	}
	
	public int getMileAge() {
		
		return mileage;
	}
	
	// Sets
	
	public void setCapacity( int value ) {
		
		capacity = value;
	}
	
	public void setHorsePower( int value ) {
		
		horsepower = value;
	}
	
	public void setMileAge( int value ) {
		
		mileage = value;
	}

}
