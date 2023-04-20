package MAS;

public enum LightsColor {
	Red(0),
	Green(1);
	
	private final int id;
	
	// Constructor
	LightsColor(int id) {
		this.id = id;
	}
	
	// Method
	public int getValue() {
		return id;
	}
	
}
