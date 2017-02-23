package games.indie.frostfire.world;

import org.newdawn.slick.geom.Rectangle;

import games.indie.frostfire.entities.Entity;

public class Box extends Rectangle {
	
	protected Entity entity;

	public Box(Entity entity, float x, float y, float width, float height) {
		super(x, y, width, height);
		this.entity = entity;
	}
	
	@Override
	public float getX() {
		return entity.getX() + x;
	}
	
	@Override
	public float getY() {
		return entity.getY() + y;
	}

}
