package pl.sskucinski.jade.utils;

public class Car {
	
	private String brand;
	private String model;
	private String color;
	private int year;
	
	public Car() {
		
	}
	
	// Gets
	
	public String getBrand() {
		
		return brand;
	}
	
	public String getModel() {
		
		return model;
	}

	public String getColor() {
	
		return color;
	}
	
	public int getYear() {
		
		return year;
	}
	
	// Sets
	
	public void setBrand( String txt ) {
		
		brand = txt;
	}
	
	public void setModel( String txt ) {
		
		model = txt;
	}
	
	public void setColor( String txt ) {
		
		color = txt;
	}
	
	public void setYear( int value ) {
		
		year = value;
	}

}
