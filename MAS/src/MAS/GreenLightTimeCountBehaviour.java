package MAS;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.TickerBehaviour;
import jade.domain.AMSService;
import jade.domain.FIPAException;
import jade.domain.FIPANames;
import jade.domain.FIPAAgentManagement.AMSAgentDescription;
import jade.domain.FIPAAgentManagement.SearchConstraints;
import jade.lang.acl.ACLMessage;

public class GreenLightTimeCountBehaviour extends TickerBehaviour {
	
	//  онструктор класса
	public GreenLightTimeCountBehaviour(Agent a, long period) {
		super(a, period);
	}

	@Override
	protected void onTick() {
		ACLMessage msg = new ACLMessage(ACLMessage.REQUEST);
		// ѕолучили имена агентов
		List<String> agentsNames = getAllAgentsNames();
		//  аждого агента назначили получателем сообщени€
		for (String name : agentsNames) {
			msg.addReceiver(new AID(name, AID.ISLOCALNAME));
		}
		// ”становили протокол взаимодействи€
		msg.setProtocol(FIPANames.InteractionProtocol.FIPA_PROPOSE);
		// ”становили ответет через 10 сек от текущего момента времени
		msg.setReplyByDate(new Date(System.currentTimeMillis() + 10000));
		try {
			msg.setContentObject(new SwitchColorPropose(LightsColor.Red, ((TrafficLightAgent)myAgent).getPriority()));
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println(myAgent.getLocalName() + ": green-light time is over");
		myAgent.addBehaviour(new ChangeColorProposeInitiator(myAgent, msg));
	}
	
	private List<String> getAllAgentsNames() {
		try {
			SearchConstraints c = new SearchConstraints();
			c.setMaxResults(new Long(-1));
			AMSAgentDescription[] agents = AMSService.search(myAgent, new AMSAgentDescription(), c);
			List<String> names = new ArrayList<>();
			
			for (AMSAgentDescription agentDescription : agents) {
				if (!agentDescription.getName().getLocalName().equals(myAgent.getLocalName())) {
					names.add(agentDescription.getName().getLocalName());
				}
			}
			return names;
		} catch (FIPAException e) {
			e.printStackTrace();
		}
		return null;
	}

}
