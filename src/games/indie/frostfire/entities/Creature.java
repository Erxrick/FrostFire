package games.indie.frostfire.entities;

import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Vector2f;

import games.indie.frostfire.entities.stats.Stat;
import games.indie.frostfire.items.*;
import games.indie.frostfire.world.Direction;

public abstract class Creature extends Entity {
	
	protected Stat energy;
	protected Stat temperature;
	protected Inventory inventory;
	protected Direction direction;
	
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
	
	public void setDirection(Direction direction) {
		this.direction = direction;
	}
	
	public Direction getDirection() {
		return direction;
	}
	
	public void consume(ConsumableType consumable) {
		health.affect(consumable.getHealthChange());
	}

}
