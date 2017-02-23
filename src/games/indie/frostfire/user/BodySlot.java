package games.indie.frostfire.user;

import games.indie.frostfire.entities.human.BodyPart;

public class BodySlot extends InventorySlot {
	
	private BodyPart holding;
	
	public BodySlot(int screen_x, int screen_y, BodyPart equip) {
		super(screen_x, screen_y);
		this.holding = equip;
	}

}
