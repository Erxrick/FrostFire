package games.indie.frostfire.user;

import games.indie.frostfire.Drawable;
import games.indie.frostfire.FrostFire;
import games.indie.frostfire.entities.human.Human;
import games.indie.frostfire.items.Axe;

public class UI implements Drawable {
	
	private Drawable[] components;
	
	public UI(Human human) {
		int screen_midpoint = FrostFire.NATIVE_WIDTH/2;
		int lift = FrostFire.NATIVE_HEIGHT - 18;
		HandSlot leftHand = new HandSlot(screen_midpoint - 56, lift,
				human.getLeftHand(), "left-hand");
		HandSlot rightHand = new HandSlot(screen_midpoint + 40, lift, 
				human.getRightHand(), "left-hand");
		EquipmentSlot upper_body = new EquipmentSlot(
				new BodySlot(screen_midpoint - 32, lift, human.getHead()),
				new BodySlot(screen_midpoint - 16, lift, human.getChest()),
				"upper-slots");
		EquipmentSlot lower_body = new EquipmentSlot(
				new BodySlot(screen_midpoint, lift, human.getLegs()),
				new BodySlot(screen_midpoint + 16, lift, human.getFeet()),
				"lower-slots");
		
		rightHand.pickup(new Axe());
		
		StatIcon health = new StatIcon(screen_midpoint - 32, lift - 10, "heart");
		StatIcon thirst = new StatIcon(screen_midpoint - 22, lift - 10, "water");
		StatIcon hunger = new StatIcon(screen_midpoint - 12, lift - 10, "food");
		
		components = new Drawable[] {
				rightHand,
				leftHand,
				upper_body,
				lower_body,
				health,
				thirst,
				hunger
		};
	}

	public void draw() {
		for (Drawable component : components) {
			component.draw();
		}
	}

}
