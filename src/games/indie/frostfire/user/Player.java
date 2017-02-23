package games.indie.frostfire.user;

import org.lwjgl.input.Mouse;
import org.newdawn.slick.Input;
import org.newdawn.slick.geom.Vector2f;

import games.indie.frostfire.FrostFire;
import games.indie.frostfire.entities.human.Human;
import games.indie.frostfire.world.Camera;

public class Player extends Human {
	
	protected UI ui;
	
	public Player() {
		ui = new UI(this);
	}
	
	public void control(Input input) {
		// TODO movement
		// TODO interaction events
	}
	
	public void setLocation(float x, float y) {
		setLocation(x, y);
		Camera.setCenter(getCenterX(), minY);
	}
	
	public void updateHead() {
		Vector2f position = Camera.onScreen(maxX, maxY);
		head.setSightAngle(new Vector2f(
				position.getX() - Mouse.getX()/FrostFire.scale, 
				position.getY() - Mouse.getY()/FrostFire.scale).getTheta());
	}
	
	
}
