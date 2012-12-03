package pl.sskucinski.jade.agents;

import jade.content.Concept;
import jade.content.ContentElement;
import jade.content.lang.Codec;
import jade.content.lang.sl.SLCodec;
import jade.content.onto.Ontology;
import jade.content.onto.basic.Action;
import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.core.behaviours.OneShotBehaviour;
import jade.domain.FIPAException;
import jade.lang.acl.ACLMessage;
import pl.sskucinski.jade.interfaces.CarsBase;
import pl.sskucinski.jade.ontology.CarOntology;
import pl.sskucinski.jade.utils.Available;
import pl.sskucinski.jade.utils.Rent;

public class CarDealerAgent extends Agent implements CarsBase {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Codec codec = new SLCodec();
	private Ontology ontology = CarOntology.getInstance();
	
	protected void setup() {
		getContentManager().registerLanguage(codec);
		getContentManager().registerOntology(ontology);
		
		
	}
	
	public void agentReply(ACLMessage msg) {
		
		try {
	         java.io.Serializable content = msg.getContentObject();
	         ACLMessage reply = msg.createReply();
	         reply.setPerformative(ACLMessage.NOT_UNDERSTOOD);
	         reply.setContentObject(content);
	         send(reply);
	      }
	      catch(Exception ex) { 
	    	  ex.printStackTrace();
	      }
		
	}
	
	// Behavior of receiving messages
	
	class ReceiveMessage extends CyclicBehaviour {
		
		public ReceiveMessage (Agent a){
			super(a);
		}

		@Override
		public void action() {
			ACLMessage msg = receive();
			
			if(msg == null) {
				block();
				return;
			}
			
			try {
				
				ContentElement content = getContentManager().extractContent(msg);
				Concept action = ((Action)content).getAction();
				
				switch (msg.getPerformative()) {
				case (ACLMessage.REQUEST):
					System.out.println("Request from " + msg.getSender().getLocalName());
					
					if (content instanceof Available) {
						
						addBehaviour(new HandleAvailable(myAgent, msg));
						
					} else if (content instanceof Rent) {
						
						addBehaviour(new HandleRentCar(myAgent, msg));
						
					} else {
						
						agentReply(msg);
						break;
						
					}
				
				}
				
			} catch (Exception e) {
				
				e.getStackTrace();
				
			}
			
			
		}
		
	}
	
	class HandleAvailable extends OneShotBehaviour {
		
		private ACLMessage request;

	      HandleAvailable(Agent a, ACLMessage request) {

	         super(a);
	         this.request = request;
	      }

		@Override
		public void action() {
			// TODO Auto-generated method stub
			
		}
		
	}
	
	
	class HandleRentCar extends OneShotBehaviour {
		
		private ACLMessage request;

	      HandleRentCar(Agent a, ACLMessage request) {

	         super(a);
	         this.request = request;
	      }

		@Override
		public void action() {
			// TODO Auto-generated method stub
			
		}
		
	}
	
	

}
