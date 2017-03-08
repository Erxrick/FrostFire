package games.indie.frostfire.items;

import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Vector2f;

import games.indie.frostfire.Resource;
import games.indie.frostfire.Sprite;
import games.indie.frostfire.world.Camera;

public abstract class Item extends Sprite implements StateChangeListener, Usable {
	
	protected Image show; // Displayed during motion
	protected int weight;
	protected float angle;
	protected Vector2f holdingOffset;
	
	protected int maxStackSize;
	
	protected float hover;
	protected boolean hoverUp;
	
	public Item(int weight, String itemName) {
		maxStackSize = 1;
		this.weight = weight;
		icon = Resource.getImage(itemName);
		setSize(icon.getWidth(), icon.getHeight());
		show = icon.copy();
		show.setCenterOfRotation(2, show.getHeight() - 2);
	}
	
	public float getHover() {
		if (hoverUp) {
			hover += .1;
		} else {
			hover -= .1;
		}
		if (hover >= 2) {
			hoverUp = false;
		} else if (hover <= -2) {
			hoverUp = true;
		}
		return hover;
	}
	
	public Image getShow() {
		return show;
	}
	
	public int getWeight() {
		return weight;
	}
	
	public void draw() {
		Camera.draw(show, x, y);
	}

}
