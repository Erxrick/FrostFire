package games.indie.frostfire.user.ui;

import java.awt.Point;
import java.awt.Rectangle;

import org.newdawn.slick.Image;

import games.indie.frostfire.Resource;

public class Button extends UIComponent {
	
	protected Image icon;
	
	public Button(int screen_x, int screen_y, String imageName) {
		super(screen_x, screen_y);
		icon = Resource.getImage(imageName);
	}

	public void draw() {
		icon.draw(screen_x, screen_y);
	}
	
	public boolean mousePressed(int button, int x, int y) {
		return new Rectangle(screen_x, screen_y, icon.getWidth(), icon.getHeight()).contains(new Point(x, y));
	}

}
