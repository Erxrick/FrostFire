package games.indie.frostfire.motion;

import org.newdawn.slick.geom.Vector2f;

import games.indie.frostfire.entities.Human;

public class Punch extends Motion {

	public Punch(Human human) {
		super(0.5, human.getHead().getSightAngle(), 8);
	}

	public Vector2f next() {
		count++;
		float percentageComplete = count/(float) duration;
		float distanceFromOrigin;
		distanceFromOrigin = (count < duration/2) ? percentageComplete : 1 - percentageComplete;
		distanceFromOrigin *= 2 * range;
		return new Vector2f(0, distanceFromOrigin);
	}

}
