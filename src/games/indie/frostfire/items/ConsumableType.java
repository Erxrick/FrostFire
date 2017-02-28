package games.indie.frostfire.items;

public enum ConsumableType {
	STRAWBERRY(1, "strawberry");
	
	private int weight;
	private String itemName;
	
	private ConsumableType(int weight, String itemName) {
		this.weight = weight;
		this.itemName = itemName;
	}
	
	public String getItemName() {
		return itemName;
	}
	
	public int getWeight() {
		return weight;
	}

}
