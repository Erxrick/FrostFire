package games.indie.frostfire.entities;

public class CocoPlant extends Prop {

	public CocoPlant() {
		super("coconut-plant");
		collision = new Box(this, 10, 3, 2, -15);
	}

}
