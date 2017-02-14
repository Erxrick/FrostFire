package games.indie.frostfire.entities;

import games.indie.frostfire.Drawable;
import games.indie.frostfire.Layer;
import games.indie.frostfire.entities.Action.ActionType;
import games.indie.frostfire.world.Coord;
import games.indie.frostfire.world.Direction;

public abstract class Entity implements Drawable, Comparable<Entity> {
	
	protected Coord location;
	protected Box collision;
	private int width;
	private int height;
	
	public Entity() {
		setLocation(new Coord());
	}
	
	public Coord center() {
		return getLocation().midpoint(new Coord(getLocation().getX() + getWidth(), getLocation().getY() + getHeight()));
	}
	
	public void move(Direction direction, float distance) {
		// TODO temporary ActionListener --improve this hacky code
		((Human) this).setAction(ActionType.MOVE, direction);
		move(direction.getAngle(), distance);
	}

	public void move(double degrees, float distance) {
		float x_component = (float) (Math.cos(Math.toRadians(degrees)) * distance);
		float y_component = (float) (Math.sin(Math.toRadians(degrees)) * distance);
		setLocation(new Coord(location.getX() + x_component, location.getY() + y_component));
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
	
	public Layer getLayer() {
		return Layer.ENTITY;
	}
	
	public int compareTo(Entity entity) {
		return (int) ((entity.getLocation().getY() - entity.getHeight()) - (location.getY() - height));
	}

}
