package pl.sskucinski.jade.ontology;

import jade.content.onto.BasicOntology;
import jade.content.onto.Ontology;
import jade.content.onto.OntologyException;
import jade.content.schema.AgentActionSchema;
import jade.content.schema.ConceptSchema;
import jade.content.schema.ObjectSchema;
import jade.content.schema.PredicateSchema;
import jade.content.schema.PrimitiveSchema;
import pl.sskucinski.jade.interfaces.CarsBase;
import pl.sskucinski.jade.utils.AutoMobile;
import pl.sskucinski.jade.utils.Available;
import pl.sskucinski.jade.utils.Car;
import pl.sskucinski.jade.utils.Catalog;
import pl.sskucinski.jade.utils.Cost;
import pl.sskucinski.jade.utils.CreditCard;
import pl.sskucinski.jade.utils.Engine;
import pl.sskucinski.jade.utils.Rent;

public class CarOntology extends Ontology implements CarsBase{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final String ONTOLOGY_NAME = "OntologyName";
	private static Ontology Instance = new CarOntology();
	
	
	public static Ontology getInstance() {
		
		return Instance;
	}
	
	private CarOntology() {
		
		super(ONTOLOGY_NAME, BasicOntology.getInstance());
		
		try{
			
			add(new ConceptSchema(CAR), Car.class);
			add(new ConceptSchema(ENGINE), Engine.class);
			add(new PredicateSchema(AVAILABLE), Available.class);
			add(new ConceptSchema(COST), Cost.class);
			add(new ConceptSchema(AUTOMOBILE), AutoMobile.class);
			add(new ConceptSchema(CREDITCARD), CreditCard.class);
			add(new AgentActionSchema(RENT), Rent.class);
			add(new ConceptSchema(CATALOG), Catalog.class);
			
			ConceptSchema cs1 = (ConceptSchema) getSchema(CAR);
			cs1.add(BRAND, (PrimitiveSchema) getSchema(BasicOntology.STRING));
			cs1.add(MODEL, (PrimitiveSchema) getSchema(BasicOntology.STRING));
			cs1.add(COLOR, (PrimitiveSchema) getSchema(BasicOntology.STRING));
			cs1.add(YEAR, (PrimitiveSchema) getSchema(BasicOntology.INTEGER));
			
			ConceptSchema cs2 = (ConceptSchema) getSchema(ENGINE);
			cs2.add(CAPACITY, (PrimitiveSchema) getSchema(BasicOntology.INTEGER));
			cs2.add(HORSEPOWER, (PrimitiveSchema) getSchema(BasicOntology.INTEGER));
			cs2.add(MILEAGE, (PrimitiveSchema) getSchema(BasicOntology.INTEGER));
			
			ConceptSchema cs3 = (ConceptSchema) getSchema(COST);
			cs3.add(PRIZE, (PrimitiveSchema) getSchema(BasicOntology.INTEGER));
			cs3.add(AUTOMOBILE, (PrimitiveSchema) getSchema(BasicOntology.INTEGER));
			
			ConceptSchema cs4 = (ConceptSchema) getSchema(AUTOMOBILE);
			cs4.add(SERIALID, (PrimitiveSchema) getSchema(BasicOntology.INTEGER));
			
			ConceptSchema cs5 = (ConceptSchema) getSchema(CREDITCARD);
			cs5.add(TYPE, (PrimitiveSchema) getSchema(BasicOntology.STRING));
			cs5.add(NUMBER, (PrimitiveSchema) getSchema(BasicOntology.INTEGER));
			cs5.add(DATE, (PrimitiveSchema) getSchema(BasicOntology.DATE));
			
			ConceptSchema cs6 = (ConceptSchema) getSchema(CATALOG);
			cs6.add(CARS, (PrimitiveSchema) getSchema(AUTOMOBILE),1, ObjectSchema.UNLIMITED);
			
			AgentActionSchema aas1 = (AgentActionSchema) getSchema(RENT);
			aas1.add(AID, (PrimitiveSchema) getSchema(BasicOntology.AID));
			aas1.add(AUTOMOBILE, (PrimitiveSchema) getSchema(BasicOntology.INTEGER));
			aas1.add(CREDITCARD, (PrimitiveSchema) getSchema(BasicOntology.STRING));
			
			PredicateSchema ps1 = (PredicateSchema) getSchema(AVAILABLE);
			ps1.add(AID, (PrimitiveSchema) getSchema(BasicOntology.AID));
			ps1.add(AUTOMOBILE, (PrimitiveSchema) getSchema(BasicOntology.INTEGER));
			
		} catch(OntologyException oe) {
			
			oe.printStackTrace();
			
		}
	}

}
