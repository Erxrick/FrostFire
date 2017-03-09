package games.indie.frostfire.user.ui;

import org.newdawn.slick.geom.Rectangle;

import games.indie.frostfire.Sprite;

public abstract class UIComponent extends Sprite {
	
	public boolean isMouseOverSelf(int mouseX, int mouseY) {
		return new Rectangle(x, y, width, height).contains(mouseX, mouseY);
	}
	
	public boolean mousePressed(int button, int mouseX, int mouseY) {
		return isMouseOverSelf(mouseX, mouseY);
	}
	
	public boolean mouseReleased(UI ui, int mouseX, int mouseY) {
		return isMouseOverSelf(mouseX, mouseY);
	}
	
}
