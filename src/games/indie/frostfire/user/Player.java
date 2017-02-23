package games.indie.frostfire.user;

import org.lwjgl.input.Mouse;
import org.newdawn.slick.Input;
import org.newdawn.slick.geom.Vector2f;

import games.indie.frostfire.FrostFire;
import games.indie.frostfire.entities.human.Human;
import games.indie.frostfire.entities.human.Action.ActionType;
import games.indie.frostfire.world.Camera;
import games.indie.frostfire.world.Direction;

public class Player extends Human {
	
	protected UI ui;
	
	public Player() {
		ui = new UI(this);
	}
	
	public void control(Input input) {
		if (input.isKeyDown(Input.KEY_E))
			rightHand.punch();
		if (input.isKeyDown(Input.KEY_Q))
			leftHand.punch();
		
		Vector2f movement = new Vector2f();
		if (input.isKeyDown(Input.KEY_W))
			movement.add(Direction.NORTH.getVector());
		if (input.isKeyDown(Input.KEY_A))
			movement.add(Direction.WEST.getVector());
		if (input.isKeyDown(Input.KEY_S))
			movement.add(Direction.SOUTH.getVector());
		if (input.isKeyDown(Input.KEY_D))
			movement.add(Direction.EAST.getVector());
		if (movement.equals(new Vector2f())) {
			setAction(ActionType.IDLE, direction);
		} else {
			move(movement.getNormal());
			setAction(ActionType.MOVE, movement.getTheta());
		}
	}
	
	public void setLocation(float x, float y) {
		super.setLocation(x, y);
		Camera.setCenter(getCenterX(), minY);
	}
	
	public void updateHead() {
		Vector2f position = Camera.onScreen(getCenterX(), getCenterY());
		head.setSightAngle(new Vector2f(
				position.getX() - Mouse.getX()/FrostFire.scale, 
				position.getY() - Mouse.getY()/FrostFire.scale).getTheta() - 180);
	}
	
	public UI getUI() {
		return ui;
	}
	
}
