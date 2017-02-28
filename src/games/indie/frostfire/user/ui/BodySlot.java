package games.indie.frostfire.user.ui;

import java.awt.Point;
import java.awt.Rectangle;

import games.indie.frostfire.entities.human.BodyPart;

public class BodySlot extends UIComponent {
	
	private BodyPart holding;
	
	public BodySlot(int screen_x, int screen_y, BodyPart equip) {
		super(screen_x, screen_y);
		this.holding = equip;
	}

	public void draw() {
		if (holding.getEquipped() != null) {
			holding.getEquipped().getIcon().draw(
					screen_x + 8 - holding.getEquipped().getIcon().getWidth()/2,
					screen_y + 8 - holding.getEquipped().getIcon().getHeight()/2);
		}
	}

	public boolean mousePressed(int button, int x, int y) {
		boolean clicked = new Rectangle(screen_x, screen_y, 16, 16).contains(new Point(x, y));
		if (clicked && holding.hasItem()) {
			if (button == 0) {
				System.out.println("Pickup item");
				holding.dropItem();
			} else if (button == 1) {
				holding.getEquipped().stateChange();
			}
		}
		return clicked;
	}

}
