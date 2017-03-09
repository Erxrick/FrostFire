package games.indie.frostfire.states;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import games.indie.frostfire.FrostFire;
import games.indie.frostfire.Resource;
import games.indie.frostfire.user.ui.Button;
import games.indie.frostfire.user.ui.TextEntry;

public class StartMenu extends BasicGameState {
	
	private Button createWorld;
	private Image background;
	private TextEntry enterSeed;
	private boolean start;

	public void init(GameContainer gc, StateBasedGame game) throws SlickException {
		createWorld = new Button(96, 64, "create-new-world");
		background = Resource.getImage("menu-background");
		enterSeed = new TextEntry(gc, 111, 111);
	}

	public void render(GameContainer gc, StateBasedGame game, Graphics screen) throws SlickException {
		screen.scale(FrostFire.scale, FrostFire.scale);
		background.draw();
		createWorld.draw();
		enterSeed.draw(screen);
	}

	public void update(GameContainer gc, StateBasedGame game, int delta) throws SlickException {
		
		if (gc.getInput().isKeyPressed(Input.KEY_ESCAPE)) {
			gc.exit();
		}
		
		if (gc.getInput().isKeyDown(Input.KEY_ENTER)) {
			start = true;
		}
		if (start) {
			((Gameplay) game.getState(GameState.GAMEPLAY)).getWorld().generate(enterSeed.getBox().getText());
			game.enterState(GameState.GAMEPLAY);
		}
		enterSeed.blink(delta);
	}

	public int getID() {
		return GameState.START_MENU;
	}
	
	@Override
	public void mousePressed(int button, int mouseX, int mouseY) {
		mouseX /= FrostFire.scale;
		mouseY /= FrostFire.scale;
		enterSeed.mousePressed(button, mouseX, mouseY);
		if (createWorld.mousePressed(button, mouseX, mouseY)) {
			start = true;
		}
	}

}
