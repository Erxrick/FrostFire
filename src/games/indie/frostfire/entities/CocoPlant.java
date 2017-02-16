package games.indie.frostfire.entities;

public class CocoPlant extends Prop {

	public CocoPlant() {
		super("res/images/entities/coconut-plant.png");
		collision = new Box(this, 10, 3, 2, -15);
	}

}
