package games.indie.frostfire.entities;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Vector2f;

import games.indie.frostfire.Sprite;
import games.indie.frostfire.world.Box;
import games.indie.frostfire.world.Camera;

public abstract class Entity extends Sprite {
	
	// x and y of collision relative to this location
	protected Box collision;
	protected Stat health;
	protected float direction;
	
	public void draw() {
		Camera.draw(icon, x, y);
	}
	
	public String toString() {
		return this.getClass().getSimpleName() + " at " + getLocation();
	}
	
	public void debug_draw(Graphics screen) {
		screen.setColor(new Color(255, 255, 255, 30));
		Vector2f position = Camera.onScreen(x, y);
		screen.fillRect(position.getX(), position.getY(), width, height);
		screen.setColor(new Color(200, 100, 100, 150));
		position = Camera.onScreen(collision.getX(), collision.getY());
		screen.fillRect(position.getX(), position.getY(), collision.getWidth(), collision.getHeight());
	}

	public Box getCollision() {
		return collision;
	}

	public void setCollision(float offset_x, float offset_y, float width, float height) {
		collision = new Box(this, offset_x, offset_y, width, height);
	}

}
