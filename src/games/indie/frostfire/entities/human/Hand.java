package games.indie.frostfire.entities.human;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Line;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.openal.Audio;

import games.indie.frostfire.Resource;
import games.indie.frostfire.entities.human.Action.ActionType;
import games.indie.frostfire.motion.Motion;
import games.indie.frostfire.motion.Punch;
import games.indie.frostfire.world.Camera;

public class Hand extends BodyPart {
	
	private Audio punchSound = Resource.loadSound("res/audio/step.wav");
	
	protected Motion currentMotion;
	protected Vector2f offset;
	protected ActionType handUsed;
	protected Line interaction;

	public Hand(Human human, ActionType handUsed) {
		super(human);
		this.handUsed = handUsed;
		this.offset = new Vector2f();
	}
	
	public Vector2f getOffset() {
		return offset;
	}
	
	public void punch() {
		if (currentMotion == null) {
			if (getEquipped() == null) {
				punchSound.playAsSoundEffect(1, 1, false);
				body.setAction(handUsed, body.head.getSightAngle());
				currentMotion = new Punch(this);
			} else {
				System.out.println("Holding item");
			}
		}
	}
	
	public Vector2f getLocation() {
		return body.getLocation().add(offset);
	}
	
	public void update(int delta) {
		if (currentMotion != null) {
			if (currentMotion.hasNext()) {
				interaction = new Line(getLocation(), getLocation().add(currentMotion.generate(delta)));
				body.getWorld().hit(body, interaction);
			} else {
				currentMotion = null;
				interaction = null;
				body.setAction(ActionType.IDLE, body.getDirection());
			}
		}
	}
	
	public void draw() {
		if (getEquipped() != null) {
			Camera.draw(getEquipped().getShow(), getLocation().add(new Vector2f(0, 9)));
		}
	}
	
	public void debug_draw(Graphics screen) {
		if (interaction != null) {
			screen.setColor(Color.red);
			screen.draw(Camera.onScreen(interaction));
		}
	}

}
