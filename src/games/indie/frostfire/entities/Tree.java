package games.indie.frostfire.entities;

import org.newdawn.slick.Image;

import games.indie.frostfire.Resources;
import games.indie.frostfire.world.Camera;

public class Tree extends Entity {
	
	private Image sprite;
	
	public Tree() {
		sprite = Resources.loadImage("res/images/tree.png");
		setHeight(32);
		setWidth(32);
	}

	public void draw() {
		Camera.draw(sprite, location);
	}

}
