package pl.sskucinski.jade.utils;

import jade.content.AgentAction;
import jade.core.AID;

public class Rent implements AgentAction {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2966403142002318415L;
	private AID renter;
	private AutoMobile am;
	private CreditCard cc;
	
	public Rent(AID renter, AutoMobile car, CreditCard crCard) {
		this.am = car;
		this.renter = renter;
		this.cc = crCard;
	}

	public AID getRenter() {
		return renter;
	}

	public void setRenter(AID renter) {
		this.renter = renter;
	}

	public AutoMobile getCar() {
		return am;
	}

	public void setCar(AutoMobile car) {
		this.am = car;
	}

	public CreditCard getCrCard() {
		return cc;
	}

	public void setCrCard(CreditCard crCard) {
		this.cc = crCard;
	}

}
