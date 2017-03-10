package games.indie.frostfire.entities;

import games.indie.frostfire.Resource;
import games.indie.frostfire.entities.stats.Health;
import games.indie.frostfire.items.Consumable;
import games.indie.frostfire.items.ConsumableType;

public class Mushroom extends Plant {
	
	public Mushroom() {
		setIcon(Resource.getImage(("mushroom")));
		setCollision(2, -10, 12, 6);
		setHealth(new Health(this, 0, 10));
	}
	
	public void interaction(Interactor hand) {
		world.place(new Consumable(ConsumableType.MUSHROOM), x, y);
		Resource.getSound("hit-mushroom").playAsSoundEffect(1, 1, false);
	}

}
