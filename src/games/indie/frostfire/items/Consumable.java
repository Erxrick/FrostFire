package games.indie.frostfire.items;

import org.newdawn.slick.geom.Vector2f;

import games.indie.frostfire.Resource;
import games.indie.frostfire.entities.human.BodyPart;
import games.indie.frostfire.entities.human.Hand;

public class Consumable extends Item {
	
	protected ConsumableType type;

	public Consumable(ConsumableType type) {
		super(type.getWeight(), type.getItemName());
		maxStackSize = 9;
		this.type = type;
		setHoldingOffset(new Vector2f(-width/2, height/2));
	}

	public void stateChange(BodyPart holder) {
		holder.getBody().consume(type);
		Resource.getSound("eat").playAsSoundEffect(1, 1, false);
		quantity--;
		if (quantity == 0) {
			holder.setEquipped(null);
		}
	}

	public void use(Hand hand, double direction) {
		stateChange(hand);
	}
	
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((type == null) ? 0 : type.hashCode());
		return result;
	}
	
	public boolean equals(Item item) {
		return getClass().equals(item.getClass()) && type.equals(((Consumable) item).type);
	}
	
}
