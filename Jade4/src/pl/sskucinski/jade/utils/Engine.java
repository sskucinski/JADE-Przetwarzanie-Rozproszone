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
