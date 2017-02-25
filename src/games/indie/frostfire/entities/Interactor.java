package games.indie.frostfire.entities;

import org.newdawn.slick.geom.Line;

public interface Interactor {
	
	public Line getLine();
	public void interact(Entity hit);

}
