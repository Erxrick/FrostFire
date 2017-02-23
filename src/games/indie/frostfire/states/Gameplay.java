package games.indie.frostfire.states;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import games.indie.frostfire.FrostFire;
import games.indie.frostfire.user.Player;
import games.indie.frostfire.world.Camera;
import games.indie.frostfire.world.World;

public class Gameplay extends BasicGameState {
	
	private Player player;
	private World world;

	public void init(GameContainer gc, StateBasedGame game) throws SlickException {
		player = new Player();
		world = new World();
		world.place(player);
		player.setWorld(world);
		Camera.setCenter(player.getCenterX(), player.getMinY());
	}

	public void render(GameContainer gc, StateBasedGame game, Graphics screen) throws SlickException {
		screen.setBackground(new Color(44, 141, 144));
		screen.scale(FrostFire.scale, FrostFire.scale);
		screen.setLineWidth(FrostFire.scale);
		world.draw();
	}

	public void update(GameContainer gc, StateBasedGame game, int delta) throws SlickException {
		world.update();
		player.control(gc.getInput());
		if (gc.getInput().isKeyPressed(Input.KEY_ESCAPE))
			gc.exit();
	}
	
	@Override
	public void mouseMoved(int oldx, int oldy, int newx, int newy) {
		player.updateHead();
	}

	public int getID() {
		return GameState.GAMEPLAY;
	}

}
