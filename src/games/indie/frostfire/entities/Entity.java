package games.indie.frostfire.entities;

import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;

import games.indie.frostfire.Sprite;
import games.indie.frostfire.world.Camera;

public abstract class Entity extends Sprite {
	
	// x and y of collision relative to this.location
	protected Shape collision;
	protected float offset_x, offset_y;
	
	public void setCollision(float width, float height, float offset_x, float offset_y) {
		this.offset_x = offset_x;
		this.offset_y = offset_y;
		collision = new Rectangle(x + offset_x, y + offset_y, width, height);
	}
	
	public Shape getCollision() {
		return collision;
	}
	
	@Override
	public void setLocation(float x, float y) {
		super.setLocation(x, y);
		collision.setLocation(x + offset_x, y + offset_y);
	}
	
	public void draw() {
		Camera.draw(icon, getX(), getY());
	}
	
	public String toString() {
		return this.getClass().getSimpleName() + " at " + getLocation();
	}
	

}
