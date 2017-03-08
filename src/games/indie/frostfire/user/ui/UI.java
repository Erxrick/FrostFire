package games.indie.frostfire.user.ui;

import java.util.ArrayList;

import org.lwjgl.input.Mouse;

import games.indie.frostfire.Drawable;
import games.indie.frostfire.FrostFire;
import games.indie.frostfire.entities.human.Human;
import games.indie.frostfire.items.Item;
import games.indie.frostfire.user.Player;

public class UI extends ArrayList<UIComponent> implements Drawable {
	
	private Item grabbed;
	
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
		if (getGrabbed() != null) {
			getGrabbed().getIcon().draw(Mouse.getX()/FrostFire.scale, FrostFire.NATIVE_HEIGHT - Mouse.getY()/FrostFire.scale);
		}
	}

	public void grab(Item equipped) {
		setGrabbed(equipped);
	}
	
	public void dropGrabbed(Player player) {
		if (getGrabbed() != null) {
			player.getWorld().place(getGrabbed(), player.getCenterX(), player.getCenterY());
			setGrabbed(null);
		}
	}

	public Item getGrabbed() {
		return grabbed;
	}

	public void setGrabbed(Item grabbed) {
		this.grabbed = grabbed;
	}
	

}
