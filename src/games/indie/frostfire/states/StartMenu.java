package games.indie.frostfire.states;

import org.newdawn.slick.AngelCodeFont;
import org.newdawn.slick.Color;
import org.newdawn.slick.Font;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.gui.TextField;
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
	private Font text;
	private TextField entry;

	public void init(GameContainer gc, StateBasedGame game) throws SlickException {
		host = new Button(124, 48, "host-button");
		join = new Button(128, 80, "join-button");
		text = new AngelCodeFont("res/input.fnt", Resource.loadImage("res/input_0.png"));
		entry = new TextField(gc, text, 120, 114, 80, 16);
		entry.setBackgroundColor(null);
		entry.setBorderColor(null);
		entry.setCursorVisible(true);
		entry.setFocus(true);
	}

	public void render(GameContainer gc, StateBasedGame game, Graphics screen) throws SlickException {
		screen.scale(FrostFire.scale, FrostFire.scale);
		Resource.getImage("menu-background").draw();
		host.draw();
		join.draw();
		screen.setColor(new Color(95, 84, 102));
		Resource.getImage("text-entry").draw(111, 111);
		entry.render(gc, screen);
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
			hostGame(entry.getText());
		} else if (join.mousePressed(button, x/FrostFire.scale, y/FrostFire.scale)) {
			joinGame(entry.getText());
		}
	}
	
	private void hostGame(String seed) {
		System.out.println("HOST GAME!");
		System.out.println(seed);
		FrostFire.multiplayer = new Multiplayer(new GameServer(FrostFire.gameplay), new GameClient(FrostFire.gameplay, "localhost"));
	}
	
	private void joinGame(String ipAddress) {
		System.out.println("JOIN GAME!");
		System.out.println(ipAddress);
		FrostFire.multiplayer = new Multiplayer(new GameClient(FrostFire.gameplay, ipAddress));
	}

}
