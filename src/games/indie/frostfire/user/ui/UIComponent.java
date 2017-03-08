package games.indie.frostfire.user.ui;

import games.indie.frostfire.Drawable;

public abstract class UIComponent implements Drawable, Clickable {
	
	protected int screen_x, screen_y;
	
	public UIComponent(int screen_x, int screen_y) {
		this.screen_x = screen_x;
		this.screen_y = screen_y;
	}
	
	public boolean mouseOverSelf(int x, int y) {
		return false;
	}
	
	public boolean mousePressed(int button, int x, int y) {
		return false;
	}
	
	public boolean mouseReleased(int x, int y) {
		return false;
	}
}
