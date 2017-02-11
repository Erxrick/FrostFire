package games.indie.frostfire;

import java.util.HashMap;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Image;

public class Human extends Creature {
	
	public enum BodyPart {
		HEAD,
		CHEST,
		LEGS,
		FEET,
		RIGHT_HAND,
		LEFT_HAND;
	}

	private int hunger, maxHunger;
	private int thirst, maxThirst;
	// TODO temperature affects thirst change rate
	private int strength;
	protected HashMap<BodyPart, Item> gear;
	
	private Image body = Resources.loadImage("res/images/body0.png");
	
	private class Head implements Drawable {
		
		protected double sightAngle;
		private Image image;
		
		Head(String path) {
			this.image = Resources.loadImage(path);
		}

		public void draw() {
			
		}
	}
	
	private Image[] heads;
	private Animation[] bodies;
	
	public Human() {
		super(100, 100);
		gear = new HashMap<>();
		maxHunger = 100;
		hunger = maxHunger;
		maxThirst = 100;
		thirst = maxThirst;
		strength = 100;
	}
	
	public void pickup(Item i) {
		if (inventory.getWeight() + i.getWeight() <= strength) {
			inventory.add(i);
		}
	}

	public void draw() {
		body.draw(location.getX(), location.getY());
	}

}
