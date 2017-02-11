package games.indie.frostfire;

import java.util.HashMap;

public class Human extends Creature {
	
	private int hunger, maxHunger;
	private int thirst, maxThirst;
	// TODO temperature affects thirst change rate
	// TODO Sprite for head
	private int strength;
	private HashMap<BodyPart, Item> gear;
	
	public Human() {
		super(100, 100);
		gear = new HashMap<>();
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
