package examples.OfferAgent;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.Behaviour;
import jade.core.behaviours.CyclicBehaviour;
import jade.core.behaviours.ThreadedBehaviourFactory;
import jade.core.behaviours.TickerBehaviour;
import jade.domain.DFService;
import jade.domain.FIPAException;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.ServiceDescription;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;



public class OfferAgent extends Agent {

	/**
	 * Agent wysy�aj�cy offerty i subskrybuj�cy...
	 */
	
	private static final long serialVersionUID = 1L;
	private Behaviour behave;
	private ThreadedBehaviourFactory tbf = new ThreadedBehaviourFactory();
	private static Set<AID> subskrybenci = new HashSet<AID>();
	
	@Override
	protected void setup() {
		odpowiedziAgenta("Rozpoczynam prac�!");
		
		rejestracjaUslugi();
		rejestracjaBehave();
		
	}
	
	@Override
	protected void takeDown() {
		try {
			DFService.deregister(this);
		} catch (FIPAException e) {
			e.printStackTrace();
			}
	}
	
	@Override
	public void doDelete() {
		
		tbf.getThread(behave).interrupt();
		super.doDelete();
		
	}
	
	protected void rejestracjaUslugi() {
		
		DFAgentDescription dfa = new DFAgentDescription();
		dfa.setName(getAID());

		ServiceDescription sd = new ServiceDescription();
		sd.setType(getClass().getSimpleName());
		sd.setName("(" + getLocalName() + ") - " + getClass().getSimpleName());
		dfa.addServices(sd);

		try {
		DFService.register(this, dfa);
		} catch (FIPAException e) {
		e.printStackTrace();
		}
		
		odpowiedziAgenta("Zarejestrowa�em us�ug�!");
		
	}
	
	protected void rejestracjaBehave() {
		behave = new Subskrypcja(this);
		addBehaviour(tbf.wrap(behave));
		
		addBehaviour(new WysylajPowiadomienia(this, 1000));
	}
	
	public void odpowiedziAgenta(String msg) {
		System.out.println("[" + getLocalName() + "] - " + msg);
	}
	
	public class Subskrypcja extends CyclicBehaviour {
		
		/**
		 * Operacja Subskrypcji
		 */
		private static final long serialVersionUID = 8155862681327142068L;
		private Agent subskrybent;
		
		public Subskrypcja(OfferAgent oa) {
			super(oa);
			
			subskrybent = myAgent;
		}
		
		@Override
		public void onStart() {
			odpowiedziAgenta("Oczekuj� na subskrybcj�!");
		}

		@Override
		public void action() {
			ACLMessage msg = subskrybent.receive(MessageTemplate.MatchOntology("Subskrypcja"));
			
			if (msg != null) {
				przetwarzajWiadomosc(msg);
			} else {
				block();
			}
			
		}
		
		private void dodaj(AID aid) {
			subskrybenci.add(aid);
		}
		
		private void usun(AID aid) {
			subskrybenci.remove(aid);
		}
		
		private void przetwarzajWiadomosc(ACLMessage msg) {
			String wiadomosc = msg.getContent().toLowerCase();
			
			if(wiadomosc.equalsIgnoreCase("subskrybuj")) {
				
				AID aid = msg.getSender();
				
				if(subskrybenci.contains(aid)) {
					odpowiedziAgenta("Pro�b� odrzucono! Ju� jeste� subskrybentem tej oferty!");
					return;
				} else {
					
					dodaj(aid);
					
					odpowiedziAgenta("dodano nowego subskrybenta o nazwie: " + aid.getLocalName());
					
					ACLMessage odpowiedz = msg.createReply();
					odpowiedz.addReceiver(aid);
					odpowiedz.setContent("Potwierdzono subskrypcj�!");
					send(odpowiedz);
				}
				
			} else if(wiadomosc.equalsIgnoreCase("desubskrybuj")) {
				
				AID aid = msg.getSender();
				
				if(subskrybenci.contains(aid)) {
					usun(aid);
					
					odpowiedziAgenta("usuni�to subskrybenta o nazwie: " + aid.getLocalName());
					
					ACLMessage odpusun = msg.createReply();
					odpusun.addReceiver(aid);
					odpusun.setContent("Usuni�to subskrypcj�!");
					send(odpusun);
				}
				
			} else {
				odpowiedziAgenta("Nieznana warto�� wiadomo�ci: " + wiadomosc);
			}
		}

		
	}
	
	public static class WysylajPowiadomienia extends TickerBehaviour {
		
		/**
		 * Powiadomienia...
		 */
		private static final long serialVersionUID = 2883465289863822714L;
		private final static List<String> powiadomienia = new ArrayList<String>();
		private Random rand = new Random();
		
		static {
			powiadomienia.add("Ha zasubskrybowa�e�! A wi�c witaj!");
			powiadomienia.add("Teraz b�dziesz zasypywany spamem!");
			powiadomienia.add("Spam spam spam spam HA HA HA!!");
			powiadomienia.add("Je�li jeste� zadowolony z naszych us�ug wci�nij 0");
		}

		public WysylajPowiadomienia(Agent a, long period) {
			super(a, period);
		}

		@Override
		protected void onTick() {
			ACLMessage msg = new ACLMessage(ACLMessage.INFORM);
			
			for (AID aid : subskrybenci) {
				msg.addReceiver(aid);
			}
			
			msg.setLanguage("Polski");
			msg.setOntology("Spam");
			msg.setContent(powiadomienia.get(rand.nextInt(powiadomienia.size() - 1)));
			myAgent.send(msg);
			
		}
		
	}

}

