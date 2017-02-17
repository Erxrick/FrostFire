package games.indie.frostfire.user;

import org.newdawn.slick.Image;

import games.indie.frostfire.Resource;

public class Stat extends UIComponent {
	
	private Image icon;

	public Stat(int screen_x, int screen_y, String name) {
		super(screen_x, screen_y);
		icon = Resource.get(name);
	}

	public void draw() {
		icon.draw(screen_x, screen_y);
	}
}
