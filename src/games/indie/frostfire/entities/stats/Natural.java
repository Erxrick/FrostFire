package games.indie.frostfire.entities.stats;

/**
 * A Stat that changes by itself over time and affects Health once it reaches min
 * 
 * @author Wesley Barlow
 *
 */
public class Natural extends Stat {
	
	protected double changeRate;
	protected Health health;

	public Natural(int min, int max, double changeRate, Health health) {
		super(min, max);
		setChangeRate(changeRate);
		this.health = health;
	}

	public void setChangeRate(double changeRate) {
		this.changeRate = changeRate;
	}
	
	public double getChangeRate() {
		return changeRate;
	}
	
	public void update(int delta) {
		affect(delta * changeRate);
	}
	
	protected void reachMin() {
		health.affect(-.01);
	}

}
