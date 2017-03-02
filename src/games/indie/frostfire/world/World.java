package games.indie.frostfire.world;

import java.util.ArrayList;
import java.util.List;

import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;

import games.indie.frostfire.Drawable;
import games.indie.frostfire.entities.CocoPlant;
import games.indie.frostfire.entities.Entity;
import games.indie.frostfire.entities.Interactor;
import games.indie.frostfire.entities.Tree;
import games.indie.frostfire.entities.human.Action.ActionType;
import games.indie.frostfire.items.Axe;
import games.indie.frostfire.items.Item;
import games.indie.frostfire.multiplayer.PlayerMP;


public class World implements Drawable {
	
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
		Tree tree = new Tree();
		tree.setLocation(80, 80);
		place(tree);
		Tree tree2 = new Tree();
		tree2.setLocation(-48, 0);
		place(tree2);
		CocoPlant c = new CocoPlant();
		c.setLocation(0, -30);
		place(c);
		onGround.add(new Axe());
	}
	
	public void place(Entity e) {
		e.setWorld(this);
		entities.add(e);
	}
	
	public void update() {
		entities.sort(topDown);
	}

	public void draw() {
		for (Item item : onGround) {
			item.draw();
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

	public synchronized void movePlayer(String username, float x, float y, ActionType action, Direction direction) {
		int index = getPlayerMPIndex(username);
		getEntities().get(index).setLocation(x, y);
	}
	
    public synchronized void removePlayerMP(String username) {
        int index = 0;
        for (Entity e : getEntities()) {
            if (e instanceof PlayerMP && ((PlayerMP) e).getUsername().equals(username)) {
                break;
            }
            index++;
        }
        this.getEntities().remove(index);
    }
    
    private synchronized int getPlayerMPIndex(String username) {
        int index = 0;
        for (Entity e : getEntities()) {
            if (e instanceof PlayerMP && ((PlayerMP) e).getUsername().equals(username)) {
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
