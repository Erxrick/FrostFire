package games.indie.frostfire;

public class Coord {
	
	private float x, y;
	
	public Coord() {
		this.x = 0;
		this.y = 0;
	}
	
	public Coord(float x, float y) {
		this.x = x;
		this.y = y;
	}

	public float getX() {
		return x;
	}

	public void setX(float x) {
		this.x = x;
	}

	public float getY() {
		return y;
	}

	public void setY(float y) {
		this.y = y;
	}
	
	public void setLocation(float x, float y) {
		this.x = x;
		this.y = y;
	}
	
	public Coord midpoint(Coord coord) {
		return new Coord((this.x + coord.x)/2, (this.y + coord.y)/2);
	}
	
	public double directionTo(Coord coord) {
		double angle = Math.atan2(-(this.y - coord.y), this.x - coord.x);
	    if (angle < 0)
	        angle = 2 * Math.PI + angle;
	    return Math.toDegrees(angle);
	}
	
	public String toString() {
		return "(" + x + ", " + y + ")";
	}
	
}
