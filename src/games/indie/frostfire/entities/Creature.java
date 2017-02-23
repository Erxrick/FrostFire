package games.indie.frostfire.entities;

import org.newdawn.slick.geom.Rectangle;

import games.indie.frostfire.items.Inventory;
import games.indie.frostfire.items.Item;
import games.indie.frostfire.world.Direction;
import games.indie.frostfire.world.World;

public abstract class Creature extends Entity {
	
	protected Stat energy;
	protected Stat temperature;
	protected Inventory inventory;
	protected World world;
	
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
	
	public void addToInventory(Item i) {
		inventory.add(i);
	}
	
	public void setWorld(World world) {
		this.world = world;
	}
	
	private void die() {
		// TODO drop inventory
	}

}
