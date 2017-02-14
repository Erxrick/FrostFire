package games.indie.frostfire.states;

import java.util.ArrayList;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import games.indie.frostfire.FrostFire;
import games.indie.frostfire.entities.Entity;
import games.indie.frostfire.entities.Tree;
import games.indie.frostfire.user.Player;
import games.indie.frostfire.user.UI;
import games.indie.frostfire.world.Camera;
import games.indie.frostfire.world.Coord;

public class Client extends GameState {
	
	private Player player;
	private Tree tree;
	private ArrayList<Entity> entities;
	private UI display;

	public void init(GameContainer gc, StateBasedGame game) throws SlickException {
		player = new Player();
		tree = new Tree();
		tree.setLocation(new Coord(80, 80));
		entities = new ArrayList<>();
		entities.add(player);
		entities.add(tree);
		Camera.setCenter(player.center());
		display = new UI();
	}

	public void render(GameContainer gc, StateBasedGame game, Graphics screen) throws SlickException {
		screen.setBackground(new Color(44, 141, 144));
		screen.scale(FrostFire.SCALE, FrostFire.SCALE);
		for (Entity entity : entities) {
			entity.draw();
		}
		if (FrostFire.debug) {
			for (Entity entity : entities) {
				Coord position = Camera.onScreen(entity.getLocation());
				screen.drawRect(position.getX(), position.getY(), entity.getWidth(), entity.getHeight());
			}
		}
		display.draw();
	}

	public void update(GameContainer gc, StateBasedGame game, int delta) throws SlickException {
		player.control(gc.getInput());
		player.updateHead();
		entities.sort(null);
		if (gc.getInput().isKeyPressed(Input.KEY_TAB))
			FrostFire.debug = (FrostFire.debug) ? false : true;
		if (gc.getInput().isKeyPressed(Input.KEY_ESCAPE))
			gc.exit();
	}

	public int getID() {
		return CLIENT;
	}

}
