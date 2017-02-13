package games.indie.frostfire.world;

import org.newdawn.slick.Renderable;

import games.indie.frostfire.FrostFire;

public class Camera {
	
	private Coord focus;
	
	public Camera(Coord startingFocus) {
		focus = startingFocus;
	}
	
	public void setFocus(Coord focus) {
		this.focus = focus;
	}
	
	public void display(Renderable sprite, float x, float y) {
		sprite.draw(x * FrostFire.SCALE, y * FrostFire.SCALE);
	}
	
}
