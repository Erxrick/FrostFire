package games.indie.frostfire.items;

import games.indie.frostfire.world.Coord;
import games.indie.frostfire.world.Line;

/**
 * 
 * @author Wesley Barlow
 *
 */
public class Motion {
	
	enum MotionType {
		STAB,
		SLASH,
		SWING,
		SPIN
	}
	
	private Line[] action;
	
	public Motion(Coord origin, MotionType type, double direction, int duration, double range) {
		
	}

}
