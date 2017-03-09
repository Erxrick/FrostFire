package games.indie.frostfire.user.ui;

import games.indie.frostfire.Resource;
import games.indie.frostfire.entities.human.BodyPart;
import games.indie.frostfire.user.Player;

public class BodySlot extends UIComponent {
	
	private BodyPart holder;
	
	public BodySlot(int screenX, int screenY, BodyPart equip, String imageName) {
		setIcon(Resource.getImage(imageName));
		setLocation(screenX, screenY);
		this.holder = equip;
	}

	public void draw() {
		super.draw();
		if (holder.hasItem()) {
			holder.getEquipped().getIcon().draw(
					x + 8 - holder.getEquipped().getIcon().getWidth()/2,
					y + 8 - holder.getEquipped().getIcon().getHeight()/2);
			int itemQuantity = holder.getEquipped().getQuantity();
			if (itemQuantity > 1) {
				UI.STACK_FONT.drawString(x - 2, y - 2, Integer.toString(itemQuantity));
			}
		}
	}

	public boolean mousePressed(int button, int mouseX, int mouseY) {
		boolean clicked = isMouseOverSelf(mouseX, mouseY);
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
	
	public boolean mouseReleased(UI ui, int mouseX, int mouseY) {
		boolean holderPickup = isMouseOverSelf(mouseX, mouseY) && holder.canPickup(ui.getGrabbed());
		if (holderPickup) {
			holder.pickup(ui.getGrabbed());
			ui.setGrabbed(null);
		}
		return holderPickup;
	}

}
