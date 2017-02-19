package games.indie.frostfire.entities;

import games.indie.frostfire.world.Coord;
import games.indie.frostfire.world.Line;

public class Box {
	
	private Entity entity;
	private int offset_x, offset_y;
	private int width, height;
	
	public Box(Entity entity) {
		this.entity = entity;
		this.width = entity.getWidth();
		this.height = entity.getHeight();
		offset_x = 0;
		offset_y = 0;
	}
	
	public Box(Entity entity, int width, int height, int offset_x, int offset_y) {
		this.entity = entity;
		this.width = width;
		this.height = height;
		this.offset_x = offset_x;
		this.offset_y = offset_y;
	}
	
	public Coord[] getEdges(Coord location) {
		return new Coord[] {
				// top-left
				new Coord(location.getX() + offset_x,
						location.getY() + offset_y),
				// top-right
				new Coord(location.getX() + offset_x + width, 
						location.getY() + offset_y),
				// bottom-right
				new Coord(location.getX() + offset_x + width,
						location.getY() + offset_y - height),
				// bottom-left
				new Coord(location.getX() + offset_x, 
						location.getY() + offset_y - height),
		};
	}
	
	public Line[] getLines(Coord location) {
		Coord[] edges = getEdges(location);
		return new Line[] {
				// top
				new Line(edges[0], edges[1]),
				// right
				new Line(edges[1], edges[2]),
				// bottom
				new Line(edges[2], edges[3]),
				// left
				new Line(edges[0], edges[3])
		};
	}
	
	public float getX() {
		return entity.getLocation().getX() + offset_x;
	}
	
	public float getY() {
		return entity.getLocation().getY() + offset_y;
	}
	
	public boolean contains(Coord point) {
		if (point.getX() < getX())
			return false;
		if (point.getX() > getX() + width)
			return false;
		if (point.getY() > getY())
			return false;
		if (point.getY() < getY() - height)
			return false;
		return true;
	}
	
	public boolean contains(Line line) {
		
		return true;
	}
	
	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public int getOffset_x() {
		return offset_x;
	}

	public void setOffset_x(int offset_x) {
		this.offset_x = offset_x;
	}

	public int getOffset_y() {
		return offset_y;
	}

	public void setOffset_y(int offset_y) {
		this.offset_y = offset_y;
	}

}
