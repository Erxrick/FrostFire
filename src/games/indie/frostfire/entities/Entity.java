package games.indie.frostfire.entities;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.geom.Vector2f;

import games.indie.frostfire.Sprite;
import games.indie.frostfire.world.Camera;

public abstract class Entity extends Sprite {
	
	// x and y of collision relative to this.location
	protected Rectangle collision;
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
	
	public void debug_draw(Graphics screen) {
		screen.setColor(Color.green);
		Vector2f onScreen = Camera.onScreen(x, y);
		screen.drawRect(onScreen.getX(), onScreen.getY(), width, height);
		screen.setColor(Color.red);
		onScreen = Camera.onScreen(collision.getX(), collision.getY());
		screen.drawRect(onScreen.getX(), onScreen.getY(), collision.getWidth(), collision.getHeight());
	}
	

}
