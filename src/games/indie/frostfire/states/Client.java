package games.indie.frostfire.states;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import games.indie.frostfire.*;

public class Client extends GameState {
	
	private Player player;

	public void init(GameContainer gc, StateBasedGame game) throws SlickException {
		player = new Player();
	}

	public void render(GameContainer gc, StateBasedGame game, Graphics screen) throws SlickException {
		screen.setBackground(new Color(44, 141, 144));
		screen.scale(FrostFire.SCALE, FrostFire.SCALE);
		
		player.draw();
	}

	public void update(GameContainer gc, StateBasedGame game, int delta) throws SlickException {
		player.updatePosition(gc);
		player.updateHead();
		if (gc.getInput().isKeyPressed(Input.KEY_ESCAPE))
			gc.exit();
	}

	public int getID() {
		return CLIENT;
	}

}
