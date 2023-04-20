package MAS;

import jade.core.Agent;

public class TrafficLightAgent extends Agent {
	
	private LightsColor _currentColor;
	private int _waitingCarsCount = 0;
	private int _maxCarsWaitingCount;
	private int _maxGreenTime;
	private int _priority;
	private GreenLightTimeCountBehaviour _greenLightTimeCounter;
	
	
	public GreenLightTimeCountBehaviour getGreenLightTimeCounter() {
		return _greenLightTimeCounter;
	}
	
	
	public void setGreenLightTimeCounter(GreenLightTimeCountBehaviour _greenLightTimeCounter) {
		this._greenLightTimeCounter = _greenLightTimeCounter;
	}
	
	private MySubscriptionManager _myManager = new MySubscriptionManager();
	
	private SwitchColorPropose _proposeInProgress;
	
	public MySubscriptionManager getMySubscriptionManager(){
		return _myManager;
	}

	public void setCurrentColor(LightsColor color){
		_currentColor = color;
	}
	
	public int getMaxGreenTime(){
		return _maxGreenTime;
	}

	public void increaseWaitingCarsCount(){
		_waitingCarsCount++;
	}


	public void decreaseWaitingCarsCount(){
		_waitingCarsCount--;
	}


	public int getWaitingCarsCount(){
		return _waitingCarsCount;
	}


	public int getMaxCarsWaitingCount(){
		return _maxCarsWaitingCount;
	}


	public LightsColor getCurrentColor(){
		return _currentColor;
	}


	public SwitchColorPropose getProposeInProgress(){
		return _proposeInProgress;
	}


	public int getPriority(){
		return _priority;
	}

	public void setProposeInProgress(SwitchColorPropose proposeInProgress) {
		_proposeInProgress = proposeInProgress;
	}

	@Override
	protected void setup() {
		
		Object[] args = getArguments();
		
		if (args != null && args.length > 0) {
			_currentColor = LightsColor.values()[Integer.parseInt(args[0].toString())];
            _waitingCarsCount = Integer.parseInt(args[1].toString());
            _maxCarsWaitingCount = Integer.parseInt(args[2].toString());
            _maxGreenTime = Integer.parseInt(args[3].toString());
            _priority = Integer.parseInt(args[4].toString());
		}
		
		if (_currentColor == LightsColor.Green) {
			System.out.println(this.getLocalName()+ ": light is now green" );
		} else {
			System.out.println(this.getLocalName()+ ": light is now red" );
		}
		
		addBehaviour(new RegisterCarArrivedBehaviour());
		addBehaviour(new RegisterCarGoneBehaviour());
		addBehaviour(new LightSwitchSubscriptionResponder(_myManager));
		addBehaviour(new ChangeColorProposeResponder(this));
	}
	
	private static final long serialVersionUID = 6185206318540380464L;
	
}
