package games.indie.frostfire.entities;

import games.indie.frostfire.Resource;

public class CocoPlant extends Entity {

	public CocoPlant() {
		setIcon(Resource.get("coconut-plant"));
		setCollision(10, 3, 2, -15);
	}

}
