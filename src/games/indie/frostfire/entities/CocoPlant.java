package games.indie.frostfire.entities;

import games.indie.frostfire.Resource;

public class CocoPlant extends Entity {

	public CocoPlant() {
		setIcon(Resource.getImage("coconut-plant"));
		setCollision(2, -12, 10, 6);
	}

}
