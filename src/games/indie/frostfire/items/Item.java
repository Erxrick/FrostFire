package games.indie.frostfire.items;

import org.newdawn.slick.Image;

import games.indie.frostfire.Resource;
import games.indie.frostfire.Sprite;

public abstract class Item extends Sprite implements StateChangeListener, Usable {
	
	protected Image show; // Displayed during motion
	protected int weight;
	protected float angle;
	
	public Item(int weight, String itemName) {
		this.weight = weight;
		icon = Resource.get(itemName);
		show = icon.copy();
	}
	
	public Image getShow() {
		return show;
	}
	
	public int getWeight() {
		return weight;
	}

}
