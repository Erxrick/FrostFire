package games.indie.frostfire;



import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;

public abstract class Entity implements Drawable {
	
	private Image sprite;
	protected Coord location;
	// TODO collision
	
	public Entity(String path) {
		sprite = Resources.loadImage(path);
		location = new Coord();
	}

	public void draw() {
		sprite.draw(location.getX(), location.getY());
	}

	

	public void move(double angle, float distance) {
		this.location.setLocation(((float)(this.location.getX() + distance *Math.cos(Math.toRadians(angle)))), (float) (this.location.getY() + -distance * Math.sin(Math.toRadians(angle))));
	}

}
