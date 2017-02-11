package games.indie.frostfire.states;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.state.BasicGameState;

public abstract class GameState extends BasicGameState {
	
	public static final int MAIN_MENU = 0;
	public static final int CLIENT = 1;
	
	public void debug(Graphics g) {
		g.drawString(this.getClass().getSimpleName(), 10, 10);
	}

}
