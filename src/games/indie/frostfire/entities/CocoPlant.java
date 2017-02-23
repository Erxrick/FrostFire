package games.indie.frostfire.entities;

import games.indie.frostfire.Resource;

public class CocoPlant extends Entity {

	public CocoPlant() {
		setIcon(Resource.get("coconut-plant"));
		setCollision(2, -15, 10, 3);
	}

}
