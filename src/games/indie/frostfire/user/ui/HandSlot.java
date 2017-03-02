package games.indie.frostfire.user.ui;

import org.newdawn.slick.Image;

import games.indie.frostfire.Resource;
import games.indie.frostfire.entities.human.BodyPart;

public class HandSlot extends BodySlot {
	
	private Image icon;

	public HandSlot(int x, int y, BodyPart equip, String name) {
		super(x, y, equip);
		icon = Resource.getImage(name);
	}

	public void draw() {
		icon.draw(screen_x, screen_y);
		super.draw();
	}

}