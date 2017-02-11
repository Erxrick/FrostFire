package games.indie.frostfire;



import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;

public abstract class Entity implements Drawable {
	
	protected Coord location;	// TODO collision
	protected int width, height;
	public Entity() {
		location = new Coord();
	}
	
	public Coord center() {
		return location.midpoint(new Coord(location.getX() + width, location.getY() + height));
	}

	

	public void move(double angle, float distance) {
		this.location.setLocation(((float)(this.location.getX() + distance *Math.cos(Math.toRadians(angle)))), (float) (this.location.getY() + -distance * Math.sin(Math.toRadians(angle))));
	}

}
