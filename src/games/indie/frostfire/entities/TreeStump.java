package games.indie.frostfire.entities;

import games.indie.frostfire.Resource;

public class TreeStump extends Entity {
	
	public TreeStump() {
		setIcon(Resource.getImage("tree-stump"));
		setCollision(2, -10, 12, 6);
	}

}
