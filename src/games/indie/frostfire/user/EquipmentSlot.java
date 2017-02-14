package games.indie.frostfire.user;

import org.newdawn.slick.Image;

import games.indie.frostfire.Drawable;
import games.indie.frostfire.Resources;

public class EquipmentSlot implements Drawable {
	
	private BodySlot first;
	private BodySlot second;
	private Image icon;
	
	public EquipmentSlot(BodySlot first, BodySlot second, String path) {
		this.first = first;
		this.second = second;
		icon = Resources.loadImage(path);
	}

	public void draw() {
		icon.draw(first.screen_x, first.screen_y);
		first.draw();
		second.draw();
	}

}
