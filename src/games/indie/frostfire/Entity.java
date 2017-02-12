package games.indie.frostfire;

public abstract class Entity implements Drawable {
	
	protected Coord location;
	// TODO collision
	protected int width, height;
	
	public Entity() {
		setLocation(new Coord());
	}
	
	public Coord center() {
		return getLocation().midpoint(new Coord(getLocation().getX() + width, getLocation().getY() + height));
	}

	public void move(double angle, float distance) {
		setLocation(new Coord(((float)(this.getLocation().getX() + distance *Math.cos(Math.toRadians(angle)))), (float) (this.getLocation().getY() + -distance * Math.sin(Math.toRadians(angle)))));
	}

	public Coord getLocation() {
		return location;
	}

	public void setLocation(Coord location) {
		this.location = location;
	}

}
