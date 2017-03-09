package games.indie.frostfire;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import games.indie.frostfire.multiplayer.Multiplayer;
import games.indie.frostfire.states.Gameplay;
import games.indie.frostfire.states.StartMenu;

public class FrostFire extends StateBasedGame {
	
	public static final int FPS = 60;
	public static final int NATIVE_WIDTH = 320, NATIVE_HEIGHT = 180;
	public static int scale = 4;
	public static Multiplayer multiplayer;	
	public static Gameplay gameplay;
	public static FrostFire game;
	FrostFire() throws SlickException {
		super("FrostFire");
	}
	
	public void initStatesList(GameContainer gc) throws SlickException {
		gc.setMaximumLogicUpdateInterval(FPS);
		gc.setTargetFrameRate(FPS);
		gc.setAlwaysRender(true);
		gc.setShowFPS(false);
		gc.setVSync(true);
		
		Resource.load();
		gameplay = new Gameplay();
		this.addState(new StartMenu());
		this.addState(gameplay);
	}

	public static void main(String[] args) throws SlickException {
		game = new FrostFire();
		AppGameContainer gc = new AppGameContainer(game);
		gc.setDisplayMode(NATIVE_WIDTH * scale, NATIVE_HEIGHT * scale, false);
		gc.start();
	}

}
