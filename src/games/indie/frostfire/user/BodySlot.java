package games.indie.frostfire.user;

import games.indie.frostfire.entities.Human.BodyPart;

public class BodySlot extends InventorySlot {
	
	private BodyPart equip;
	
	public BodySlot(int x, int y, BodyPart equip) {
		super(x, y);
		this.equip = equip;
	}

	public void draw() {
		if (holding != null) {
			// TODO draw item
		}
	}

}
