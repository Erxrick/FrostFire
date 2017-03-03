package games.indie.frostfire.states;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import games.indie.frostfire.FrostFire;
import games.indie.frostfire.Resource;
import games.indie.frostfire.multiplayer.GameClient;
import games.indie.frostfire.multiplayer.GameServer;
import games.indie.frostfire.multiplayer.Multiplayer;
import games.indie.frostfire.user.ui.Button;

public class StartMenu extends BasicGameState {
	
	private Button host, join;

	public void init(GameContainer gc, StateBasedGame game) throws SlickException {
		host = new Button(124, 48, "host-button");
		join = new Button(128, 80, "join-button");
	}

	public void render(GameContainer gc, StateBasedGame game, Graphics screen) throws SlickException {
		screen.scale(FrostFire.scale, FrostFire.scale);
		Resource.getImage("menu-background").draw();
		host.draw();
		join.draw();
	}

	public void update(GameContainer gc, StateBasedGame game, int delta) throws SlickException {
		if (gc.getInput().isKeyDown(Input.KEY_ENTER))
			game.enterState(GameState.GAMEPLAY);
	}

	public int getID() {
		return GameState.START_MENU;
	}
	
	@Override
	public void mousePressed(int button, int x, int y) {
		if (host.mousePressed(button, x/FrostFire.scale, y/FrostFire.scale)) {
			hostGame();
		} else if (join.mousePressed(button, x/FrostFire.scale, y/FrostFire.scale)) {
			joinGame("localhost");
		}
	}
	
	private void hostGame() {
		System.out.println("HOST GAME!");
		FrostFire.multiplayer = new Multiplayer(new GameServer(FrostFire.gameplay), new GameClient(FrostFire.gameplay, "localhost"));
	}
	
	private void joinGame(String ipAddress) {
		System.out.println("JOIN GAME!");
		FrostFire.multiplayer = new Multiplayer(new GameClient(FrostFire.gameplay, "localhost"));
	}

}
