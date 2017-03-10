package games.indie.frostfire.entities.human;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Line;
import org.newdawn.slick.geom.Vector2f;

import games.indie.frostfire.entities.*;
import games.indie.frostfire.entities.human.Action.ActionType;
import games.indie.frostfire.items.Axe;
import games.indie.frostfire.motion.Motion;
import games.indie.frostfire.motion.Punch;

public class Hand extends BodyPart implements Interactor {
	
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
			Vector2f actionOffset = body.getHead().getRealOffset().negate();
			Vector2f actualLocation = getLocation().add(equipped.getHoldingOffset());
			body.getWorld().getCamera().draw(equipped.getShow(), actionOffset.x + actualLocation.x, actionOffset.y + actualLocation.y);
		}
	}
	
	public void debug_draw(Graphics screen) {
		if (interaction != null) {
			screen.setColor(Color.red);
			screen.draw(body.getWorld().getCamera().onScreen(interaction));
		}
	}

	public Line getLine() {
		return interaction;
	}

	public void interact(Entity entity) {
		entity.interaction(this);
		if (equipped instanceof Axe) {
			if (entity instanceof Tree || entity instanceof TreeStump) {
				entity.takeDamage(25);
				endMotion();
			} else {
				entity.takeDamage(15);
			}
		} else {
			entity.takeDamage(10);
			endMotion();
		}
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
