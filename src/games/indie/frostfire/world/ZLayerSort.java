package games.indie.frostfire.world;

import java.util.Comparator;

import games.indie.frostfire.entities.Entity;

/**
 * 
 * @author Wesley Barlow
 *
 */
public class ZLayerSort implements Comparator<Entity> {

	public int compare(Entity e_0, Entity e_1) {
		return (int) (e_1.getY() - e_1.getHeight() - (e_0.getY() - e_0.getHeight()));
	}

}
