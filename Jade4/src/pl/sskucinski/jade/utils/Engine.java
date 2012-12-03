package pl.sskucinski.jade.utils;

public class Engine extends AutoMobile{
	
	private int capacity;
	private int horsepower;
	private int mileage;
	
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
	
	public int setCapacity( int value ) {
		
		return capacity = value;
	}
	
	public int setHorsePower( int value ) {
		
		return horsepower = value;
	}
	
	public int setMileAge( int value ) {
		
		return mileage = value;
	}

}
