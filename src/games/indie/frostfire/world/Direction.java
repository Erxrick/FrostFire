package games.indie.frostfire.world;

import org.newdawn.slick.geom.Vector2f;

/**
 * Hooray!
 * 
 * @author Wesley Barlow
 *
 */
public enum Direction {
	EAST			(0),
	NORTH_EAST		(45),
	NORTH			(90),
	NORTH_WEST		(135),
	WEST			(180),
	SOUTH_WEST		(225),
	SOUTH			(270),
	SOUTH_EAST		(315);
	
	private double angle;
	
	Direction(double angle) {
		this.angle = angle;
	}
	
	public double getAngle() {
		return this.angle;
	}
	
	public Vector2f getVector() {
		return new Vector2f(angle);
	}
	
	public static Direction four(double degrees) {
		if (degrees < 0)
			degrees = 360 + (degrees % 360);
		return values()[((int) ((degrees + 45)/90)) % 4 * 2];
	}
	
	public static Direction eight(double degrees) {
		if (degrees < 0)
			degrees = 360 + (degrees % 360);
		return values()[((int) ((degrees + 22.5)/45)) % 8];
	}
	
	public static Direction opposite(Direction direction) {
		return eight(direction.getAngle() + 180);
	}
	public static Direction opposite(double degrees) {
		return eight(degrees + 180);
	}
	
}
