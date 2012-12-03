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
	
	public String setType( String txt ) {
		
		return type = txt; 
	}
	
	public int setNumber( int value ) {
		
		return number = value;
	}
	
	public Date setDate ( Date val ) {
		
		return date = val;
	}

}
