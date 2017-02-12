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
	
	private Body body = new Body("res/images/player/move-forward.png");
	private Head head = new Head("res/images/head0.png");
	
	private class Body implements Drawable {

		private SpriteSheet sprites;
		private Animation moveSouth;
//		private HashMap<Action, Animation> animations;
		
		Body(String path) {
			moveSouth = new Animation(new SpriteSheet(Resources.loadImage(path), 16, 16), 100);
			moveSouth.addFrame(moveSouth.getImage(1), 100);
			moveSouth.addFrame(moveSouth.getImage(0), 100);
			moveSouth.addFrame(moveSouth.getImage(1).getFlippedCopy(true, false), 100);
			moveSouth.addFrame(moveSouth.getImage(2).getFlippedCopy(true, false), 100);
			moveSouth.addFrame(moveSouth.getImage(5), 100);
		}

		public void draw() {
			switch (moveSouth.getFrame()) {
			case 1:
			case 3:
			case 5:
			case 7:
				head.x_offset = 0;
				head.y_offset = -7;
				break;
			case 2:
				head.x_offset = 1;
				head.y_offset = -6;
				break;
			case 6:
				head.x_offset = -1;
				head.y_offset = -6;
				break;
			default:
				head.x_offset = 0;
				head.y_offset = -8;
			}
			moveSouth.draw(location.getX(), location.getY());
		}
	}
	
	private class Head implements Drawable {
		
		protected double sightAngle;
		private SpriteSheet sprites;
		private int x_offset, y_offset;
		
		Head(String path) {
			sprites = new SpriteSheet(Resources.loadImage(path), 16, 16);
			x_offset = 0;
			y_offset = -8;
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
		body.draw();
		head.draw();
	}

}
