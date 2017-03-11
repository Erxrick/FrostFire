package games.indie.frostfire.items;

import org.newdawn.slick.geom.Vector2f;

import games.indie.frostfire.Resource;
import games.indie.frostfire.entities.human.BodyPart;
import games.indie.frostfire.entities.human.Hand;
import games.indie.frostfire.motion.Swing;

public class Axe extends Tool {

	public Axe() {
		super(10, "axe");
		setHoldingOffset(new Vector2f(0, 9));
	}

	public void stateChange(BodyPart holder) {
		System.out.println("State changed!");
	}

	public void use(Hand hand, double direction) {
		hand.setCurrentMotion(new Swing(300, direction, 10));
		hand.getBody().setAction(hand.getSide(), direction);
		Resource.getSound("swing").playAsSoundEffect(1, 1, false);
	}

}
