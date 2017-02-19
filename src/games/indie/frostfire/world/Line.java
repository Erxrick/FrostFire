package games.indie.frostfire.world;

/**
 * 
 * @author Wesley Barlow
 *
 */
public class Line {
	
	public Coord point_a, point_b;
	
	public Line(Coord point_a, Coord point_b) {
		this.point_a = point_a;
		this.point_b = point_b;
	}
	
	public Line(Coord origin, float offset_x, float offset_y) {
		this.point_a = origin;
		this.point_b = new Coord(origin.getX() + offset_x, origin.getY() + offset_y);
	}

}
