package games.indie.frostfire.entities;

public class Stat {
	
	protected Entity entity;
	protected int min, max;
	protected double currentValue;
	
	public Stat(Entity entity, int min, int max) {
		this.entity = entity;
		this.max = max;
		currentValue = max;
	}
	
	public void add(double amount) {
		currentValue += amount;
		if (currentValue < 0) {
			currentValue = 0;
		} else if (currentValue > max)
			currentValue = max;
	}
	
	public void setValue(int value) {
		this.currentValue = value;
	}
	
	public void update(int delta) {
		
	}

}
