package games.indie.frostfire.entities.human;

import org.newdawn.slick.Animation;

import games.indie.frostfire.world.Direction;

public class Action {
	
	public enum ActionType {
		IDLE (0),
		MOVE (1),
		PUNCH_RIGHT (2),
		PUNCH_LEFT (3);

		private int num;
		
		ActionType(int num) {
			this.num = num;
		}
		
		public int getNum() {
			return this.num;
		}
	}
	
	private Animation animation;
	private ActionType type;
	private Direction direction;
	
	public boolean equals(ActionType type, Direction direction) {
		return this.type == type && this.direction == direction;
	}
	
	public Action(Animation animation, ActionType type, Direction direction) {
		this.setAnimation(animation);
		this.type = type;
		this.direction = direction;
		animation.setAutoUpdate(false);
	}

	public Animation getAnimation() {
		return animation;
	}

	public void setAnimation(Animation animation) {
		this.animation = animation;
	}
	
	public ActionType getType() {
		return type;
	}
	
	public void update(int delta) {
		animation.update(delta);
	}
	
	public void nextFrame() {
		if (animation.getFrame() == animation.getFrameCount() - 1)
			animation.setCurrentFrame(0);
		else
			animation.setCurrentFrame(animation.getFrame() + 1);
	}
	
}
