package games.indie.frostfire.entities;

import games.indie.frostfire.Drawable;
import games.indie.frostfire.entities.Action.ActionType;
import games.indie.frostfire.world.Coord;
import games.indie.frostfire.world.Direction;

public abstract class Entity implements Drawable {
	
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
		((Human) this).setAction(ActionType.MOVE, direction);
		move(direction.getAngle(), distance);
	}

	public void move(double degrees, float distance) {
		setLocation(new Coord(((float)(this.getLocation().getX() + distance *Math.cos(Math.toRadians(degrees)))), (float) (this.getLocation().getY() + -distance * Math.sin(Math.toRadians(degrees)))));
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

}
