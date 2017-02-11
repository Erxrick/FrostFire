package games.indie.frostfire;

import org.newdawn.slick.Image;

public abstract class Entity implements Drawable {
	
	private Image sprite;
	private Coord location;
	// TODO collision
	
	public Entity(String path) {
		sprite = Resources.loadImage(path);
		location = new Coord();
	}

	public void draw() {
		sprite.draw(location.getX(), location.getY());
	}

}
