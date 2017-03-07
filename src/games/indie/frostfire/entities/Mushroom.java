package games.indie.frostfire.entities;

import games.indie.frostfire.Resource;

public class Mushroom extends Plant {
	
	public Mushroom() {
		setIcon(Resource.getImage(("mushroom")));
		setCollision(2, -10, 12, 6);
	}
	
	public void interaction(Interactor hand) {
		Resource.getSound("hit-mushroom").playAsSoundEffect(1, 1, false);
	}

}
