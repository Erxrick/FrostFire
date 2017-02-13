package games.indie.frostfire.entities;

import games.indie.frostfire.world.Coord;

public class Box {
	
	private Coord location;
	private int width, height;
	
	public Box(Entity entity) {
		this.location = entity.getLocation();
		this.width = entity.getWidth();
		this.height = entity.getHeight();
	}
	
	public Box(Entity entity, int width, int height) {
		this.location = entity.getLocation();
		this.width = width;
		this.height = height;
	}

	public Coord getLocation() {
		return location;
	}

	public void setLocation(Coord location) {
		this.location = location;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

}
