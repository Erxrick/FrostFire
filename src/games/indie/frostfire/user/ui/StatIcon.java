package games.indie.frostfire.user.ui;

import org.newdawn.slick.SpriteSheet;

import games.indie.frostfire.Resource;
import games.indie.frostfire.entities.stats.Stat;

public class StatIcon extends UIComponent {
	
	private Stat representing;
	private SpriteSheet states;

	public StatIcon(int screenX, int screenY, Stat representing, String name) {
		setLocation(screenX, screenY);
		this.representing = representing;
		states = new SpriteSheet(Resource.getImage(name), 10, 9);
	}

	public void draw() {
		states.getSprite((int) ((1 - representing.getPercentage()) * 4), 0).draw(x, y);
	}
}
