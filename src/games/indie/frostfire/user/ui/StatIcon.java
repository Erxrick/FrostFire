package games.indie.frostfire.user.ui;

import org.newdawn.slick.SpriteSheet;

import games.indie.frostfire.Resource;
import games.indie.frostfire.entities.stats.Stat;

public class StatIcon extends UIComponent {
	
	private Stat representing;
	private SpriteSheet states;

	public StatIcon(int screen_x, int screen_y, String name) {
		super(screen_x, screen_y);
		states = new SpriteSheet(Resource.getImage(name), 10, 9);
	}
	
	public void setStat(Stat representing) {
		this.representing = representing;
	}

	public void draw() {
		states.getSprite((int) ((1 - representing.getPercentage()) * 4), 0).draw(screen_x, screen_y);
	}
}
