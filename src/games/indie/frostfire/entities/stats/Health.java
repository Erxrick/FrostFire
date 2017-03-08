package games.indie.frostfire.entities.stats;

import games.indie.frostfire.entities.Entity;

public class Health extends Stat {
	
	protected Entity entity;

	public Health(Entity entity, int min, int max) {
		super(min, max);
		this.entity = entity;
	}
	
	protected void reachMin() {
		entity.die();
	}
	public double getHealth() {
		return this.currentValue;
	}
	public void setHp(double health) {
		this.currentValue = health;
	}

}
