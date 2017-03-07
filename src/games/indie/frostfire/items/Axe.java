package games.indie.frostfire.items;

import games.indie.frostfire.Resource;
import games.indie.frostfire.entities.human.Hand;
import games.indie.frostfire.motion.Swing;

public class Axe extends Tool {

	public Axe() {
		super(10, "axe");
	}

	public void stateChange() {
		System.out.println("State changed!");
	}

	public void use(Hand hand, double direction) {
		hand.setCurrentMotion(new Swing(300, direction, 10));
		hand.getBody().setAction(hand.getSide(), direction);
		Resource.getSound("swing").playAsSoundEffect(1, 1, false);
	}

}
