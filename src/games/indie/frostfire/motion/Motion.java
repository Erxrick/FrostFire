package games.indie.frostfire.motion;

import java.util.Iterator;

import org.newdawn.slick.geom.Vector2f;

import games.indie.frostfire.FrostFire;

/**
 * A base generator class for Motions created by world interactions (EX: punching or using a tool)
 * Creating class has the responsiblity of having an origin point
 * because returned Vector2fs use an origin of (0, 0)
 * 
 * @author Wesley Barlow
 */
public abstract class Motion implements Iterator<Vector2f> {
	
	// Number of frames the animation lasts
	protected int duration;
	protected int count;
	protected double direction;
	protected float range;
	
	public Motion(double timeInSeconds, double direction, float range) {
		this.duration = (int) (timeInSeconds * FrostFire.FPS);
		this.direction = direction;
		this.range = range;
		this.count = 0;
	}
	
	public boolean hasNext() {
		return count < duration;
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
