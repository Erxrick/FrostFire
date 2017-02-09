package games.indie.frostfire;

import java.util.ArrayList;

public class Inventory extends ArrayList<Item> {
	
	public int getWeight() {
		int weight = 0;
		for (Item i : this) {
			weight += i.getWeight();
		}
		return weight;
	}
	
}
