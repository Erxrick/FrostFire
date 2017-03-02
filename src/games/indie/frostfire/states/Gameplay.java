package games.indie.frostfire.states;

import java.util.UUID;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import games.indie.frostfire.FrostFire;
import games.indie.frostfire.entities.Entity;
import games.indie.frostfire.items.Axe;
import games.indie.frostfire.items.Consumable;
import games.indie.frostfire.items.ConsumableType;
import games.indie.frostfire.multiplayer.PlayerMP;

import games.indie.frostfire.user.ui.UIComponent;
import games.indie.frostfire.world.Camera;
import games.indie.frostfire.world.World;

public class Gameplay extends BasicGameState {
	
	private PlayerMP player;
	public World world;
	private boolean debugDraw = false;

	public void init(GameContainer gc, StateBasedGame game) throws SlickException {
		player = new PlayerMP(10, 10, new UUID(0, 0), null, 0);
		world = new World();
		world.place(player);
		Camera.setCenter(player.getCenterX(), player.getMinY());
	}

	public void render(GameContainer gc, StateBasedGame game, Graphics screen) throws SlickException {
		screen.setBackground(new Color(44, 141, 144));
		screen.scale(FrostFire.scale, FrostFire.scale);
		screen.setLineWidth(FrostFire.scale);
		world.draw();
		if (debugDraw) {
			for (Entity entity : world.entities) {
				entity.debug_draw(screen);
			}
		}
		player.getUI().draw();
	}

	public void update(GameContainer gc, StateBasedGame game, int delta) throws SlickException {
		world.update();
		player.update(delta);
		player.control(gc.getInput());
		if (gc.getInput().isKeyPressed(Input.KEY_ESCAPE))
			gc.exit();
		if (gc.getInput().isKeyPressed(Input.KEY_Z)) {
			player.getRightHand().pickup(new Consumable(ConsumableType.STRAWBERRY));
			player.getLeftHand().pickup(new Axe());
		}
		if (gc.getInput().isKeyPressed(Input.KEY_TAB)) {
			debugDraw = debugDraw ? false : true;
		}
	}
	
	@Override
	public void mouseMoved(int oldx, int oldy, int newx, int newy) {
		player.updateHead();
	}
	
	@Override
	public void mousePressed(int button, int x, int y) {
		boolean punch = true;
		for (UIComponent component : player.getUI()) {
			if (component.mousePressed(button, x/FrostFire.scale, y/FrostFire.scale)) {
				punch = false;
				break;
			}
		}
		if (punch) {
			if (button == 0)
				player.getLeftHand().punch();
			else if (button == 1)
				player.getRightHand().punch();
		}
	}

	public int getID() {
		return GameState.GAMEPLAY;
	}

	public PlayerMP getPlayer() {
		return this.player;
	}

}
