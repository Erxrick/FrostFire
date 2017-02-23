package games.indie.frostfire.entities.human;

import games.indie.frostfire.entities.Creature;
import games.indie.frostfire.entities.Stat;

public class Human extends Creature {
	
	protected Stat hunger;
	protected Stat thirst;
	
	protected Head head = new Head(this);
	protected Hand rightHand = new Hand(this);
	protected Hand leftHand = new Hand(this);
	protected Chest chest = new Chest(this);
	protected Legs legs = new Legs(this);
	protected Feet feet = new Feet(this);
	
	public Human() {
		setSize(16, 16);
		setCollision(2, -12, 12, 4);
	}
	
	public Head getHead() {
		return head;
	}
	public Hand getRightHand() {
		return rightHand;
	}
	public Hand getLeftHand() {
		return leftHand;
	}
	public Chest getChest() {
		return chest;
	}
	public Legs getLegs() {
		return legs;
	}
	public Feet getFeet() {
		return feet;
	}
	
	public void draw() {
		
	}
	
}
