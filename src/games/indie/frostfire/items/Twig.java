package games.indie.frostfire.items;

import games.indie.frostfire.entities.human.Hand;
import games.indie.frostfire.motion.Swing;

public class Twig extends Item {

	public Twig() {
		super(2, "twig");
	}

	@Override
	public void stateChange() {
		
	}

	@Override
	public void use(Hand hand, double direction) {
		hand.setCurrentMotion(new Swing(250, direction, 10));
		hand.getBody().setAction(hand.getSide(), direction);
	}

}
