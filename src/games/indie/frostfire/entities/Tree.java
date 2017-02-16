package games.indie.frostfire.entities;

public class Tree extends Prop {
		
	public Tree() {
		super("res/images/entities/tree.png");
		collision = new Box(this, 24, 8, 4, -23);
	}

}
