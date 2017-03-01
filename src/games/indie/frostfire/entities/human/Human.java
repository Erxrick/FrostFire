package games.indie.frostfire.entities.human;

import java.util.LinkedList;
import java.util.Queue;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.geom.Vector2f;

import games.indie.frostfire.Resource;
import games.indie.frostfire.entities.Creature;
import games.indie.frostfire.entities.human.Action.ActionType;
import games.indie.frostfire.entities.stats.Natural;
import games.indie.frostfire.items.Item;
import games.indie.frostfire.world.Camera;
import games.indie.frostfire.world.Direction;

public class Human extends Creature {
	
	protected Natural hunger;
	protected Natural thirst;
	
	protected Hand rightHand = new Hand(this, ActionType.PUNCH_RIGHT);
	protected Hand leftHand = new Hand(this, ActionType.PUNCH_LEFT);
	protected Head head = new Head(this);
	protected BodyPart chest = new BodyPart(this);
	protected BodyPart legs = new BodyPart(this);
	protected BodyPart feet = new BodyPart(this);
	
	private static Action[] actions;
	protected Action currentAction;
	
	public Human() {
		setSize(16, 16);
		setCollision(2, -12, 12, 4);
		loadActions();
		setAction(ActionType.IDLE, Direction.SOUTH);
		hunger = new Natural(0, 100, -.0001, health);
		thirst = new Natural(0, 100, -.0002, health);
	}
	
	private int time;
	private Queue<Item> nearbyItems = new LinkedList<>();
	private int timeToPickup;
	
	public void update(int delta) {
		currentAction.getAnimation().update(delta);
		rightHand.update(delta);
		leftHand.update(delta);
		time += delta;
		if (time >= 1000) {
			time -= 1000;
			hunger.update(1000);
			thirst.update(1000);
		}
		for (Item item : world.onGround) {
			if (nearbyItems.contains(item)) {
				if (!withinPickupRange(item)) {
					nearbyItems.remove(item);
					break;
				}
			} else if (withinPickupRange(item)) {
				nearbyItems.offer(item);
			}
		}
		if (nearbyItems.size() > 0) {
			timeToPickup += delta;
		} else {
			timeToPickup = 0;
		}
		if (timeToPickup > 500) {
			Item item = nearbyItems.poll();
			Resource.play("pickup");
			rightHand.pickup(item);
			world.onGround.remove(item);
		}
	}
	
	
	private boolean withinPickupRange(Item item) {
		return getLocation().distance(item.getLocation()) < 16;
	}
	
	public void setAction(ActionType type, Direction direction) {
		this.direction = direction;
		switch (direction) {
		case WEST:
			leftHand.getOffset().set(6, -12);
			rightHand.getOffset().set(4, -12);
			break;
		case EAST:
			leftHand.getOffset().set(12, -12);
			rightHand.getOffset().set(10, -12);
			break;
		case NORTH:
			leftHand.getOffset().set(3, -12);
			rightHand.getOffset().set(13, -12);
			break;
		case SOUTH:
			rightHand.getOffset().set(3, -12);
			leftHand.getOffset().set(13, -12);
			break;
		default:
		}
		for (Action action : actions) {
			if (action.equals(type, direction)) {
				currentAction = action;
				break;
			}
		}
	}
	public void setAction(ActionType type, double degrees) {
		setAction(type, Direction.four(degrees));
	}
	
	public Action getCurrentAction() {
		return currentAction;
	}
	
	public void draw() {
		if (direction == Direction.NORTH) {
			head.draw();
			Camera.draw(currentAction.getAnimation().getCurrentFrame(), x, y);
		} else {
			Camera.draw(currentAction.getAnimation().getCurrentFrame(), x, y);
			head.draw();
		}
		rightHand.draw();
		leftHand.draw();
	}
	
	public boolean move(Vector2f movement) {
		boolean moved = super.move(movement);
		head.setSightAngle(movement.getTheta());
		return moved;
	}
	
