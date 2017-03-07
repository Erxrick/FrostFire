package games.indie.frostfire.entities;

import java.util.Stack;

import games.indie.frostfire.Resource;
import games.indie.frostfire.items.Consumable;
import games.indie.frostfire.items.ConsumableType;
import games.indie.frostfire.items.Twig;

public class Bush extends Plant {
	
	protected Stack<Consumable> berries;
	protected int time;
	
	public Bush() {
		setIcon(Resource.getImage("bush"));
		setCollision(2, -10, 12, 6);
		berries = new Stack<>();
	}
	
	public void die() {
		world.place(new Twig(), x, y);
		super.die();
	}
	
	public void update(int delta) {
		time += delta;
		if (shouldGrowBerry()) {
			time = 0;
			Consumable berry = new Consumable(ConsumableType.STRAWBERRY);
			berry.setLocation(x + (float) Math.random() * (width - berry.getWidth()), y - (float) Math.random() * (height - berry.getHeight()));
			berries.push(berry);
		}
	}
	
	private boolean shouldGrowBerry() {
		return time > 10000 && berries.size() < 3;
	}
	
	public void draw() {
		super.draw();
		for (Consumable berry : berries) {
			berry.draw();
		}
	}
	
	public void interaction(Interactor hand) {
		while (!berries.isEmpty()) {
			Consumable berry = berries.pop();
			world.place(berry, berry.getX(), berry.getY() - 8);
		}
	}

}
