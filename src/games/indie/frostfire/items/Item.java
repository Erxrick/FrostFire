package games.indie.frostfire.items;

import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Vector2f;

import games.indie.frostfire.Resource;
import games.indie.frostfire.Sprite;

public abstract class Item extends Sprite implements StateChangeListener, Usable {
	
	protected Image show; // Displayed during motion
	protected int weight;
	protected float angle;
	protected Vector2f holdingOffset;
	
	public Item(int weight, String itemName) {
		this.weight = weight;
		icon = Resource.get(itemName);
		show = icon.copy();
		show.setCenterOfRotation(2, show.getHeight() - 2);
	}
	
	public Image getShow() {
		return show;
	}
	
	public int getWeight() {
		return weight;
	}

}
