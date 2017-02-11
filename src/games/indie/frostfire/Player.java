package games.indie.frostfire;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Input;

import games.indie.frostfire.states.GameState;

public class Player extends Human {
	
	public Player() {
		
	}

	public void updatePosition(GameContainer gc) {
		if (gc.getInput().isKeyDown(Input.KEY_W)) {
			move(90, 1);
		}
		if (gc.getInput().isKeyDown(Input.KEY_A)) {
			move(180, 1);
		}
		if (gc.getInput().isKeyDown(Input.KEY_S)) {
			move(270, 1);
		}
		if (gc.getInput().isKeyDown(Input.KEY_D)) {
			move(0, 1);
		}
		
		
			
	}
	
	
	
}
