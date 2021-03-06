package games.indie.frostfire.user.ui;

import org.newdawn.slick.Image;

import games.indie.frostfire.Resource;

public class StatIcon extends UIComponent {
	
	private Image icon;

	public StatIcon(int screen_x, int screen_y, String name) {
		super(screen_x, screen_y);
		icon = Resource.getImage(name);
	}

	public void draw() {
		icon.draw(screen_x, screen_y);
	}
}
