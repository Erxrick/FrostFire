package games.indie.frostfire.entities;

import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Vector2f;

import games.indie.frostfire.items.Inventory;
import games.indie.frostfire.items.Item;
import games.indie.frostfire.world.Direction;
import games.indie.frostfire.world.World;

public abstract class Creature extends Entity {
	
	protected Stat energy;
	protected Stat temperature;
	protected Inventory inventory;
	protected Direction direction;
	protected World world;
	
	public Creature() {
		this.direction = Direction.SOUTH;
	}
	
	public boolean move(Direction direction) {
		return move(direction.getVector());
	}

	public boolean move(Vector2f movement) {
		Rectangle moveTo = new Rectangle(
				collision.getX() + movement.getX(), 
				collision.getY() + movement.getY(), 
				collision.getWidth(), collision.getHeight());
		boolean validMove = world.isValidMove(this, moveTo);
		direction = Direction.four(movement.getTheta());
		if (validMove) {
			setLocation(x + movement.getX(), y + movement.getY());
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
	
	public Direction getDirection() {
		return direction;
	}

}
