package games.indie.frostfire.items;

import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Vector2f;

import games.indie.frostfire.Resource;
import games.indie.frostfire.Sprite;

public abstract class Item extends Sprite implements StateChangeListener, Usable {
	
	protected Image show; // Displayed during motion
	protected int weight;
	protected float angle;
	private Vector2f holdingOffset;
	
	protected int maxStackSize;
	protected int quantity;
	
	protected float hover;
	protected boolean hoverUp;
	
	public Item(int weight, String itemName) {
		quantity = 1;
		maxStackSize = 1;
		this.weight = weight;
		icon = Resource.getImage(itemName);
		setSize(icon.getWidth(), icon.getHeight());
		show = icon.copy();
		show.setCenterOfRotation(2, show.getHeight() - 2);
	}
	
	public float getHover() {
		if (hoverUp) {
			hover += .1;
		} else {
			hover -= .1;
		}
		if (hover >= 2) {
			hoverUp = false;
		} else if (hover <= -2) {
			hoverUp = true;
		}
		return hover;
	}
	
	public Image getShow() {
		return show;
	}
	
	public int getWeight() {
		return weight;
	}
	
	public int getQuantity() {
		return quantity;
	}
	
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public Vector2f getHoldingOffset() {
		return holdingOffset;
	}

	public void setHoldingOffset(Vector2f holdingOffset) {
		this.holdingOffset = holdingOffset;
	}
	
	public boolean equals(Item item) {
		return this.getClass().equals(item.getClass());
	}

	public boolean roomForAdditional(int additional) {
		return quantity + additional <= maxStackSize;
	}

}
