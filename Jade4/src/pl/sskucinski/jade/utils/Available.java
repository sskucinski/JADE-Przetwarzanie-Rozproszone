package pl.sskucinski.jade.utils;

import jade.content.Predicate;
import jade.core.AID;

public class Available implements Predicate {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4268594429908524042L;
	AID aid;
	AutoMobile am;
	
	// Gets
	
	public AID getAID() {
		
		return aid;
	}
	
	public AutoMobile getAuto() {
		
		return am;
	}
	
	// Sets
	
	public void setAID (AID val) {
		
		aid = val;
	}
	
	public void setAuto( AutoMobile auto ) {
		
		am = auto;
	}
	
	
	
	

}
