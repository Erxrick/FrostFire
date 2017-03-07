package games.indie.frostfire.entities;

import games.indie.frostfire.Resource;

public class Tree extends Plant {
		
	public Tree() {
		setIcon(Resource.getImage("tree"));
		setCollision(4, -23, 24, 8);
	}
	
	public void die() {
		world.place(new TreeStump(), x + 8, y - 16);
		super.die();
	}
	
	public void interaction(Interactor hand) {
		Resource.getSound("hit-tree").playAsSoundEffect(1, 1, false);
	}

}
