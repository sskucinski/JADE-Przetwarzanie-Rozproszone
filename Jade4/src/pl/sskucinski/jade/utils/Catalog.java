package pl.sskucinski.jade.utils;

import jade.util.leap.List;

public class Catalog extends AutoMobile {

	public Catalog(Integer id) {
		super(id);
	}
	
	public static List getCars() {
		return cars;
	}

	public static void setCars(List cars) {
		Catalog.cars = cars;
	}

	private static List cars;

}
