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
import games.indie.frostfire.user.ui.UIComponent;
import games.indie.frostfire.world.World;

public class Gameplay extends BasicGameState {
	
	private Player player;
	private World world;
	private boolean debugDraw = false;
	
	public void init(GameContainer gc, StateBasedGame game) throws SlickException {
		world = new World();
		player = new Player();
		getWorld().place(player, 0, 0);
	}

	public void render(GameContainer gc, StateBasedGame game, Graphics screen) throws SlickException {
		
		screen.setBackground(new Color(44, 141, 144));
		screen.scale(FrostFire.SCALE, FrostFire.SCALE);
		screen.setLineWidth(FrostFire.SCALE);
		
		getWorld().draw(screen, debugDraw);
		
		player.getUI().draw();
	}

	public void update(GameContainer gc, StateBasedGame game, int delta) throws SlickException {
		
		if (gc.getInput().isKeyPressed(Input.KEY_ESCAPE)) {
			gc.exit();
		}
		
		getWorld().update(delta);
		player.control(gc.getInput());
		
		if (gc.getInput().isKeyPressed(Input.KEY_TAB)) {
			debugDraw = debugDraw ? false : true;
		}
	}
	
	public void mouseMoved(int oldx, int oldy, int newx, int newy) {
		player.updateHead();
	}
	
	public void mouseDragged(int oldx, int oldy, int newx, int newy) {
		player.updateHead();
	}
	
	public void mousePressed(int button, int x, int y) {
		boolean punch = true;
		for (UIComponent component : player.getUI()) {
			if (component.mousePressed(button, x/FrostFire.SCALE, y/FrostFire.SCALE)) {
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
	
	public void mouseReleased(int button, int x, int y) {
		if (player.getUI().getGrabbed() != null) {
			boolean shouldDrop = true;
			for (UIComponent component : player.getUI()) {
				if (component.mouseReleased(player.getUI(), x/FrostFire.SCALE, y/FrostFire.SCALE)) {
					player.getUI().grab(null);
					shouldDrop = false;
					break;
				}
			}
			if (shouldDrop) {
				player.getUI().dropGrabbed(player);
			}
		}
	}

	public int getID() {
		return GameState.GAMEPLAY;
	}

	public World getWorld() {
		return world;
	}

}
