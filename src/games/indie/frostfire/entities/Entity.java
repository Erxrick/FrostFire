package games.indie.frostfire.entities;

import java.util.ArrayList;

import games.indie.frostfire.Drawable;
import games.indie.frostfire.entities.Action.ActionType;
import games.indie.frostfire.world.Coord;
import games.indie.frostfire.world.Direction;

public abstract class Entity implements Drawable, Comparable<Entity> {
	
	protected Coord location;
	protected Box collision;
	private int width;
	private int height;
	private ArrayList<EntityMoveListener> listeners;
	
	public Entity() {
		listeners = new ArrayList<>();
		setLocation(new Coord());
	}
	
	public Coord center() {
		return new Coord(location.getX() + width/2, location.getY() + height/2);
	}
	
	public void move(Direction direction, float distance) {
		// TODO temporary ActionListener --improve this hacky code
		((Human) this).setAction(ActionType.MOVE, direction);
		move(direction.getAngle(), distance);
	}

	public void move(double degrees, float distance) {
		float x_component = (float) (Math.cos(Math.toRadians(degrees)) * distance);
		float y_component = (float) (Math.sin(Math.toRadians(degrees)) * distance);
		boolean move_causes_collision = false;
		
		if (!move_causes_collision) {
			setLocation(new Coord(location.getX() + x_component, location.getY() + y_component));
			for (EntityMoveListener listener : listeners) {
				listener.moved(this);
			}
		}
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
	
	public Box getCollision() {
		return collision;
	}
	
	public Coord[] getEdges(Coord location) {
		return new Coord[] {
				new Coord(location.getX() + collision.getOffset_x(),
						location.getY() + collision.getOffset_y()),
				new Coord(location.getX() + collision.getOffset_x() + collision.getWidth(), 
						location.getY() + collision.getOffset_y()),
				new Coord(location.getX() + collision.getOffset_x(), 
						location.getY() + collision.getOffset_y() + collision.getHeight()),
				new Coord(location.getX() + collision.getOffset_x() + collision.getWidth(), 
						location.getY() + collision.getOffset_y() + collision.getHeight()),
		};
	}
	
	public int compareTo(Entity entity) {
		return (int) ((entity.getLocation().getY() - entity.getHeight()) - (location.getY() - height));
	}

}
