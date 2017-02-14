package games.indie.frostfire.user;

import games.indie.frostfire.Drawable;
import games.indie.frostfire.FrostFire;
import games.indie.frostfire.entities.Human.BodyPart;

public class UI implements Drawable {
	
	
	private Drawable[] components;
	
	public UI() {
		int screen_midpoint = FrostFire.NATIVE_WIDTH/2;
		int lift = FrostFire.NATIVE_HEIGHT - 18;
		HandSlot right_hand = new HandSlot(screen_midpoint - 56, lift,
				BodyPart.RIGHT_HAND, "res/images/ui/right-hand.png");
		HandSlot left_hand = new HandSlot(screen_midpoint + 40, lift, 
				BodyPart.RIGHT_HAND, "res/images/ui/left-hand.png");
		EquipmentSlot upper_body = new EquipmentSlot(
				new BodySlot(screen_midpoint - 32, lift, BodyPart.HEAD),
				new BodySlot(screen_midpoint - 16, lift, BodyPart.CHEST),
				"res/images/ui/upper-slots.png");
		EquipmentSlot lower_body = new EquipmentSlot(
				new BodySlot(screen_midpoint, lift, BodyPart.LEGS),
				new BodySlot(screen_midpoint + 16, lift, BodyPart.FEET),
				"res/images/ui/lower-slots.png");
		
		Stat health = new Stat(screen_midpoint - 32, lift - 10, "res/images/ui/heart.png");
		Stat thirst = new Stat(screen_midpoint - 22, lift - 10, "res/images/ui/water.png");
		Stat hunger = new Stat(screen_midpoint - 12, lift - 10, "res/images/ui/food.png");
		
		components = new Drawable[] {
				right_hand,
				left_hand,
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
