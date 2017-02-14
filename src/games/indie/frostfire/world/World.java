package games.indie.frostfire.world;

import java.util.ArrayList;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Rectangle;

import games.indie.frostfire.Drawable;
import games.indie.frostfire.entities.Entity;
import games.indie.frostfire.entities.Tree;

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
	}
	
	public void place(Entity e) {
		entities.add(e);
	}
	
	public void update() {
		// TODO use EntityMoveListener and sort more efficiently
		entities.sort(null);
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
			screen.setColor(new Color(212, 57, 78, 150));
			screen.fill(new Rectangle(
					position.getX() + entity.getCollision().getOffset_x(),
					position.getY() + entity.getCollision().getOffset_y(), 
					entity.getCollision().getWidth(), entity.getCollision().getHeight()));
		}
	}

}
