package games.indie.frostfire.states;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class StartMenu extends BasicGameState {

	public void init(GameContainer gc, StateBasedGame game) throws SlickException {

	}

	public void render(GameContainer gc, StateBasedGame game, Graphics g) throws SlickException {
	}

	public void update(GameContainer gc, StateBasedGame game, int delta) throws SlickException {
		if (gc.getInput().isKeyDown(Input.KEY_ENTER))
			game.enterState(GameState.GAMEPLAY);
	}

	public int getID() {
		return GameState.START_MENU;
	}

	

}
