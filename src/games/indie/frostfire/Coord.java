package games.indie.frostfire;

public class Coord {
	
	private int x, y;
	
	public Coord() {
		this.x = 0;
		this.y = 0;
	}
	
	public Coord(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}
	
	public void setLocation(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public Coord midpoint(Coord coord) {
		return new Coord((this.x + coord.x)/2, (this.y + coord.y)/2);
	}
	
}
