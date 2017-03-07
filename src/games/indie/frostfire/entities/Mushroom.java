package games.indie.frostfire.entities;

import games.indie.frostfire.Resource;

public class Mushroom extends Plant {
	
	public Mushroom() {
		setIcon(Resource.getImage(("mushroom")));
		setCollision(2, -10, 12, 6);
	}

}
