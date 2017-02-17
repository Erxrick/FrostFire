package games.indie.frostfire.entities;

public class Tree extends Prop {
		
	public Tree() {
		super("tree");
		collision = new Box(this, 24, 8, 4, -23);
	}

}
