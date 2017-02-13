package games.indie.frostfire.world;

public enum Direction {
	SOUTH			(270),
	SOUTH_EAST		(315),
	EAST			(0),
	NORTH_EAST		(45),
	NORTH			(90),
	NORTH_WEST		(135),
	WEST			(180),
	SOUTH_WEST		(225);
	
	private double angle;
	
	Direction(double angle) {
		this.angle = angle;
	}
	
	public double getAngle() {
		return this.angle;
	}
	
	public static Direction towards(double degrees) {
		degrees -= 180;
	    if (degrees < 0)
	        degrees = 360 + degrees;
		for (Direction d : values())
			if (degrees < d.angle + 22.5 && degrees > d.angle - 22.5)
				return d;
		return EAST;
	}
	
	public static Direction fit(double degrees) {
		for (Direction d : values())
			if (degrees < d.angle + 22.5 && degrees > d.angle - 22.5)
				return d;
		return WEST;
	}
	
	
}
