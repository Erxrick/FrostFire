package games.indie.frostfire.entities.human;

import org.newdawn.slick.geom.Vector2f;

import games.indie.frostfire.items.Item;

public abstract class BodyPart {
	
	protected Item equipped;
	protected int zIndex;
	protected Vector2f offset;
	protected Human body;
	
	public BodyPart(Human body) {
		this.body = body;
	}
	
	public Human getBody() {
		return body;
	}

}
