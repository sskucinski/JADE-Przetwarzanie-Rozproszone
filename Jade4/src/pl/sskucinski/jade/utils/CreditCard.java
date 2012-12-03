package pl.sskucinski.jade.utils;

import java.util.Date;

public class CreditCard {
	
	public static String type;
	public static int number;
	public static Date date;
	
	// Gets
	
	public String getType() {
		
		return type;
	}
	
	public int getNumber() {
		
		return number;
	}
	
	public Date getDate() {
		
		return date;
	}
	
	// Sets
	
	public void setType( String txt ) {
		
		type = txt; 
	}
	
	public void setNumber( int value ) {
		
		number = value;
	}
	
	public void setDate ( Date val ) {
		
		date = val;
	}

}
