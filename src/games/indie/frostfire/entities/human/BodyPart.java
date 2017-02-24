package games.indie.frostfire.entities.human;

import games.indie.frostfire.items.Item;

public class BodyPart {
	
	private Item equipped;
	protected int zIndex;
	protected Human body;
	
	public BodyPart(Human human) {
		this.body = human;
	}
	
	public Human getBody() {
		return body;
	}
	
	public void equip(Item item) {
		this.setEquipped(item);
	}

	public Item getEquipped() {
		return equipped;
	}

	public void setEquipped(Item equipped) {
		this.equipped = equipped;
	}

}
