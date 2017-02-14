package games.indie.frostfire.user;

import games.indie.frostfire.items.Item;

public abstract class InventorySlot extends UIComponent {
	
	protected Item holding;
	
	public InventorySlot(int screen_x, int screen_y) {
		super(screen_x, screen_y);
	}

}
