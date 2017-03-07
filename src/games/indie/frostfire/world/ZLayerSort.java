package games.indie.frostfire.world;

import java.util.Comparator;

import games.indie.frostfire.Sprite;

/**
 * 
 * @author Wesley Barlow
 *
 */
public class ZLayerSort implements Comparator<Sprite> {

	public int compare(Sprite s_0, Sprite s_1) {
		return (int) (s_1.getY() - s_1.getHeight() - (s_0.getY() - s_0.getHeight()));
	}

}
