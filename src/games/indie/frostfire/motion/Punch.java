package games.indie.frostfire.motion;

import org.newdawn.slick.geom.Vector2f;

import games.indie.frostfire.entities.human.Hand;

public class Punch extends Motion {

	public Punch(Hand hand) {
		super(300, hand.getBody().getHead().getSightAngle(), 12);
	}

	public Vector2f next() {
		float percentageComplete = timeSinceStart/(float) duration;
		float distanceFromOrigin;
		distanceFromOrigin = (timeSinceStart < duration/2) ? percentageComplete : 1 - percentageComplete;
		distanceFromOrigin *= 2 * range;
		Vector2f components = new Vector2f(direction);
		return new Vector2f(components.getX() * distanceFromOrigin, components.getY() * distanceFromOrigin);
	}

}
