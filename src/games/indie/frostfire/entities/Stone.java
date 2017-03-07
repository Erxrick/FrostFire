package games.indie.frostfire.entities;

import games.indie.frostfire.Resource;

public class Stone extends Entity {
	
	public Stone() {
		setIcon(Resource.getImage("stone"));
		setCollision(2, -10, 12, 6);
	}
	
	public void interaction(Interactor hand) {
		Resource.getSound("mine").playAsSoundEffect(1, 1, false);
	}

}
