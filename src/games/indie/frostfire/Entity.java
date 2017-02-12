package games.indie.frostfire;

import games.indie.frostfire.Action.ActionType;

public abstract class Entity implements Drawable {
	
	protected Coord location;
	protected Box collision;
	protected int width, height;
	
	public Entity() {
		setLocation(new Coord());
	}
	
	public Coord center() {
		return getLocation().midpoint(new Coord(getLocation().getX() + width, getLocation().getY() + height));
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

}
