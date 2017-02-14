package games.indie.frostfire.entities;

import java.util.ArrayList;

import games.indie.frostfire.Drawable;
import games.indie.frostfire.entities.Action.ActionType;
import games.indie.frostfire.world.Coord;
import games.indie.frostfire.world.Direction;
import games.indie.frostfire.world.World;

public abstract class Entity implements Drawable, Comparable<Entity> {
	
	protected Coord location;
	protected Box collision;
	private int width, height;
	private ArrayList<EntityMoveListener> listeners;
	// World included so they can reference other entities for collision detection
	private World world;
	
	public Entity() {
		listeners = new ArrayList<>();
		setLocation(new Coord());
	}
	
	public Coord center() {
		return new Coord(location.getX() + width/2, location.getY() + height/2);
	}
	
	public boolean move(Direction direction, float distance) {
		// TODO temporary ActionListener --improve this hacky code
		((Human) this).setAction(ActionType.MOVE, direction);
		return move(direction.getAngle(), distance);
	}

	public boolean move(double degrees, float distance) {
		float x_component = (float) (Math.cos(Math.toRadians(degrees)) * distance);
		float y_component = (float) (Math.sin(Math.toRadians(degrees)) * distance);
		Coord moveTo = new Coord(location.getX() + x_component, location.getY() + y_component);
		boolean validMove = world.testMove(this, moveTo);
		if (validMove) {
			setLocation(moveTo);
			for (EntityMoveListener listener : listeners) {
				listener.moved(this);
			}
		}
		return validMove;
	}

	public Coord getLocation() {
		return location;
	}

	public void setLocation(Coord location) {
		this.location = location;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}
	
	public Box getCollision() {
		return collision;
	}
	
	public void setWorld(World world) {
		this.world = world;
	}
	
	public int compareTo(Entity entity) {
		return (int) ((entity.getLocation().getY() - entity.getHeight()) - (location.getY() - height));
	}

}
