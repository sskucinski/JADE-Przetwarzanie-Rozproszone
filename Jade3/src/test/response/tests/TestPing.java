package test.response.tests;

import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.Behaviour;
import jade.core.behaviours.SimpleBehaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;
import jade.util.leap.Properties;
import jade.wrapper.AgentContainer;
import jade.wrapper.AgentController;
import test.common.Test;
import test.common.TestException;

public class TestPing extends Test {
	private static final long serialVersionUID = 1L;
	private static final String RESPONDER_NAME = "responder";
	private final String CONV_ID = "conv_ID"+hashCode();
	private final String CONTENT = "ping";
	private final String RESPONSE = "pong";
	

	private AgentController respAgent = null;
	private AID resp = null;
	
	@Override
	public Behaviour load(Agent a) throws TestException {
		setTimeout(1000);
		
		String t = this.getTestArgument("el");
		System.out.println("getTestArg: " + t);
		
		resp = new AID(RESPONDER_NAME, AID.ISLOCALNAME);
		
		try {			
			// create agent responder on the same container of the creator agent
			AgentContainer container = (AgentContainer) a.getContainerController(); // get a container controller for creating new agents
			respAgent = container.createNewAgent(RESPONDER_NAME, "examples.PingAgent.PingAgent", null);
			respAgent.start();
			System.out.println(a.getLocalName()+" CREATED AND STARTED NEW THANKSAGENT: " + RESPONDER_NAME + " ON CONTAINER "+container.getContainerName());
						
			Behaviour b = new SimpleBehaviour() {
				private static final long serialVersionUID = 1L;
				private boolean finished = false;
	  			
				public void onStart() {
	  				ACLMessage msg = new ACLMessage(ACLMessage.REQUEST);
	  				msg.addReceiver(resp);
	  				msg.setConversationId(CONV_ID);
	  				msg.setContent(CONTENT);
	  				myAgent.send(msg);
	  			}
	  			
	  			public void action() {
	  				ACLMessage msg = myAgent.receive(MessageTemplate.MatchConversationId(CONV_ID)); 
						if (msg != null) { 
							AID sender = msg.getSender();
							Properties pp = msg.getAllUserDefinedParameters();
							if (!sender.equals(resp)) {
								failed("Unexpected reply sender "+sender.getName());
							} 
							else if (msg.getPerformative() != ACLMessage.INFORM) {
								failed("Unexpected reply performative "+msg.getPerformative());
							}
							else if (!RESPONSE.equals(msg.getContent())) {
								failed("Unexpected reply content "+msg.getContent());
							}
							else {
								passed("Reply message correctly received");
							}
							finished = true;
	  				}
	  				else {
	  					block();
	  				}
	  			}
	  			
	  			public boolean done() {
	  				return finished;
	  			}
	  		};
	  	  		
	  		return b;
			
//		} catch (TestException te) {
//	  		throw te;
	  	}
	  	catch (Exception e) {
	  		throw new TestException("Error loading test", e);	  	
	  	}
	}
	
	@Override
	public void clean(Agent a) {
		try {
			respAgent.kill();
	  		//TestUtility.killAgent(a, resp);
	  		Thread.sleep(1000);
	  	}
	  	catch (Exception e) {
	  		e.printStackTrace();
	  	}
	}
}
