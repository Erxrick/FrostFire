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
