package pl.sskucinski.jade.utils;

public class Car extends AutoMobile{
	
	private String brand;
	private String model;
	private String color;
	private int year;
	
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
	
	public String setBrand( String txt ) {
		
		return brand = txt;
	}
	
	public String setModel( String txt ) {
		
		return model = txt;
	}
	
	public String setColor( String txt ) {
		
		return color = txt;
	}
	
	public int setYear( int value ) {
		
		return year = value;
	}

}
