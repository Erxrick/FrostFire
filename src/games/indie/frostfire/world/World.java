package games.indie.frostfire.world;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CopyOnWriteArrayList;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.geom.Vector2f;

import games.indie.frostfire.entities.Bush;
import games.indie.frostfire.entities.CocoPlant;
import games.indie.frostfire.entities.Crystal;
import games.indie.frostfire.entities.Entity;
import games.indie.frostfire.entities.Interactor;
import games.indie.frostfire.entities.Mushroom;
import games.indie.frostfire.entities.Stone;
import games.indie.frostfire.entities.Tree;
import games.indie.frostfire.entities.human.Action.ActionType;
import games.indie.frostfire.items.Item;
import games.indie.frostfire.multiplayer.PlayerMP;


public class World {
	
	public static Camera camera = new Camera();
	private int seed = 200;
	private ZLayerSort topDown;
	private List<Entity> entities;
	public ArrayList<Item> onGround;
	private int entityCount;
	
	public World(int seed) {
		topDown = new ZLayerSort();
		entities = new CopyOnWriteArrayList<>();
		onGround = new ArrayList<>();
generate(seed);	}
	
	public void generate() {//reduce entities that are spawned
		ProceduralGeneration pG = new ProceduralGeneration(this);
		pG.generateWorld(this.getWorldSeed());
	}
	public int getWorldSeed(){
		return this.seed;
	}
	
	public void place(Item item, float x, float y) {
		item.setLocation(x, y);
		onGround.add(item);
	}
	
	public void place(Entity entity, float x, float y) {
		entity.setLocation(x, y);
		entity.setWorld(this);
		entityCount++;
		entity.setID(entityCount);
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
			Camera.draw(showOnMap, item.getX(), item.getY() + item.getHover());
		}
		for (Entity entity : getEntities()) {
			entity.draw();
		}
	}
	
	public void checkInteraction(Entity self, Interactor hand) {
		for (Entity entity : getEntities()) {
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
		for (Entity entity : getEntities()) {
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

	public synchronized void movePlayer(long l, float x, float y) {
		int index = getPlayerMPIndex(l);
		getEntities().get(index).setLocation(x, y);
	//	System.out.println(action);
	//	((PlayerMP) getEntities().get(index)).setAction(action, direction);
	}
	
	
	
    public synchronized void removePlayerMP(long username) {
        this.getEntities().remove(getPlayerMPIndex(username));
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
    public void setWorldSeed(int seed) {
    	this.seed = seed;
    }
}
