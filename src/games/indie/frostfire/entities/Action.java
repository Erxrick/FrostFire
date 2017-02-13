package games.indie.frostfire.entities;

import java.util.Arrays;

import org.newdawn.slick.Animation;

import games.indie.frostfire.world.Direction;

public class Action {
	
	public enum ActionType {
		IDLE,
		MOVE
	}
	
	private Animation animation;
	private ActionType type;
	private Direction[] directions;
	
	public Action(Animation animation, ActionType type, Direction...directions) {
		this.setAnimation(animation);
		// TODO Update animation based on Entity speed
		// TODO Notify Human of frame change
		this.type = type;
		this.directions = directions;
	}

	public boolean is(ActionType type, Direction direction) {
		return this.type == type && Arrays.asList(directions).contains(direction);
	}
	
	public boolean points(Direction...directions) {
		for (Direction d : directions) {
			if (Arrays.asList(this.directions).contains(d))
				return true;
		}
		return false;
	}

	public Animation getAnimation() {
		return animation;
	}

	public void setAnimation(Animation animation) {
		this.animation = animation;
	}
	
}
