package test.response.tests;

import examples.OfferAgent.OfferAgent;
import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.Behaviour;
import jade.core.behaviours.SimpleBehaviour;
import jade.domain.DFService;
import jade.domain.FIPAException;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.ServiceDescription;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;
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
	private boolean zarejestrowany = false;
	
	
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
			
			Behaviour b = new SimpleBehaviour() {

				/**
				 * 
				 */
				private static final long serialVersionUID = 1L;
				private boolean finished = false;
				private AID nazwaUslugi = null;

				@Override
				public void action() {
					subskrybuj();
					
				}
				
				private void subskrybuj() {
					DFAgentDescription dfagent = new DFAgentDescription();
					ServiceDescription sd = new ServiceDescription();
					sd.setType(OfferAgent.class.getSimpleName());
					dfagent.addServices(sd);
					
					DFAgentDescription[] wynik = null;
					
					try{
						
						wynik = DFService.search(myAgent, dfagent);
						
					} catch (FIPAException fe){
						fe.printStackTrace();
					}
					
					if(wynik.length != 0){
						
						nazwaUslugi = wynik[0].getName();
						
						ACLMessage msg = new ACLMessage(ACLMessage.INFORM);
						msg.addReceiver(nazwaUslugi);
						msg.setLanguage("Polski");
						msg.setOntology("Subskrypcja");
						msg.setContent("subskrybuj");
						myAgent.send(msg);
						
						ACLMessage msgZwrotne = myAgent.receive(MessageTemplate.MatchOntology("Subskrypcja"));

						if (msgZwrotne != null) {
							String reply = msgZwrotne.getContent();
							if (reply.equals("Potwierdzono subskrypcjê!")) {
								zarejestrowany = true;
								finished = true;
								passed("Correctly received registration reply");
							}
						} else {
							block();
						}
						
					} else {
						failed("There are no service that you can register to...");
						System.out.println("Brak us³ugi o nazwie: " + nazwaUslugi.getName());
					}
				}

				@Override
				public boolean done() {
					return finished;
				}
				
			};
			
			// TODO ...
			
			return b;
			
		} catch (Exception e) {
			throw new TestException("Error loading test", e);
		}
		
	}
	
	@Override
	public void clean(Agent a) {
		try {
			responderAgent.kill();
	  		
	  		Thread.sleep(1000);
	  	}
	  	catch (Exception e) {
	  		e.printStackTrace();
	  	}
	}

}
