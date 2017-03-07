package games.indie.frostfire.entities;

import games.indie.frostfire.Resource;
import games.indie.frostfire.items.Twig;

public class Bush extends Plant {
	
	public Bush() {
		setIcon(Resource.getImage("bush"));
		setCollision(2, -10, 12, 6);
	}
	
	public void die() {
		world.place(new Twig(), x, y);
		super.die();
	}

}
