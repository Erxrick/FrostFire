package games.indie.frostfire.items;

public enum ConsumableType {
	STRAWBERRY(1, "strawberry", 1, 5, 8);
	
	private int weight;
	private String itemName;
	private float healthChange;
	private float hungerChange;
	private float thirstChange;
	
	private ConsumableType(int weight, String itemName, float healthChange, float hungerChange, float thirstChange) {
		this.weight = weight;
		this.itemName = itemName;
		this.healthChange = healthChange;
		this.hungerChange = hungerChange;
		this.thirstChange = thirstChange;
	}
	
	public String getItemName() {
		return itemName;
	}
	
	public int getWeight() {
		return weight;
	}
	
	public float getHealthChange() {
		return healthChange;
	}
	
	public float getHungerChange() {
		return hungerChange;
	}
	
	public float getThirstChange() {
		return thirstChange;
	}
	
}
