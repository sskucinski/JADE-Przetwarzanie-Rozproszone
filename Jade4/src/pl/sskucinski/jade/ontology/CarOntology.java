package pl.sskucinski.jade.ontology;

import jade.content.onto.BasicOntology;
import jade.content.onto.Ontology;
import jade.content.onto.OntologyException;
import jade.content.schema.ConceptSchema;
import jade.content.schema.PrimitiveSchema;
import pl.sskucinski.jade.interfaces.CarsBase;
import pl.sskucinski.jade.utils.Car;
import pl.sskucinski.jade.utils.Engine;

public class CarOntology extends Ontology implements CarsBase{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final String ONTOLOGY_NAME = "OntologyName";
	private static Ontology Instance = new CarOntology(null);
	
	
	public static Ontology getInstance() {
		
		return Instance;
	}
	
	private CarOntology(Ontology base) {
		
		super(ONTOLOGY_NAME, base);
		
		try{
			
			add(new ConceptSchema(CAR), Car.class);
			add(new ConceptSchema(ENGINE), Engine.class);
			
			ConceptSchema cs1 = (ConceptSchema) getSchema(CAR);
			cs1.add(BRAND, (PrimitiveSchema) getSchema(BasicOntology.STRING));
			cs1.add(MODEL, (PrimitiveSchema) getSchema(BasicOntology.STRING));
			cs1.add(COLOR, (PrimitiveSchema) getSchema(BasicOntology.STRING));
			cs1.add(YEAR, (PrimitiveSchema) getSchema(BasicOntology.INTEGER));
			
			ConceptSchema cs2 = (ConceptSchema) getSchema(ENGINE);
			cs2.add(CAPACITY, (PrimitiveSchema) getSchema(BasicOntology.INTEGER));
			cs2.add(HORSEPOWER, (PrimitiveSchema) getSchema(BasicOntology.INTEGER));
			cs2.add(MILEAGE, (PrimitiveSchema) getSchema(BasicOntology.INTEGER));
			
		} catch(OntologyException oe) {
			
			oe.printStackTrace();
			
		}
	}

}
