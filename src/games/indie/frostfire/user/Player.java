package games.indie.frostfire.user;

import org.lwjgl.input.Mouse;
import org.newdawn.slick.Input;

import games.indie.frostfire.FrostFire;
import games.indie.frostfire.entities.Human;
import games.indie.frostfire.entities.Action.ActionType;
import games.indie.frostfire.world.Camera;
import games.indie.frostfire.world.Coord;
import games.indie.frostfire.world.Direction;

public class Player extends Human {
	
	private static Input input;
	private float speed;
	
	public Player() {
		speed = 0.9f;
	}
	
	enum Control {
		MOVE_UP		(Input.KEY_UP, Input.KEY_W),
		MOVE_RIGHT	(Input.KEY_RIGHT, Input.KEY_D),
		MOVE_DOWN	(Input.KEY_DOWN, Input.KEY_S), 
		MOVE_LEFT	(Input.KEY_LEFT, Input.KEY_A),
		INTERACT_RIGHT	(Input.KEY_E),
		INTERACT_LEFT	(Input.KEY_Q);
		
		private int[] keys;
		
		Control(int ...keys) {
			this.keys = keys;
		}
		
		public boolean isPressed() {
			for (int i = 0; i < keys.length; i++) {
				if (input.isKeyDown(keys[i]))
					return true;
			}
			return false;
		}
	}

	public void control(Input input) {
		Player.input = input;
		if (Control.MOVE_RIGHT.isPressed()) {
			if (Control.MOVE_UP.isPressed())
				move(Direction.NORTH_EAST ,speed);
			else if (Control.MOVE_DOWN.isPressed())
				move(Direction.SOUTH_EAST, speed);
			else
				move(Direction.EAST, speed);
		} else if (Control.MOVE_UP.isPressed()) {
			if (Control.MOVE_LEFT.isPressed())
				move(Direction.NORTH_WEST, speed);
			else
				move(Direction.NORTH, speed);
		} else if (Control.MOVE_LEFT.isPressed()) {
			if (Control.MOVE_DOWN.isPressed())
				move(Direction.SOUTH_WEST, speed);
			else
				move(Direction.WEST, speed);
		} else if (Control.MOVE_DOWN.isPressed()) {
			move(Direction.SOUTH, speed);
		} else {
			setAction(ActionType.IDLE, body.getDirection());
		}
		if (Control.INTERACT_LEFT.isPressed()) {
			System.out.println("Punch with LEFT_HAND");
		}
		if (Control.INTERACT_RIGHT.isPressed()) {
			System.out.println("Purnch with RIGHT_HAND");
		}
	}
	
	public void setLocation(Coord location) {
		this.location = location;
		Camera.setCenter(center());
		if (head != null) {
			updateHead();
		}
	}
	
	public void updateHead() {
		head.setSightAngle(Camera.onScreen(location).directionTo(
		new Coord(Mouse.getX()/FrostFire.SCALE, FrostFire.NATIVE_HEIGHT - Mouse.getY()/FrostFire.SCALE)));
	}
	
}
