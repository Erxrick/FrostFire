package games.indie.frostfire;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Input;

import games.indie.frostfire.states.GameState;

public class Player extends Human {
	
	public Player() {
		
	}

	public void updatePosition(GameContainer gc) {	
		float speed = 0.8f;
		if (gc.getInput().isKeyDown(Input.KEY_W)) {
			if(gc.getInput().isKeyDown(Input.KEY_W) && gc.getInput().isKeyDown(Input.KEY_A)) {
				move((180-45),speed);
			} else if(gc.getInput().isKeyDown(Input.KEY_W) && gc.getInput().isKeyDown(Input.KEY_D)) {
				move((45),speed);
			} else {
				move(90, speed);
			}
		}
		else if (gc.getInput().isKeyDown(Input.KEY_S)) {
			if(gc.getInput().isKeyDown(Input.KEY_A) && gc.getInput().isKeyDown(Input.KEY_S)) {
				move((270-45),speed);
			} else if(gc.getInput().isKeyDown(Input.KEY_D) && gc.getInput().isKeyDown(Input.KEY_S)) {
				move((360-45),speed);
			} else {
				move(270, speed);
			}
		}
		else if (gc.getInput().isKeyDown(Input.KEY_A)) {
			move(180, speed);
		}
		else if (gc.getInput().isKeyDown(Input.KEY_D)) {
			move(0, speed);
		}			
	}
	
	
	
}
