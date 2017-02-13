package games.indie.frostfire.items;

import org.newdawn.slick.Image;

public abstract class Item {
	
	private Image sprite;
	private int weight;
	
	public Item(int weight) {
		this.weight = weight;
	}
	
	public int getWeight() {
		return weight;
	}

}
