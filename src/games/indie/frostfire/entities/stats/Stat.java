package games.indie.frostfire.entities.stats;

/**
 * 
 * @author Wesley Barlow
 *
 */
public class Stat {
	
	protected int min, max;
	protected double currentValue;
	
	public Stat(int min, int max) {
		this.max = max;
		currentValue = max;
	}
	
	public void affect(double amount) {
		currentValue += amount;
		if (currentValue <= min) {
			reachMin();
		} else if (currentValue >= max)
			reachMax();
	}
	
	protected void reachMin() {
		currentValue = min;
	}
	
	protected void reachMax() {
		currentValue = max;
	}
	
	public String toString() {
		return (int) currentValue + "/" + max;
	}
	
	public double getPercentage() {
		return currentValue/max;
	}

}
