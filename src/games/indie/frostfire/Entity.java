package games.indie.frostfire;


import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Input;

public abstract class Entity implements Drawable {
	
	protected Coord location;
	protected int width, height;
	// TODO collision
	
	public Entity() {
		location = new Coord();
	}
	
	public Coord center() {
		return location.midpoint(new Coord(location.getX() + width, location.getY() + height));
	}

	

	public void setPosition(GameContainer gc) {
		if (gc.getInput().isKeyDown(Input.KEY_W)) {
			this.location.setY((this.location.getY() + 1));
		}
		if (gc.getInput().isKeyDown(Input.KEY_A)) {
			this.location.setX((this.location.getX() - 1));
		}
		if (gc.getInput().isKeyDown(Input.KEY_S)) {
			this.location.setY((this.location.getY() - 1));
		}
		if (gc.getInput().isKeyDown(Input.KEY_D)) {
			this.location.setX((this.location.getX() + 1));
		}
	}
}
