package games.indie.frostfire.items;

import org.newdawn.slick.geom.Vector2f;

public abstract class Tool extends Item {
	
	protected Vector2f holdingOffset;
	protected int durability;

	public Tool(int weight, String path) {
		super(weight, path);
		this.durability = 100;
	}

}
