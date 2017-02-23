package games.indie.frostfire.motion;

import org.newdawn.slick.geom.Vector2f;

import games.indie.frostfire.entities.human.Hand;

public class Punch extends Motion {

	public Punch(Hand hand) {
		super(0.5, hand.getBody().getHead().getSightAngle() + 180, 8);
	}

	public Vector2f next() {
		count++;
		float percentageComplete = count/(float) duration;
		float distanceFromOrigin;
		distanceFromOrigin = (count < duration/2) ? percentageComplete : 1 - percentageComplete;
		distanceFromOrigin *= 2 * range;
		Vector2f components = new Vector2f(direction);
		return new Vector2f(components.getX() * distanceFromOrigin, components.getY() * distanceFromOrigin);
	}

}
