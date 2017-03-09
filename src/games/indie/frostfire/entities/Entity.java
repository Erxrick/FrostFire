package games.indie.frostfire.entities;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Vector2f;

import games.indie.frostfire.FrostFire;
import games.indie.frostfire.Sprite;
import games.indie.frostfire.entities.stats.Health;
import games.indie.frostfire.multiplayer.PlayerMP;
import games.indie.frostfire.multiplayer.packets.Packet04Damage;
import games.indie.frostfire.multiplayer.packets.Packet05Death;
import games.indie.frostfire.multiplayer.packets.Packet06MPDamage;
import games.indie.frostfire.multiplayer.packets.Packet07MPDeath;
import games.indie.frostfire.world.Box;
import games.indie.frostfire.world.Camera;
import games.indie.frostfire.world.World;

public abstract class Entity extends Sprite {
	
	// x and y of collision relative to this location
	protected Box collision;
	protected Health health;
	protected float direction;
	protected World world;
	private int ID;
	
	public Entity() {
		health = new Health(this, 0, 100);
	}
	
	public int getID() {
		return ID;
	}
	
	public void setID(int ID) {
		this.ID = ID;
	}
	
	public World getWorld() {
		return world;
	}
	
	public void setWorld(World world) {
		this.world = world;
	}
	
	public void draw() {
		Camera.draw(icon, x, y);
	}
	
	public String toString() {
		return this.getClass().getSimpleName() + " at " + getLocation();
	}
	
	public void debug_draw(Graphics screen) {
		screen.setColor(new Color(255, 255, 255, 30));
		Vector2f position = Camera.onScreen(x, y);
		screen.fillRect(position.getX(), position.getY(), width, height);
		screen.setColor(new Color(200, 100, 100, 150));
		position = Camera.onScreen(collision.getX(), collision.getY());
		screen.fillRect(position.getX(), position.getY(), collision.getWidth(), collision.getHeight());
	}

	public Box getCollision() {
		return collision;
	}

	public void setCollision(float offset_x, float offset_y, float width, float height) {
		collision = new Box(this, offset_x, offset_y, width, height);
	}
	
	public synchronized void die() {
		System.out.println(this + " DIED!");
	//	System.out.println(this + " DIED!");
		if(this instanceof PlayerMP) {
			PlayerMP player = (PlayerMP) this;
			Packet07MPDeath deathpacket = new Packet07MPDeath(player.getUsername());
			deathpacket.writeData(FrostFire.multiplayer.getClient());
		} else {
		Packet05Death deathpacket = new Packet05Death(this.ID);
		deathpacket.writeData(FrostFire.multiplayer.getClient());
		}
		world.getEntities().remove(this);
	}
	
	public void takeDamage(double damage) {
		//send a damage packet here
		health.affect(-damage);
		if(this instanceof PlayerMP) {
			PlayerMP player = (PlayerMP) this;
			Packet06MPDamage dmgpacket = new Packet06MPDamage((player.getUsername()), this.getHealth());
			dmgpacket.writeData(FrostFire.multiplayer.getClient());
		} else {
			Packet04Damage dmgpacket = new Packet04Damage(this.ID, this.getHealth());
			dmgpacket.writeData(FrostFire.multiplayer.getClient());
		}
	}
	public double getHealth() {
		return this.health.getHealth();
	}
	public void setHealth(double health) {
		this.health.setHp(health);
	}

}
