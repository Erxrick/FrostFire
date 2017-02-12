package games.indie.frostfire;

import org.lwjgl.input.Mouse;
import org.newdawn.slick.Input;

public class Player extends Human {
	
	private static Input input;
	private float speed = 0.8f;
	
	enum Control {
		MOVE_UP		(Input.KEY_UP, Input.KEY_W),
		MOVE_RIGHT	(Input.KEY_RIGHT, Input.KEY_D),
		MOVE_DOWN	(Input.KEY_DOWN, Input.KEY_S),
		MOVE_LEFT	(Input.KEY_LEFT, Input.KEY_A);
		
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
				move(45 ,speed);
			else if (Control.MOVE_DOWN.isPressed())
				move(315, speed);
			else
				move(0, speed);
		}
		else if (Control.MOVE_UP.isPressed()) {
			if (Control.MOVE_LEFT.isPressed())
				move(135, speed);
			else
				move(90, speed);
		}
		else if (Control.MOVE_LEFT.isPressed()) {
			if (Control.MOVE_DOWN.isPressed())
				move(225, speed);
			else
				move(180, speed);
		}
		else if (Control.MOVE_DOWN.isPressed()) {
			move(270, speed);
		}
	}
	
	public void setLocation(Coord location) {
		this.location = location;
		if (head != null) {
			head.sightAngle = this.location.directionTo(
			new Coord(Mouse.getX()/FrostFire.SCALE, FrostFire.NATIVE_HEIGHT - Mouse.getY()/FrostFire.SCALE));
		}
	}
	
}
