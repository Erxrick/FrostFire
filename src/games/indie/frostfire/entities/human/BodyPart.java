package games.indie.frostfire.entities.human;

import games.indie.frostfire.items.Item;

public class BodyPart {
	
	protected Item equipped;
	protected int zIndex;
	protected Human body;
	
	public BodyPart(Human human) {
		this.body = human;

	}
	
	public Human getBody() {
		return body;
	}

}
