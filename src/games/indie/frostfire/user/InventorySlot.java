package games.indie.frostfire.user;

import games.indie.frostfire.items.Item;

public abstract class InventorySlot extends UIComponent {
	
	protected Item holding;
	
	public InventorySlot(int screen_x, int screen_y) {
		super(screen_x, screen_y);
	}
	
	public void pickup(Item item) {
		holding = item;
	}
	
	public void draw() {
		if (holding != null) {
			holding.getIcon().draw(
					screen_x + 8 - holding.getIcon().getWidth()/2,
					screen_y + 8 - holding.getIcon().getHeight()/2);
		}
	}

}
