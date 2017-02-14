package games.indie.frostfire.entities;

import games.indie.frostfire.world.Coord;

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
				new Coord(location.getX() + offset_x,
						location.getY() + offset_y),
				new Coord(location.getX() + offset_x + width, 
						location.getY() + offset_y),
				new Coord(location.getX() + offset_x, 
						location.getY() + offset_y - height),
				new Coord(location.getX() + offset_x + width,
						location.getY() + offset_y - height),
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
