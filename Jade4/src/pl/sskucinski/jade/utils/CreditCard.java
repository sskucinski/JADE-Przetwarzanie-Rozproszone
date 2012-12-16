package pl.sskucinski.jade.utils;

import java.util.Date;

public class CreditCard {
	
	private String type;
	private int number;
	private Date date;
	
	public CreditCard(String type, int number, Date expDate) {
		this.setType(type);
		this.setDate(expDate);
		this.setNumber(number);
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

}
