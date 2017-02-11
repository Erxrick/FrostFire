package games.indie.frostfire;

import org.newdawn.slick.*;
import org.newdawn.slick.state.StateBasedGame;

import games.indie.frostfire.states.*;

public class FrostFire extends StateBasedGame {
	
	private String title;
	private boolean running;
	public static final int FPS = 60;
	
	FrostFire() throws SlickException {
		super("FrostFire");
	}
	
	public void initStatesList(GameContainer gc) throws SlickException {
		gc.setMaximumLogicUpdateInterval(FPS);
		gc.setTargetFrameRate(FPS);
		gc.setAlwaysRender(true);
		gc.setShowFPS(false);
		gc.setVSync(true);
		
		this.addState(new Menu());
		this.addState(new Client());
	}

	public static void main(String[] args) throws SlickException {
		AppGameContainer game = new AppGameContainer(new FrostFire());
		game.setDisplayMode(640, 320, false);
		game.start();
	}

	
}