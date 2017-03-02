package games.indie.frostfire.entities;

import games.indie.frostfire.Resource;

public class Tree extends Plant {
		
	public Tree() {
		setIcon(Resource.getImage("tree"));
		setCollision(4, -23, 24, 8);
	}

}
