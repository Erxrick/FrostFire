package games.indie.frostfire.states;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import games.indie.frostfire.FrostFire;
import games.indie.frostfire.Resources;

public class Client extends GameState {

	public void init(GameContainer gc, StateBasedGame game) throws SlickException {
		
	}

	public void render(GameContainer gc, StateBasedGame game, Graphics screen) throws SlickException {
		debug(screen);
		screen.scale(FrostFire.SCALE, FrostFire.SCALE);
		Resources.loadImage("res/images/body0.png").draw();
	}

	public void update(GameContainer gc, StateBasedGame game, int delta) throws SlickException {
	
	}

	public int getID() {
		return CLIENT;
	}

}
