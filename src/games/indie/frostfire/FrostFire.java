package games.indie.frostfire;

import org.newdawn.slick.*;
import org.newdawn.slick.state.StateBasedGame;

import games.indie.frostfire.states.*;

public class FrostFire extends StateBasedGame {
	
	private String title;
	private boolean running;
	public static final int FPS = 60;
	public static final int NATIVE_WIDTH = 320, NATIVE_HEIGHT = 180;
	public static int SCALE = 4;
	
	FrostFire() throws SlickException {
		super("FrostFire");
	}
	
	public void initStatesList(GameContainer gc) throws SlickException {
		gc.setMaximumLogicUpdateInterval(FPS);
		gc.setTargetFrameRate(FPS);
		gc.setAlwaysRender(true);
		gc.setShowFPS(false);
		gc.setVSync(true);
	//	gc.setMouseCursor("String", Mouse.getX(), Mouse.getY());
		
		this.addState(new Client());
		this.addState(new Menu());
	}

	public static void main(String[] args) throws SlickException {
		AppGameContainer game = new AppGameContainer(new FrostFire());
		game.setDisplayMode(NATIVE_WIDTH * SCALE, NATIVE_HEIGHT * SCALE, false);
		game.start();
	}

	
}
