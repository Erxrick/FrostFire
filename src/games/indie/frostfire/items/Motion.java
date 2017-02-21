package games.indie.frostfire.items;

import org.newdawn.slick.geom.Line;
import org.newdawn.slick.geom.Point;

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
	
	public Motion(Point origin, MotionType type, double direction, int duration, double range) {
		
	}

}
