package games.indie.frostfire.user;

import org.newdawn.slick.Image;

import games.indie.frostfire.Resources;

public class Stat extends UIComponent {
	
	private Image icon;

	public Stat(int screen_x, int screen_y, String path) {
		super(screen_x, screen_y);
		icon = Resources.loadImage(path);
	}

	public void draw() {
		icon.draw(screen_x, screen_y);
	}
}
