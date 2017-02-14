package games.indie.frostfire.states;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import games.indie.frostfire.FrostFire;
import games.indie.frostfire.entities.Tree;
import games.indie.frostfire.user.Player;
import games.indie.frostfire.user.UI;
import games.indie.frostfire.world.Camera;
import games.indie.frostfire.world.World;

public class Client extends GameState {
	
	private Player player;
	private Tree tree;
	private UI ui;
	private World world;

	public void init(GameContainer gc, StateBasedGame game) throws SlickException {
		player = new Player();
		world = new World();
		world.place(player);
		Camera.setCenter(player.center());
		ui = new UI();
	}

	public void render(GameContainer gc, StateBasedGame game, Graphics screen) throws SlickException {
		screen.setBackground(new Color(44, 141, 144));
		screen.scale(FrostFire.SCALE, FrostFire.SCALE);
		world.draw();
		if (FrostFire.debug)
			world.debug_draw(screen);
		ui.draw();
	}

	public void update(GameContainer gc, StateBasedGame game, int delta) throws SlickException {
		world.update();
		player.control(gc.getInput());
		player.updateHead();
		if (gc.getInput().isKeyPressed(Input.KEY_TAB))
			FrostFire.debug = (FrostFire.debug) ? false : true;
		if (gc.getInput().isKeyPressed(Input.KEY_ESCAPE))
			gc.exit();
	}

	public int getID() {
		return CLIENT;
	}

}
