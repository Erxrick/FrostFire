package games.indie.frostfire.motion;

import org.newdawn.slick.geom.Vector2f;

public class Swing extends Motion {
	
	protected int degreesToSwing;

	public Swing(double timeInSeconds, double direction, float range) {
		super(timeInSeconds, direction, range);
		degreesToSwing = 180;
	}

	public Vector2f next() {
		count++;
		float percentageComplete = count/(float) duration;
		float distanceFromOrigin;
		distanceFromOrigin = (count < duration/2) ? percentageComplete : 1 - percentageComplete;
		distanceFromOrigin *= 2 * range;
		Vector2f components = new Vector2f(direction + percentageComplete * degreesToSwing - degreesToSwing/2);
		return new Vector2f(components.getX() * distanceFromOrigin, components.getY() * distanceFromOrigin);
	}

}
