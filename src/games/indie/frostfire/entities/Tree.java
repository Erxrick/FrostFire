package games.indie.frostfire.entities;

import org.newdawn.slick.Image;

import games.indie.frostfire.Resources;

public class Tree extends Entity {
	
	private Image sprite;
	
	public Tree() {
		sprite = Resources.loadImage("res/images/tree.png");
		setHeight(32);
		setWidth(32);
	}

	public void draw() {
		sprite.draw(location.getX(), location.getY());
	}

}
