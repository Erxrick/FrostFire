package games.indie.frostfire;

public abstract class Entity implements Drawable {
	
	protected Coord location;
	private int width, height;
	// TODO collision
	
	public Entity() {
		location = new Coord();
	}
	
	public Coord center() {
		return location.midpoint(new Coord(location.getX() + width, location.getY() + height));
	}

}
