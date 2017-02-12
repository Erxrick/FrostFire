package games.indie.frostfire;

import java.util.HashMap;

import org.lwjgl.input.Mouse;
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
	
	private Body body = new Body("res/images/player/move-forward.png");
	private Head head = new Head("res/images/player/head-sprites.png");
	
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
			moveSouth.draw(getLocation().getX(), getLocation().getY());
		}
	}
	
	private class Head implements Drawable {
		
		private double sightAngle;
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
	
	public void setLocation(Coord location) {
		this.location = location;
		if (head != null)
			updateHead();
	}
	
	public void updateHead() {
		head.sightAngle = this.location.directionTo(
		new Coord(Mouse.getX()/FrostFire.SCALE, FrostFire.NATIVE_HEIGHT - Mouse.getY()/FrostFire.SCALE));
	}

}
