package games.indie.frostfire.world;

import java.util.ArrayList;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.geom.Vector2f;

import games.indie.frostfire.entities.*;
import games.indie.frostfire.items.*;


public class World {
	
	public Camera camera;
	private ZLayerSort topDown;
	private ArrayList<Entity> entities;
	public ArrayList<Item> onGround;
	
	public World() {
		camera = new Camera();
		topDown = new ZLayerSort();
		entities = new ArrayList<>();
		onGround = new ArrayList<>();
	}
	
	public void generate(String seed) {
		ProceduralGeneration god = new ProceduralGeneration(this);
		god.generate(seed);
		place(new Axe(), 0, 0);
	}
	
	public void place(Item item, float x, float y) {
		item.setLocation(x, y);
		onGround.add(item);
	}
	
	public void place(Entity entity, float x, float y) {
		entity.setLocation(x, y);
		entity.setWorld(this);
		entities.add(entity);
	}
	
	public void remove(Entity e) {
		entities.remove(e);
	}
	
	public void update(int delta) {
		entities.sort(topDown);
		for (int i = 0; i < entities.size(); i++) {
			entities.get(i).update(delta);
		}
	}

	public void draw(Graphics screen, boolean debug) {
		for (Entity entity : entities) {
			entity.draw();
		}
		for (Item item : onGround) {
			Image showOnMap = item.getShow().copy();
			showOnMap.setAlpha(.9f);
			Vector2f position = camera.onScreen(item.getX(), item.getY());
			screen.setColor(new Color(40, 85, 138, 100));
			screen.fillOval(position.getX() + item.getWidth()/4, position.getY() + item.getHeight() + 2, 
					item.getWidth()/2, item.getHeight()/4);
			camera.draw(showOnMap, item.getX(), item.getY() + item.getHover());
		}
		if (debug) {
			debug_draw(screen);
		}
	}
	
	public void checkInteraction(Entity self, Interactor hand) {
		for (Entity entity : entities) {
			if (entity == self)
				continue;
			if (hand.getLine().intersects(new Rectangle(
					entity.getCollision().getX(), 
					entity.getCollision().getY() - entity.getCollision().getHeight(),
					entity.getCollision().getWidth(),
					entity.getCollision().getHeight()))) {
				hand.interact(entity);
				break;
			}
		}
	}
	
	public boolean isValidMove(Entity moving, Shape changedCollision) {
		for (Entity entity : entities) {
			if (entity == moving)
				continue;
			// Don't ask
			if (changedCollision.intersects(new Rectangle(
					entity.getCollision().getX(), 
					entity.getCollision().getY() + (changedCollision.getHeight() - entity.getCollision().getHeight()),
					entity.getCollision().getWidth(),
					entity.getCollision().getHeight()))) {
				return false;
			}
		}
		return true;
	}

	public void debug_draw(Graphics screen) {
		for (Entity entity : entities) {
			entity.debug_draw(screen);
		}
	}
}
