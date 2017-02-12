package games.indie.frostfire;

import java.util.HashMap;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Image;
import org.newdawn.slick.SpriteSheet;

import games.indie.frostfire.Action.ActionType;

public class Human extends Creature {
	
	public enum BodyPart {
		HEAD,
		CHEST,
		LEGS,
		FEET,
		RIGHT_HAND,
		LEFT_HAND;
	}
	protected HashMap<BodyPart, Item> gear;
	
	private int hunger, maxHunger;
	private int thirst, maxThirst;
	// TODO temperature affects thirst change rate
	private int strength;
	
	protected Action currentAction;
	
	protected Body body = new Body();
	protected Head head = new Head("res/images/player/head-sprites.png");
	private Action[] actions;
		
	class Body implements Drawable {
		
		protected Direction direction;
		
		Body() {
			
			String[] vertical = {"0", "1", "2", "1", "0", "1f", "2f", "1f"};
			String[] horizonal = {"0", "1", "2", "3", "0", "4", "5", "6"};
			SpriteSheet s = new SpriteSheet(Resources.loadImage("res/images/player/move-side.png"), 16, 16);
			Animation moveUp = Resources.build("res/images/player/move-north.png", 16, vertical);
			Animation moveDown = Resources.build("res/images/player/move-south.png", 16, vertical);
			Animation moveRight = Resources.build(s, 16, horizonal);
			Animation moveLeft = new Animation();
			for (int i = 0; i < moveRight.getFrameCount(); i++) {
				moveLeft.addFrame(moveRight.getImage(i).getFlippedCopy(true, false), 100);
			}
			
			actions = new Action[] {
					new Action (new Animation(),
							ActionType.IDLE,
							Direction.SOUTH, Direction.SOUTH_EAST, Direction.SOUTH_WEST),
					new Action (new Animation(),
							ActionType.IDLE,
							Direction.NORTH, Direction.NORTH_EAST, Direction.NORTH_WEST),
					new Action (new Animation(),
							ActionType.IDLE,
							Direction.EAST),
					new Action (new Animation(),
							ActionType.IDLE,
							Direction.WEST),
					new Action(moveDown,
							ActionType.MOVE,
							Direction.SOUTH, Direction.SOUTH_EAST, Direction.SOUTH_WEST),
					new Action(moveUp,
							ActionType.MOVE,
							Direction.NORTH, Direction.NORTH_EAST, Direction.NORTH_WEST),
					new Action(moveRight,
							ActionType.MOVE,
							Direction.EAST),
					new Action(moveLeft,
							ActionType.MOVE,
							Direction.WEST)
			};
			actions[0].animation.addFrame(moveDown.getImage(0), 100);
			actions[1].animation.addFrame(moveUp.getImage(0), 100);
			actions[2].animation.addFrame(moveRight.getImage(0), 100);
			actions[3].animation.addFrame(moveLeft.getImage(0), 100);
		}

		public void draw() {
			currentAction.animation.draw(location.getX(), location.getY());
		}
	}
	
	class Head implements Drawable {
		
		double sightAngle;
		private HashMap<Direction, Image> headMap;
		private Image currentState;
		private int x_offset, y_offset;
		
		Head(String path) {
			SpriteSheet headSprites = new SpriteSheet(Resources.loadImage(path), 16, 16);
			headMap = new HashMap<>();
			for (int i = 0; i < headSprites.getHorizontalCount(); i++)
				headMap.put(Direction.values()[i], headSprites.getSprite(i, 0));
			headMap.put(Direction.NORTH_WEST, headSprites.getSprite(3, 0).getFlippedCopy(true, false));
			headMap.put(Direction.WEST, headSprites.getSprite(2, 0).getFlippedCopy(true, false));
			headMap.put(Direction.SOUTH_WEST, headSprites.getSprite(1, 0).getFlippedCopy(true, false));
			x_offset = 0;
			y_offset = 0;
		}

		public void draw() {
			switch (currentAction.animation.getFrame()) {
  			case 1:
  			case 3:
  			case 5:
  			case 7:
  				head.x_offset = 0;
  				head.y_offset =  -7;
  				break;
  			case 2:
  				head.x_offset = 1;
  				head.y_offset =  -6;
  				break;
  			case 6:
  				head.x_offset =  -1;
  				head.y_offset =  -6;
  				break;
  			default:
  				head.x_offset = 0;
  				head.y_offset =  -8;
  			}
			if (currentAction == actions[2] || currentAction == actions[6])
				head.x_offset += 1;
			else if (currentAction == actions[3] || currentAction == actions[7])
				head.x_offset -= 1;
			
			headMap.get(Direction.towards(sightAngle)).draw(getLocation().getX() + x_offset, getLocation().getY() + y_offset);
		}
	}
	
	public Human() {
		super(100, 100);
		gear = new HashMap<>();
		maxHunger = 100;
		hunger = maxHunger;
		maxThirst = 100;
		thirst = maxThirst;
		strength = 100;
		setAction(ActionType.MOVE, Direction.SOUTH);
	}
	
	public void pickup(Item i) {
		if (inventory.getWeight() + i.getWeight() <= strength) {
			inventory.add(i);
		}
	}
	
	public void setAction(ActionType type, Direction direction) {
		for (Action action : actions)
			if (action.is(type, direction))
				currentAction = action;
				body.direction = direction;
	}

	public void draw() {
		if (currentAction.points(Direction.NORTH, Direction.NORTH_WEST, Direction.NORTH_EAST)) {
			head.draw();
			body.draw();
		} else {
			body.draw();
			head.draw();
		}
	}

}
