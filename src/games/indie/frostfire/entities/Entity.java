package games.indie.frostfire.entities;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Vector2f;

import games.indie.frostfire.Sprite;
import games.indie.frostfire.entities.stats.Health;
import games.indie.frostfire.world.Box;
import games.indie.frostfire.world.World;

public abstract class Entity extends Sprite {
	
	// x and y of collision relative to this.getLocation()
	protected Box collision;
	
	protected Health health;
	protected float direction;
	protected World world;
	
	public Entity() {
		setHealth(new Health(this, 0, 100));
	}
	
	/**
	 * An optional override that allows entities to be time aware
	 */
	public void update(int delta) {
	}
	
	/**
	 * An optional override that allows entities to react to interaction events
	 * Entities should generally respond by playing a sound and changing visually
	 */
	public void interaction(Interactor hand) {
	}
	
	public World getWorld() {
		return world;
	}
	
	public void setWorld(World world) {
		this.world = world;
	}
	
	public void draw() {
		world.camera.draw(icon, x, y);
	}
	
	public String toString() {
		return this.getClass().getSimpleName() + " at " + getLocation();
	}
	
	public void debug_draw(Graphics screen) {
		screen.setColor(new Color(255, 255, 255, 30));
		Vector2f position = world.camera.onScreen(x, y);
		screen.fillRect(position.getX(), position.getY(), width, height);
		screen.setColor(new Color(200, 100, 100, 150));
		position = world.camera.onScreen(collision.getX(), collision.getY());
		screen.fillRect(position.getX(), position.getY(), collision.getWidth(), collision.getHeight());
	}

	public Box getCollision() {
		return collision;
	}

	public void setCollision(float offset_x, float offset_y, float width, float height) {
		collision = new Box(this, offset_x, offset_y, width, height);
	}
	
	public void die() {
		world.remove(this);
	}
	
	public void takeDamage(double damage) {
		getHealth().affect(-damage);
	}

	public Health getHealth() {
		return health;
	}

	public void setHealth(Health health) {
		this.health = health;
	}

}
