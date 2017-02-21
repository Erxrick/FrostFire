package games.indie.frostfire;

import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Rectangle;

public class Sprite extends Rectangle implements Drawable {
	
	// Override draw() or update icon with Animation frames when necessary
	protected Image icon;

	public Sprite() {
		super(0, 0, 0, 0);
	}

	public void draw() {
		icon.draw(getX(), getY());
	}
	
	public void setIcon(Image icon) {
		this.icon = icon;
		setSize(icon.getWidth(), icon.getHeight());
	}

}
