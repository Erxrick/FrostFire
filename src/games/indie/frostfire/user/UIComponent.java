package games.indie.frostfire.user;

import games.indie.frostfire.Drawable;

public abstract class UIComponent implements Drawable {
	
	protected int screen_x, screen_y;
	
	public UIComponent(int screen_x, int screen_y) {
		this.screen_x = screen_x;
		this.screen_y = screen_y;
	}
}
