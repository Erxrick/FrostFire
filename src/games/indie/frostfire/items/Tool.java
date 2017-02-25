package games.indie.frostfire.items;

public abstract class Tool extends Item {
	
	protected int durability;

	public Tool(int weight, String path) {
		super(weight, path);
		this.durability = 100;
	}

}
