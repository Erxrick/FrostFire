package games.indie.frostfire.items;

import org.newdawn.slick.Image;

import games.indie.frostfire.Resource;
import games.indie.frostfire.Sprite;

public abstract class Item extends Sprite implements StateChangeListener, Usable {
	
	private Image show; // Displayed during motion
	private int weight;
	
	public Item(int weight, String itemName) {
		this.weight = weight;
		icon = Resource.get(itemName);
		show = icon.copy();
	}
	
	public int getWeight() {
		return weight;
	}
	
	public Image getIcon() {
		return icon;
	}

}
