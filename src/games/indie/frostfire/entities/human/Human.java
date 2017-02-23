package games.indie.frostfire.entities.human;

import org.newdawn.slick.Animation;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.geom.Vector2f;

import games.indie.frostfire.Resource;
import games.indie.frostfire.entities.Creature;
import games.indie.frostfire.entities.Stat;
import games.indie.frostfire.entities.human.Action.ActionType;
import games.indie.frostfire.world.Camera;
import games.indie.frostfire.world.Direction;

public class Human extends Creature {
	
	protected Stat hunger;
	protected Stat thirst;
	
	protected Hand rightHand = new Hand(this);
	protected Hand leftHand = new Hand(this);
	protected Head head = new Head(this);
	protected BodyPart chest = new BodyPart(this);
	protected BodyPart legs = new BodyPart(this);
	protected BodyPart feet = new BodyPart(this);
	
	private Action[] actions;
	protected Action currentAction;
	
	public Human() {
		setSize(16, 16);
		setCollision(2, -12, 12, 4);
		loadActions();
		setAction(ActionType.IDLE, Direction.SOUTH);
	}
	
	public void setAction(ActionType type, Direction direction) {
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
	
	public void draw() {
		if (direction == Direction.NORTH) {
			head.draw();
			Camera.draw(currentAction.getAnimation(), x, y);
		} else {
			Camera.draw(currentAction.getAnimation(), x, y);
			head.draw();
		}
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
		String[] vertical = {"0", "1", "2", "1", "0", "1f", "2f", "1f"};
		String[] horizonal = {"0", "1", "2", "3", "0", "4", "5", "6"};
		SpriteSheet s = new SpriteSheet(Resource.loadImage("res/images/player/move-side.png"), 16, 16);
		Animation moveUp = Resource.build("res/images/player/move-north.png", 16, vertical);
		Animation moveDown = Resource.build("res/images/player/move-south.png", 16, vertical);
		Animation moveRight = Resource.build(s, 16, horizonal);
		Animation moveLeft = new Animation();
		for (int i = 0; i < moveRight.getFrameCount(); i++) {
			moveLeft.addFrame(moveRight.getImage(i).getFlippedCopy(true, false), 100);
		}
		actions = new Action[] {
				new Action (new Animation(), ActionType.IDLE, Direction.SOUTH),
				new Action (new Animation(), ActionType.IDLE, Direction.NORTH),
				new Action (new Animation(), ActionType.IDLE, Direction.EAST),
				new Action (new Animation(), ActionType.IDLE, Direction.WEST),
				new Action(moveDown, ActionType.MOVE, Direction.SOUTH),
				new Action(moveUp, ActionType.MOVE, Direction.NORTH),
				new Action(moveRight, ActionType.MOVE, Direction.EAST),
				new Action(moveLeft, ActionType.MOVE, Direction.WEST)
		};
		actions[0].getAnimation().addFrame(moveDown.getImage(0), 100);
		actions[1].getAnimation().addFrame(moveUp.getImage(0), 100);
		actions[2].getAnimation().addFrame(moveRight.getImage(0), 100);
		actions[3].getAnimation().addFrame(moveLeft.getImage(0), 100);
	}
	
}
