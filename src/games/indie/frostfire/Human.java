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
	
	public enum ActionType {
		IDLE,
		MOVE
	}

	private int hunger, maxHunger;
	private int thirst, maxThirst;
	// TODO temperature affects thirst change rate
	private int strength;
	protected HashMap<BodyPart, Item> gear;
	
	private Body body = new Body();
	protected Head head = new Head("res/images/player/head-sprites.png");
	
	class Body implements Drawable {

		private SpriteSheet sprites;
		private Animation moveSouth, moveNorth, moveEast, moveWest;
//		private HashMap<Action, Animation> animations;
		
		Body() {
			String[] vertical = {"0", "1", "2", "1", "0", "1f", "2f", "1f"};
			moveSouth = Resources.build("res/images/player/move-south.png", 16, vertical);
			moveNorth = Resources.build("res/images/player/move-north.png", 16, vertical);
			String[] horizonal = {"0", "1", "2", "3", "0", "4", "5", "6"};
			SpriteSheet s = new SpriteSheet(Resources.loadImage("res/images/player/move-side.png"), 16, 16);
			moveEast = Resources.build(s, 16, horizonal);
			moveWest = Resources.build(new SpriteSheet(s.getFlippedCopy(true, false), 16, 16), 16, horizonal);
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
			moveSouth.draw(getLocation().getX(), getLocation().getY());
		}
	}
	
	class Head implements Drawable {
		
		double sightAngle;
		private HashMap<Direction, Image> states;
		private Image currentState;
		private int x_offset, y_offset;
		
		Head(String path) {
			SpriteSheet headSprites = new SpriteSheet(Resources.loadImage(path), 16, 16);
			states = new HashMap<>();
			for (int i = 0; i < headSprites.getHorizontalCount(); i++)
				states.put(Direction.values()[i], headSprites.getSprite(i, 0));
			states.put(Direction.NORTH_WEST, headSprites.getSprite(3, 0).getFlippedCopy(true, false));
			states.put(Direction.WEST, headSprites.getSprite(2, 0).getFlippedCopy(true, false));
			states.put(Direction.SOUTH_WEST, headSprites.getSprite(1, 0).getFlippedCopy(true, false));
			x_offset = 0;
			y_offset = -8;
		}

		public void draw() {
			states.get(Direction.towards(sightAngle))
			.draw(getLocation().getX() + x_offset, getLocation().getY() + y_offset);
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
