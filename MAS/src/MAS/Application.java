package MAS;

//import application.ArgumentBuilder;
import jade.Boot;


public class Application {

	public static void main(String[] args) {

		String[] parameters = new String[1];
		
		parameters[0] = "-gui";
		/*
		parameters[1] = 
				ArgumentBuilder.agent("traffic-light-1",
						TrafficLightAgent.class,"0, 0, 10, 10000, 1") +
				ArgumentBuilder.agent("traffic-light-2",
						TrafficLightAgent.class, "1, 0, 10, 10000, 0") +
				ArgumentBuilder.agent("car-traffic-1",
						CarTrafficLightAgent.class, "traffic-light-1, 1000, 0") + 
				ArgumentBuilder.agent("traffic-light-2, 2000, 1",
						CarTrafficLightAgent.class, "traffic-light-2, 2000, 1");
		*/
		Boot.main(parameters);
		
		/*
		parameters[1] = 
				ArgumentBuilder.agent("traffic-light-1",
						TrafficLightAgent.class,"1, 0, 5, 15000, 0") +
				ArgumentBuilder.agent("traffic-light-2",
						TrafficLightAgent.class, "0, 0, 5, 15000, 1") +
				ArgumentBuilder.agent("car-traffic-1",
						CarTrafficLightAgent.class, "traffic-light-1, 3000, 1") + 
				ArgumentBuilder.agent("car-traffic-2",
						CarTrafficLightAgent.class, "traffic-light-2, 3000, 0");
		*/
	}

}
