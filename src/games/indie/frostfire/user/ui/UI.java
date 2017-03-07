package games.indie.frostfire.user.ui;

import java.util.ArrayList;

import games.indie.frostfire.Drawable;
import games.indie.frostfire.FrostFire;
import games.indie.frostfire.entities.human.Human;

public class UI extends ArrayList<UIComponent> implements Drawable {
	
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
		
		StatIcon health = new StatIcon(screen_midpoint - 15, 4, "heart");
		health.setStat(human.getHealth());
		StatIcon thirst = new StatIcon(screen_midpoint - 5, 4, "water");
		thirst.setStat(human.getThirst());
		StatIcon hunger = new StatIcon(screen_midpoint + 5, 4, "food");
		hunger.setStat(human.getHunger());
		
		this.add(rightHand);
		this.add(leftHand);
		this.add(upper_body);
		this.add(lower_body);
		this.add(health);
		this.add(thirst);
		this.add(hunger);
	}

	public void draw() {
		for (Drawable component : this) {
			component.draw();
		}
	}

}