	public Head getHead() {
		return head;
	}
	public Hand getRightHand() {
		return rightHand;
	}
	public Hand getLeftHand() {
		return leftHand;
	}
	public BodyPart getChest() {
		return chest;
	}
	public BodyPart getLegs() {
		return legs;
	}
	public BodyPart getFeet() {
		return feet;
	}
	
	private void loadActions() {
		// Forgive me father, for I know not what I do
		String[] vertical = {"0", "1", "2", "1", "0", "1f", "2f", "1f"};
		String[] horizonal = {"0", "1", "2", "3", "0", "4", "5", "6"};
		SpriteSheet s = new SpriteSheet(Resource.loadImage("res/images/player/move-side.png"), 16, 16);
		Animation moveUp = Resource.build("res/images/player/move-north.png", 16, vertical);
		Animation moveDown = Resource.build("res/images/player/move-south.png", 16, vertical);
		Animation moveRight = Resource.build(s, 16, horizonal);
		Animation moveLeft = Resource.flip(moveRight);
		
		Animation rightPunchUp = new Animation(new SpriteSheet(Resource.getImage("interact-north"), 16, 16), 100);
		Animation leftPunchUp = Resource.flip(rightPunchUp);
		
		Animation rightPunchDown = new Animation(new SpriteSheet(Resource.getImage("interact-south"), 16, 16), 100);
		Animation leftPunchDown = Resource.flip(rightPunchDown);
		
		Animation rightPunchRight = new Animation(new SpriteSheet(Resource.getImage("interact-right-right_hand"), 16, 16), 100);
		Animation leftPunchRight = new Animation(new SpriteSheet(Resource.getImage("interact-right-left_hand"), 16, 16), 100);
		
		Animation rightPunchLeft = Resource.flip(leftPunchRight);
		Animation leftPunchLeft = Resource.flip(rightPunchRight);
		
		actions = new Action[] {
				
				new Action (new Animation(), ActionType.IDLE, Direction.SOUTH),
				new Action (new Animation(), ActionType.IDLE, Direction.NORTH),
				new Action (new Animation(), ActionType.IDLE, Direction.EAST),
				new Action (new Animation(), ActionType.IDLE, Direction.WEST),
				
				new Action(moveDown, ActionType.MOVE, Direction.SOUTH),
				new Action(moveUp, ActionType.MOVE, Direction.NORTH),
				new Action(moveRight, ActionType.MOVE, Direction.EAST),
				new Action(moveLeft, ActionType.MOVE, Direction.WEST),
				
				new Action(rightPunchDown, ActionType.PUNCH_RIGHT, Direction.SOUTH),
				new Action(leftPunchDown, ActionType.PUNCH_LEFT, Direction.SOUTH),
				
				new Action(rightPunchUp, ActionType.PUNCH_RIGHT, Direction.NORTH),
				new Action(leftPunchUp, ActionType.PUNCH_LEFT, Direction.NORTH),
				
				new Action(rightPunchRight, ActionType.PUNCH_RIGHT, Direction.EAST),
				new Action(leftPunchRight, ActionType.PUNCH_LEFT, Direction.EAST),
				
				new Action(rightPunchLeft, ActionType.PUNCH_RIGHT, Direction.WEST),
				new Action(leftPunchLeft, ActionType.PUNCH_LEFT, Direction.WEST),
		};
		actions[0].getAnimation().addFrame(moveDown.getImage(0), 100);
		actions[1].getAnimation().addFrame(moveUp.getImage(0), 100);
		actions[2].getAnimation().addFrame(moveRight.getImage(0), 100);
		actions[3].getAnimation().addFrame(moveLeft.getImage(0), 100);
	}
	
	public void debug_draw(Graphics screen) {
		super.debug_draw(screen);
		rightHand.debug_draw(screen);
		leftHand.debug_draw(screen);
	}
	
}
