package games.indie.frostfire.entities.human;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Line;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.openal.Audio;

import games.indie.frostfire.Resource;
import games.indie.frostfire.entities.Entity;
import games.indie.frostfire.entities.Interactor;
import games.indie.frostfire.entities.human.Action.ActionType;
import games.indie.frostfire.motion.Motion;
import games.indie.frostfire.motion.Punch;
import games.indie.frostfire.world.Camera;

public class Hand extends BodyPart implements Interactor {
//	private Audio punchSound = Resource.loadSound("audio/punch.wav");	
	private Audio punchSound = Resource.loadSound("src/res/audio/punch.wav");
	
	protected Motion currentMotion;
	protected Vector2f offset;
	private ActionType side;
	protected Line interaction;
	protected int restTime;

	public Hand(Human human, ActionType handUsed) {
		super(human);
		this.setSide(handUsed);
		this.offset = new Vector2f();
	}
	
	public void setCurrentMotion(Motion motion) {
		currentMotion = motion;
	}
	
	public boolean canPunch() {
		return currentMotion == null && restTime <= 0;
	}
	
	public void punch() {
		if (canPunch()) {
			double useDirection = body.head.getSightAngle();
			if (getEquipped() == null) {
				body.setAction(getSide(), useDirection);
				punchSound.playAsSoundEffect(1, 1, false);
				setCurrentMotion(new Punch(this));
			} else {
				equipped.use(this, useDirection);
			}
			restTime = 800;
		}
	}
	
	public void update(int delta) {
		if (restTime > 0) {
			restTime -= delta;
		}
		if (currentMotion != null) {
			if (currentMotion.hasNext()) {
				Vector2f motionVector = currentMotion.generate(delta);
				if (equipped != null) {
					equipped.getShow().setRotation(
							(float) (-motionVector.getTheta()) + 45);
				}
				interaction = new Line(getLocation(), getLocation().add(motionVector));
				body.getWorld().checkInteraction(body, this);
			} else {
				endMotion();
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

	public Line getLine() {
		return interaction;
	}

	public void interact(Entity e) {
		System.out.println("Interaction with " + e);
		e.takeDamage(10);
		endMotion();
	}
	
	private void endMotion() {
		currentMotion = null;
		interaction = null;
		body.setAction(ActionType.IDLE, body.getDirection());
	}
	
	
	public Vector2f getOffset() {
		return offset;
	}
	
	public Vector2f getLocation() {
		return body.getLocation().add(offset);
	}

	public ActionType getSide() {
		return side;
	}

	public void setSide(ActionType side) {
		this.side = side;
	}

}
