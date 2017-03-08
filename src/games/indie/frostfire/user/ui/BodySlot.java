package games.indie.frostfire.user.ui;

import java.awt.Point;
import java.awt.Rectangle;

import games.indie.frostfire.entities.human.BodyPart;
import games.indie.frostfire.user.Player;

public class BodySlot extends UIComponent {
	
	private BodyPart holder;
	
	public BodySlot(int screen_x, int screen_y, BodyPart equip) {
		super(screen_x, screen_y);
		this.holder = equip;
	}

	public void draw() {
		if (holder.getEquipped() != null) {
			holder.getEquipped().getIcon().draw(
					screen_x + 8 - holder.getEquipped().getIcon().getWidth()/2,
					screen_y + 8 - holder.getEquipped().getIcon().getHeight()/2);
		}
	}
	
	public boolean mouseOverSelf(int x, int y) {
		return new Rectangle(screen_x, screen_y, 16, 16).contains(new Point(x, y));
	}

	public boolean mousePressed(int button, int x, int y) {
		boolean clicked = mouseOverSelf(x, y);
		if (clicked && holder.hasItem()) {
			if (button == 0) {
				((Player) holder.getBody()).getUI().grab(holder.getEquipped());
				holder.setEquipped(null);
			} else if (button == 1) {
				holder.getEquipped().stateChange(holder);
			}
		}
		return clicked;
	}
	
	public boolean mouseReleased(int x, int y) {
		boolean releasedOnSelf = mouseOverSelf(x, y);
		if (releasedOnSelf) {
			holder.setEquipped(((Player) holder.getBody()).getUI().getGrabbed());
		}
		return releasedOnSelf;
	}

}
