package games.indie.frostfire.user;

import games.indie.frostfire.entities.human.BodyPart;

public class BodySlot extends UIComponent {
	
	private BodyPart holding;
	
	public BodySlot(int screen_x, int screen_y, BodyPart equip) {
		super(screen_x, screen_y);
		this.holding = equip;
	}

	@Override
	public void draw() {
		if (holding.getEquipped() != null) {
			holding.getEquipped().getIcon().draw(
					screen_x + 8 - holding.getEquipped().getIcon().getWidth()/2,
					screen_y + 8 - holding.getEquipped().getIcon().getHeight()/2);
		}
	}

}
