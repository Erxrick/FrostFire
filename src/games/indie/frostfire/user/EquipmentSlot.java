package games.indie.frostfire.user;

import org.newdawn.slick.Image;

import games.indie.frostfire.Drawable;
import games.indie.frostfire.Resource;

public class EquipmentSlot implements Drawable {
	
	protected BodySlot first;
	protected BodySlot second;
	private Image icon;
	
	public EquipmentSlot(BodySlot first, BodySlot second, String name) {
		this.first = first;
		this.second = second;
		icon = Resource.get(name);
	}

	public void draw() {
		icon.draw(first.screen_x, first.screen_y);
		first.draw();
		second.draw();
	}

}
