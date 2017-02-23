package games.indie.frostfire.entities.human;

import org.newdawn.slick.geom.Vector2f;

import games.indie.frostfire.motion.Motion;
import games.indie.frostfire.motion.Punch;

public class Hand extends BodyPart {
	
	protected Motion currentMotion;
	protected Vector2f offset;

	public Hand(Human human) {
		super(human);
	}
	
	public void punch() {
		if (currentMotion == null) {
			if (equipped == null) {
				currentMotion = new Punch(this);
			}
		}
	}
	
}
