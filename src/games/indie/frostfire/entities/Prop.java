package games.indie.frostfire.entities;

import org.newdawn.slick.Image;

import games.indie.frostfire.Resources;
import games.indie.frostfire.world.Camera;

public abstract class Prop extends Entity {
	
	private Image sprite;
	
	Prop(String path) {
		sprite = Resources.loadImage(path);
		setWidth(sprite.getWidth());
		setHeight(sprite.getHeight());
	}
	
	public void draw() {
		Camera.draw(sprite, location);
	}

}
