package games.indie.frostfire.entities;

import games.indie.frostfire.Resource;
import games.indie.frostfire.items.Twig;

public class Tree extends Plant {
		
	public Tree() {
		setIcon(Resource.getImage("tree"));
		setCollision(4, -23, 24, 8);
	}
	
	public void die() {
		world.place(new Twig(), x, y);
		super.die();
	}

}
