package games.indie.frostfire;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

public class Resources {
	
	public static Image loadImage(String path) {
		try {
			return new Image(path, false, Image.FILTER_NEAREST);
		} catch (SlickException e) {
			// TODO load placeholder image
			return null;
		}
	}

}
