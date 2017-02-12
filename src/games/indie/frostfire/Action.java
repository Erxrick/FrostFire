package games.indie.frostfire;

import java.util.Arrays;

import org.newdawn.slick.Animation;

public class Action {
	
	public enum ActionType {
		IDLE,
		MOVE
	}
	
	protected Animation animation;
	private ActionType type;
	private Direction[] directions;
	
	Action(Animation animation, ActionType type, Direction...directions) {
		this.animation = animation;
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
	
}
