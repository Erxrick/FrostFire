package games.indie.frostfire;

import games.indie.frostfire.drawing.Sprite;

public class Human extends Creature {
	
	private int hunger, maxHunger;
	private int thirst, maxThirst;
	// TODO temperature affects thirst change rate
	private Sprite head;
	private int strength;
	
	public Human() {
		super(100, 100);
		maxHunger = 100;
		hunger = maxHunger;
		maxThirst = 100;
		thirst = maxThirst;
		strength = 100;
	}
	
	public void addToInventory(Item i) {
		if (inventory.getWeight() + i.getWeight() <= strength) {
			inventory.add(i);
		}
	}

}
