package games.indie.frostfire.entities;

import games.indie.frostfire.items.Inventory;
import games.indie.frostfire.items.Item;

public abstract class Creature extends Entity {
	
	private int health, maxHealth;
	private int energy, maxEnergy;
	private int temperature, coldBound, hotBound;
	private int defence;
	protected Inventory inventory;
	
	public Creature(int maxHealth, int maxEnergy) {
		this.maxHealth = maxHealth;
		this.maxEnergy = maxEnergy;
		this.coldBound = 0; // Celcius
		this.hotBound = 35;
		init();
	}
	
	private void init() {
		health = maxHealth;
		energy = maxEnergy;
	}
	
	public void setTemperatureBounds(int coldBound, int hotBound) {
		this.coldBound = coldBound;
		this.hotBound = hotBound;
	}
	
	public void addToInventory(Item i) {
		inventory.add(i);
	}
	
	public void takeDamage(int damage) {
		
	}
	
	public void setCurrentHealth(int health) {
		this.health = health;
		if (this.health <= 0) {
			die();
		}
	}
	
	private void die() {
		// TODO drop inventory
	}

}
