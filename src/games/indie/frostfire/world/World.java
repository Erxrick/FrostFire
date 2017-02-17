package games.indie.frostfire.world;

import java.util.ArrayList;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Rectangle;

import games.indie.frostfire.Drawable;
import games.indie.frostfire.entities.*;

public class World implements Drawable {
	
	private long seed;
	private ArrayList<Entity> entities;
	
	public World() {
		entities = new ArrayList<>();
		// TODO generate random seed
		generate(0);
	}
	
	public void generate(long seed) {
		Tree tree = new Tree();
		tree.setLocation(new Coord(80, 80));
		entities.add(tree);
		Tree tree2 = new Tree();
		tree2.setLocation(new Coord(-48, 0));
		entities.add(tree2);
		CocoPlant c = new CocoPlant();
		c.setLocation(new Coord(0, -30));
		entities.add(c);
	}
	
	public void place(Entity e) {
		entities.add(e);
	}
	
	public void update() {
		// TODO use EntityMoveListener and sort more efficiently
		entities.sort(null); // This sorts for z-layer order drawing
	}

	public void draw() {
		for (Entity entity : entities) {
			entity.draw();
		}
	}
	
	public void debug_draw(Graphics screen) {
		for (Entity entity : entities) {
			Coord position = Camera.onScreen(entity.getLocation());
			screen.setColor(new Color(255, 255, 255, 50));
			screen.fill(new Rectangle(position.getX(), position.getY(), entity.getWidth(), entity.getHeight()));
			if (entity.getCollision() != null) {
				screen.setColor(new Color(212, 57, 78, 150));
				screen.fill(new Rectangle(
						position.getX() + entity.getCollision().getOffset_x(),
						position.getY() - entity.getCollision().getOffset_y(), 
						entity.getCollision().getWidth(), entity.getCollision().getHeight()));
			}
		}
	}
	
	public boolean testMove(Entity entity, Coord simulatedLocation) {
		Coord[] points = entity.getCollision().getEdges(simulatedLocation);
		for (Entity e : entities) {
			if (e == entity || e.getCollision() == null)
				continue;
			for (Coord point : points) {
				if (e.getCollision().contains(point)) {
					return false;
				}
			}
		}
		return true;
	}
}
