package games.indie.frostfire;

import java.util.HashMap;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Image;
import org.newdawn.slick.SpriteSheet;

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
	private Head head = new Head("res/images/head0.png");
	
	private class Head implements Drawable {
		
		protected double sightAngle;
		private SpriteSheet sprites;
		private int x_offset, y_offset;
		
		Head(String path) {
			sprites = new SpriteSheet(Resources.loadImage(path), 16, 16);
			x_offset = 0;
			y_offset = -7;
		}

		public void draw() {
			// TODO determine sprite to draw based on sightAngle
			sprites.getSubImage(0, 0).draw(location.getX() + x_offset, location.getY() + y_offset);
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
		head.draw();
	}

}
