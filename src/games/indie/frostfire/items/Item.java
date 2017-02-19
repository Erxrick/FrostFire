package games.indie.frostfire.items;

import org.newdawn.slick.Image;

import games.indie.frostfire.Resource;
import games.indie.frostfire.world.Coord;

public abstract class Item implements StateChangeListener, Usable {
	
	private Image icon, sprite;
	private int weight;
	private Coord location;
	
	public Item(int weight, String itemName) {
		this.weight = weight;
		icon = Resource.get(itemName);
		sprite = icon.copy();
	}
	
	public int getWeight() {
		return weight;
	}
	
	public Image getIcon() {
		return icon;
	}

}
