package games.indie.frostfire.motion;

import java.util.Iterator;

import org.newdawn.slick.geom.Vector2f;

/**
 * A base generator class for Motions created by world interactions (EX: punching or using a tool)
 * Creating class has the responsiblity of having an origin point
 * because returned Vector2fs use an origin of (0, 0)
 * 
 * @author Wesley Barlow
 */
public abstract class Motion implements Iterator<Vector2f> {
	
	protected int duration;
	protected int timeSinceStart;
	protected double direction;
	protected float range;
	
	public Motion(int timeInMilliseconds, double direction, float range) {
		this.duration = timeInMilliseconds;
		this.direction = direction;
		this.range = range;
	}
	
	public Vector2f generate(int delta) {
		timeSinceStart += delta;
		return next();
	}
	
	public boolean hasNext() {
		return timeSinceStart < duration;
	}

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	public double getDirection() {
		return direction;
	}

	public void setDirection(double direction) {
		this.direction = direction;
	}

	public float getRange() {
		return range;
	}

	public void setRange(float range) {
		this.range = range;
	}
	
}
