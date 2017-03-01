package games.indie.frostfire.user.ui;

import org.newdawn.slick.Image;

import games.indie.frostfire.Drawable;
import games.indie.frostfire.Resource;

public class EquipmentSlot extends UIComponent implements Drawable {
	
	protected BodySlot first;
	protected BodySlot second;
	private Image icon;
	
	public EquipmentSlot(BodySlot first, BodySlot second, String name) {
		super(first.screen_x, first.screen_y);
		this.first = first;
		this.second = second;
		icon = Resource.getImage(name);
	}

	public void draw() {
		icon.draw(screen_x, screen_y);
		first.draw();
		second.draw();
	}
	
	public boolean mousePressed(int button, int x, int y) {
		return (first.mousePressed(button, x, y) || second.mousePressed(button, x, y));
	}

}
