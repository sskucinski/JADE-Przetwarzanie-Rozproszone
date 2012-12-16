package pl.sskucinski.jade.utils;

public class AutoMobile {
	
	int serialID;
	
	public AutoMobile(Integer id) {
		serialID = id;
	}
	
	// Gets
	
	public int getSerial() {
		
		return serialID;
	}
	
	// Sets
	
	public void setSerial( int value ) {
		
		serialID = value;
	}

}
