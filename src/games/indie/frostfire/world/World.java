package games.indie.frostfire.world;

import java.util.ArrayList;
import java.util.List;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.geom.Vector2f;

import games.indie.frostfire.entities.Entity;
import games.indie.frostfire.entities.Interactor;
import games.indie.frostfire.entities.Tree;
import games.indie.frostfire.entities.human.Action.ActionType;
import games.indie.frostfire.items.Axe;
import games.indie.frostfire.items.Item;
import games.indie.frostfire.multiplayer.PlayerMP;


public class World {
	
	public static Camera camera = new Camera();
	private long seed;
	private ZLayerSort topDown;
	public ArrayList<Entity> entities;
	public ArrayList<Item> onGround;
	
	public World() {
		topDown = new ZLayerSort();
		entities = new ArrayList<>();
		onGround = new ArrayList<>();
		generate(0);
	}
	
	public void generate(long seed) {
		place(new Tree(), 80, 80);
		onGround.add(new Axe());
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
	
	public void update() {
		entities.sort(topDown);
	}

	public void draw(Graphics screen) {
		for (Item item : onGround) {
			Image showOnMap = item.getShow().copy();
			showOnMap.setAlpha(.9f);
			Vector2f position = Camera.onScreen(item.getX(), item.getY());
			screen.setColor(new Color(40, 85, 138));
			screen.fillOval(position.getX() + item.getWidth()/4, position.getY() + item.getHeight() + 2, 
					item.getWidth()/2, item.getHeight()/4);
			Camera.draw(showOnMap, item.getLocation());
		}
		for (Entity entity : entities) {
			entity.draw();
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

	public synchronized void movePlayer(long l, float x, float y, ActionType action, Direction direction) {
		int index = getPlayerMPIndex(l);
		getEntities().get(index).setLocation(x, y);
	}
	
    public synchronized void removePlayerMP(long username) {
        int index = 0;
        for (Entity e : getEntities()) {
            if (e instanceof PlayerMP && ((PlayerMP) e).getUsername() == username) {
                break;
            }
            index++;
        }
        this.getEntities().remove(index);
    }
    
    private synchronized int getPlayerMPIndex(long username) {
        int index = 0;
        for (Entity e : getEntities()) {
            if (e instanceof PlayerMP && ((PlayerMP) e).getUsername() == username) {
                break;
            }
            index++;
        }
        return index;
    }
    
    public synchronized List<Entity> getEntities() {
        return this.entities;
     
    }
}
