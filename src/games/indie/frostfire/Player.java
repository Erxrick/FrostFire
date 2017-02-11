package games.indie.frostfire;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Input;

import games.indie.frostfire.states.GameState;

public class Player extends Human {
	
	public Player() {
	
	}

	public void updatePosition(GameContainer gc) {
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
