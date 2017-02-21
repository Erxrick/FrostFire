package games.indie.frostfire.entities;

import org.newdawn.slick.geom.Rectangle;

import games.indie.frostfire.items.Inventory;
import games.indie.frostfire.items.Item;
import games.indie.frostfire.world.Direction;
import games.indie.frostfire.world.World;

public abstract class Creature extends Entity {
	
	private int health, maxHealth;
	private int energy, maxEnergy;
	private int temperature, coldBound, hotBound;
	private int defence;
	protected Inventory inventory;
	protected World world;
	
	public Creature(int maxHealth, int maxEnergy) {
		this.maxHealth = maxHealth;
		this.maxEnergy = maxEnergy;
		this.coldBound = 0; // Celcius
		this.hotBound = 35;
		init();
	}
	
	private void init() {
		health = maxHealth;
		energy = maxEnergy;
	}
	
	public boolean move(Direction direction, float distance) {
		return move(direction.getAngle(), distance);
	}

	public boolean move(double degrees, float distance) {
		float x_component = (float) (Math.cos(Math.toRadians(degrees)) * distance);
		float y_component = (float) (Math.sin(Math.toRadians(degrees)) * distance);
		Rectangle moveTo = new Rectangle(collision.getX() + x_component, collision.getY() + y_component, collision.getWidth(), collision.getHeight());
		boolean validMove = world.isValidMove(this, moveTo);
		if (validMove) {
			setLocation(x + x_component, y + y_component);
		}
		return validMove;
	}
	
	public void setTemperatureBounds(int coldBound, int hotBound) {
		this.coldBound = coldBound;
		this.hotBound = hotBound;
	}
	
	public void addToInventory(Item i) {
		inventory.add(i);
	}
	
	public void takeDamage(int damage) {
		
	}
	
	public void setCurrentHealth(int health) {
		this.health = health;
		if (this.health <= 0) {
			die();
		}
	}
	
	public void setWorld(World world) {
		this.world = world;
	}
	
	private void die() {
		// TODO drop inventory
	}

}
