package games.indie.frostfire.items;

import games.indie.frostfire.entities.human.BodyPart;
import games.indie.frostfire.entities.human.Hand;

public class Consumable extends Item {
	
	protected ConsumableType type;

	public Consumable(ConsumableType type) {
		super(type.getWeight(), type.getItemName());
		maxStackSize = 5;
		this.type = type;
	}

	public void stateChange(BodyPart holder) {
		holder.getBody().consume(type);
		holder.setEquipped(null);
	}

	public void use(Hand hand, double direction) {
		stateChange(hand);
	}

}
