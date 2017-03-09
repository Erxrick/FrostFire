package games.indie.frostfire.user.ui;

import games.indie.frostfire.Resource;
import games.indie.frostfire.items.Item;

public abstract class InventorySlot extends UIComponent {
	
	protected Item holding;
	
	public InventorySlot(int screenX, int screenY) {
		setIcon(Resource.getImage("right-hand"));
		setLocation(screenX, screenY);
	}
	
	public void pickup(Item item) {
		holding = item;
	}
	
	public void draw() {
		if (holding != null) {
			holding.getIcon().draw(
					x + 8 - holding.getIcon().getWidth()/2,
					y + 8 - holding.getIcon().getHeight()/2);
		}
	}

}
