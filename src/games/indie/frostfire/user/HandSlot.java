package games.indie.frostfire.user;

import org.newdawn.slick.Image;

import games.indie.frostfire.Resources;
import games.indie.frostfire.entities.Human.BodyPart;

public class HandSlot extends BodySlot {
	
	private Image icon;

	public HandSlot(int x, int y, BodyPart equip, String path) {
		super(x, y, equip);
		icon = Resources.loadImage(path);
	}

	public void draw() {
		icon.draw(screen_x, screen_y);
		super.draw();
	}

}
