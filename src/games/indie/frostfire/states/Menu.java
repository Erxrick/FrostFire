package games.indie.frostfire.states;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

public class Menu extends GameState {

	public void init(GameContainer gc, StateBasedGame game) throws SlickException {

	}

	public void render(GameContainer gc, StateBasedGame game, Graphics g) throws SlickException {
		debug(g);
	}

	public void update(GameContainer gc, StateBasedGame game, int delta) throws SlickException {
		if (gc.getInput().isKeyDown(Input.KEY_ENTER))
		game.enterState(GameState.CLIENT);
	}

	public int getID() {
		return MAIN_MENU;
	}
	

}
