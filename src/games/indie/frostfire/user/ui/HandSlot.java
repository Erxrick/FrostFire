package games.indie.frostfire.user.ui;

import org.newdawn.slick.Image;

import games.indie.frostfire.Resource;
import games.indie.frostfire.entities.human.Action.ActionType;
import games.indie.frostfire.entities.human.Hand;

public class HandSlot extends BodySlot {
	
	private Image icon;

	public HandSlot(int x, int y, Hand equip, String name) {
		super(x, y, equip);
		icon = Resource.getImage(name);
		if (equip.getSide() == ActionType.PUNCH_LEFT) {
			icon = icon.getFlippedCopy(true, false);
		}
	}

	public void draw() {
		icon.draw(screen_x, screen_y);
		super.draw();
	}

}