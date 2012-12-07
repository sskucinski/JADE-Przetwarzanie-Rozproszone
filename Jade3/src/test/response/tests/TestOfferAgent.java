package test.response.tests;

import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.Behaviour;
import jade.wrapper.AgentContainer;
import jade.wrapper.AgentController;
import test.common.Test;
import test.common.TestException;

public class TestOfferAgent extends Test {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private AgentController responderAgent = null;
	private static final String RESPONDER_NAME = "responder";
	private AID responder = null;
	
	
	@Override
	public Behaviour load(Agent a) throws TestException {
		setTimeout(1000);
		
		String testArg = this.getTestArgument("el");
		System.out.println("getTestArg: " + testArg);
		
		responder = new AID(RESPONDER_NAME, AID.ISLOCALNAME);
		
		try {
			
			AgentContainer container = (AgentContainer) a.getContainerController();
			responderAgent = container.createNewAgent(RESPONDER_NAME, "examples.OfferAgent.OfferAgent", null);
			responderAgent.start();
			System.out.println(a.getLocalName()+" CREATED AND STARTED NEW OFFERAGENT: " + RESPONDER_NAME + " ON CONTAINER "+container.getContainerName());
			
			// To-Do...
			
			return null;
			
		} catch (Exception e) {
			throw new TestException("Error loading test", e);
		}
		
	}

}
