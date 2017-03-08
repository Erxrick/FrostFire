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
	
	public World() {
		topDown = new ZLayerSort();
		entities = new CopyOnWriteArrayList<>();
		onGround = new ArrayList<>();
		generate(0);
	}
	
	public void generate(int seed) {
		PerlinNoiseGenerator p = new PerlinNoiseGenerator(seed);//generates float with 9 digits following decimal.
		Random rand = new Random(seed);
		for(float x=0;x<1;x+=0.01){
			for(float y=0;y<1;y+=0.01){
				double noise = Math.abs(p.noise2(x, y));
				System.out.println(noise);
				int posNeg = rand.nextInt(2);
				if(rand.nextDouble()>0.33){
					System.out.println("in entity statements");
					if(noise>0.4 && noise<0.49) {
						place(new Crystal(), findXorY(x, posNeg), findXorY(y, posNeg));
					} else if(noise>0.5 && noise<0.59) {
						place(new Stone(), findXorY(x, posNeg), findXorY(y, posNeg));
					} else if(noise>0.6 && noise<0.69 || noise>0.9 && noise<1) {
						place(new CocoPlant(), findXorY(x, posNeg), findXorY(y, posNeg));
					} else if(noise>0.7 && noise<0.79 || noise>0.2 && noise<0.29) {
						place(new Bush(), findXorY(x, posNeg), findXorY(y, posNeg));
					} else if(noise>0.8 && noise<0.89 || noise>0.1 && noise<0.19) {
						place(new Tree(), findXorY(x, posNeg), findXorY(y, posNeg));
					}  else if(noise>0.3 && noise<0.39 || noise>0 && noise<0.09) {
						place(new Mushroom(), findXorY(x, posNeg), findXorY(y, posNeg));
					}
				}
			}
		}
	}
	
	public float findXorY(float x, int rand){
		float result = 0;
		if(rand == 0){
			result = x*1600;
		} else {
			result = (0-x)*1600;
		}
		return result;
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

	public synchronized void movePlayer(long l, float x, float y, ActionType action, Direction direction) {
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
