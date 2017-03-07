package games.indie.frostfire.states;

import java.util.concurrent.ThreadLocalRandom;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import games.indie.frostfire.FrostFire;
import games.indie.frostfire.entities.Entity;
import games.indie.frostfire.entities.human.Action.ActionType;
import games.indie.frostfire.items.Axe;
import games.indie.frostfire.items.Consumable;
import games.indie.frostfire.items.ConsumableType;
import games.indie.frostfire.multiplayer.PlayerMP;
import games.indie.frostfire.multiplayer.packets.Packet02Move;
import games.indie.frostfire.user.ui.UIComponent;
import games.indie.frostfire.world.Camera;
import games.indie.frostfire.world.World;

public class Gameplay extends BasicGameState {
	
	private PlayerMP player;
	public World world;
	private boolean debugDraw = false;

	public Gameplay() {
		player = new PlayerMP(10, 10, ThreadLocalRandom.current().nextLong(123456), null, 0);		
	}
	
	public void init(GameContainer gc, StateBasedGame game) throws SlickException {
		world = new World();
		world.place(player, 0, 0);
		Camera.setCenter(player.getCenterX(), player.getMinY());
	}

	public void render(GameContainer gc, StateBasedGame game, Graphics screen) throws SlickException {
		screen.setBackground(new Color(44, 141, 144));
		screen.scale(FrostFire.scale, FrostFire.scale);
		screen.setLineWidth(FrostFire.scale);
		world.draw(screen);
		if (debugDraw) {
			for (Entity entity : world.getEntities()) {
				entity.debug_draw(screen);
			}
		}
		player.getUI().draw();
	}

	public void update(GameContainer gc, StateBasedGame game, int delta) throws SlickException {
		world.update();
		player.update(delta);
		player.control(gc.getInput());
	//	System.out.println(this.player.getAction());
		Packet02Move movePacket = new Packet02Move(this.player.getUsername(), this.player.getX(), this.player.getY(), this.player.getAction(), this.player.getDirection());
		movePacket.writeData(FrostFire.multiplayer.getClient());
		Camera.setCenter(player.getCenterX(), player.getMinY());
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
