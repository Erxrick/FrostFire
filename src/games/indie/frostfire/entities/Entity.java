package games.indie.frostfire.entities;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Vector2f;

import games.indie.frostfire.Sprite;
import games.indie.frostfire.entities.stats.Health;
import games.indie.frostfire.world.Box;
import games.indie.frostfire.world.Camera;
import games.indie.frostfire.world.World;

public abstract class Entity extends Sprite {
	
	// x and y of collision relative to this location
	protected Box collision;
	protected Health health;
	protected float direction;
	protected World world;
	
	public Entity() {
		health = new Health(this, 0, 100);
	}
	
	public World getWorld() {
		return world;
	}
	
	public void setWorld(World world) {
		this.world = world;
	}
	
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
	
	public void die() {
		world.getEntities().remove(this);
		//wriite a packet here for the server to know
		System.out.println(this + " DIED!");
	}
	
	public void takeDamage(double damage) {
		health.affect(-damage);
	}

}
