package examples.PingAgent;

import java.util.Random;

import test.response.tests.TestDecideAgent;

import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.Behaviour;
import jade.core.behaviours.CyclicBehaviour;
import jade.domain.DFService;
import jade.domain.FIPAException;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.ServiceDescription;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;


public class DecideAgent extends Agent {

	/**
	 *   
	 */
	private static final long serialVersionUID = 1L;
	private AID nazwaUslugi = null;
	private boolean zarejestrowany = false;
	private int sumator = 0;
	
	private int limit = 10;
	private boolean automat = false;
	
	@Override
	protected void setup() {		
		try {
			odpowiedziAgenta("Rozpoczynam pracê!");
			
			Thread.sleep(1500);
			if (automat == true) {
				addBehaviour(new Rejestracja(this));
				addBehaviour(new OdczytajWiadomosci(this));
			} else {
				addBehaviour(new OdczytajWiadomosci(this));
			}
			
			
			
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}
	
	public void odpowiedziAgenta(String msg) {
		System.out.println("[" + getLocalName() + "] - " + msg);
	}
	
	private final class Rejestracja extends Behaviour {

		/**
		 * 
		 */
		private static final long serialVersionUID = -7009336491654547266L;
		
		public Rejestracja(DecideAgent agent) {
			super(agent);
		}

		@Override
		public void action() {
			subskrybuj();			
		}

		@Override
		public boolean done() {
			return zarejestrowany;
		}
		
		private void subskrybuj() {
			DFAgentDescription dfagent = new DFAgentDescription();
			ServiceDescription sd = new ServiceDescription();
			sd.setType(TestDecideAgent.class.getSimpleName());
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
				send(msg);
				
				ACLMessage msgZwrotne = receive(MessageTemplate.MatchOntology("Subskrypcja"));

				if (msgZwrotne != null) {
					String reply = msgZwrotne.getContent();
					if (reply.equals("Potwierdzono subskrypcjê!")) {
						zarejestrowany = true;
					}
				} else {
					block();
				}
				
			} else {
				odpowiedziAgenta("Brak us³ugi o nazwie: " + nazwaUslugi.getName());
			}
		}
		
	}
	
	private final class OdczytajWiadomosci extends CyclicBehaviour {

		/**
		 * 
		 */
		private static final long serialVersionUID = 2600443665236617263L;
		private int counter = 0;
		
		public OdczytajWiadomosci(DecideAgent agent) {
			super(agent);
		}

		@Override
		public void action() {
			odczytanySpam();
		}
		
		private void odczytanySpam() {
			
			if(zarejestrowany = false) {
				odpowiedziAgenta("NIE JESTEŒ SUBSKRYBENTEM ¯ADNEJ US£UGI!");
				return;
			}
			
			ACLMessage odbior = myAgent.receive(MessageTemplate.MatchOntology("Spam"));
			
			if (odbior != null) {
				odpowiedziAgenta("Otrzymano wiadomoœæ: " + odbior.getContent());
				counter++;
			}
			
			if (automat == true && counter >= limit){
				ACLMessage msg = new ACLMessage(ACLMessage.INFORM);
				msg.addReceiver(nazwaUslugi);
				msg.setLanguage("Polski");
				msg.setOntology("Subskrypcja");
				msg.setContent("desubskrybuj");
				send(msg);
				
				myAgent.doDelete();
			}
			
		}
		
	}
	

}
