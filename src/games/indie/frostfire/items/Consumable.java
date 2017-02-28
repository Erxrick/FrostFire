package games.indie.frostfire.items;

import games.indie.frostfire.entities.human.Hand;

public class Consumable extends Item {

	public Consumable(ConsumableType type) {
		super(type.getWeight(), type.getItemName());
	}

	public void stateChange() {
		consume();
	}

	public void use(Hand hand, double direction) {
		consume();
	}
	
	protected void consume() {
	}

}
