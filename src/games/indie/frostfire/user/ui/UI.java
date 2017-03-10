package games.indie.frostfire.user.ui;

import java.util.ArrayList;
import java.util.Arrays;

import org.lwjgl.input.Mouse;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.SpriteSheetFont;

import games.indie.frostfire.FrostFire;
import games.indie.frostfire.Resource;
import games.indie.frostfire.entities.human.Human;
import games.indie.frostfire.items.Item;
import games.indie.frostfire.user.Player;

public class UI extends ArrayList<UIComponent> {
	
	static final public SpriteSheetFont STACK_FONT = new SpriteSheetFont(new SpriteSheet(Resource.getImage("stack-numbers"), 7, 7), '2');
	private Item grabbed;
	
	public UI(Human human) {
		
		int screen_midpoint = FrostFire.NATIVE_WIDTH/2;
		int lift = FrostFire.NATIVE_HEIGHT - 18;
		
		BodySlot leftHand = new BodySlot(screen_midpoint - 56, lift, human.getLeftHand(), "left-hand-slot");
		BodySlot rightHand = new BodySlot(screen_midpoint + 40, lift, human.getRightHand(), "right-hand-slot");
		BodySlot head = new BodySlot(screen_midpoint - 32, lift, human.getHead(), "head-slot");
		BodySlot chest = new BodySlot(screen_midpoint - 17, lift, human.getChest(), "chest-slot");
		BodySlot legs = new BodySlot(screen_midpoint + 1, lift, human.getLegs(), "legs-slot");
		BodySlot feet = new BodySlot(screen_midpoint + 16, lift, human.getFeet(), "feet-slot");
		
		StatIcon health = new StatIcon(screen_midpoint - 15, 4, human.getHealth(), "heart");
		StatIcon thirst = new StatIcon(screen_midpoint - 5, 4, human.getThirst(), "water");
		StatIcon hunger = new StatIcon(screen_midpoint + 5, 4, human.getHunger(), "food");
		
		this.addAll(Arrays.asList(leftHand, rightHand, head, chest, legs, feet, health, thirst, hunger));
	}

	public void draw() {
		for (UIComponent component : this) {
			component.draw();
		}
		if (getGrabbed() != null) {
			getGrabbed().getIcon().draw(Mouse.getX()/FrostFire.SCALE, FrostFire.NATIVE_HEIGHT - Mouse.getY()/FrostFire.SCALE);
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
