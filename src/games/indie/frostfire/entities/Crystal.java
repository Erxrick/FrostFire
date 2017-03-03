package games.indie.frostfire.entities;

import games.indie.frostfire.Resource;

public class Crystal extends Entity {
	
	public Crystal() {
		setIcon(Resource.getImage("crystal"));
		setCollision(2, -10, 12, 6);
	}

}
