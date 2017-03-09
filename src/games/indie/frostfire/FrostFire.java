package games.indie.frostfire;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import games.indie.frostfire.states.*;

public class FrostFire extends StateBasedGame {
	
	public static final int FPS = 60;
	public static final int NATIVE_WIDTH = 320, NATIVE_HEIGHT = 180;
	public static int scale = 8;
	
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
		this.addState(new StartMenu());
		this.addState(new Gameplay());
	}

	public static void main(String[] args) throws SlickException {
		AppGameContainer game = new AppGameContainer(new FrostFire());
		game.setDisplayMode(NATIVE_WIDTH * scale, NATIVE_HEIGHT * scale, true);
		game.start();
	}

}
