package games.indie.frostfire.entities.human;

import java.util.HashMap;

import org.newdawn.slick.Image;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.geom.Vector2f;

import games.indie.frostfire.Resource;
import games.indie.frostfire.world.Camera;
import games.indie.frostfire.world.Direction;

public class Head extends BodyPart {
	
	private double sightAngle;
	private HashMap<Direction, Image> headMap;
	protected Vector2f offset;
	
	public Head(Human body) {
		super(body);
		offset = new Vector2f(0, 8);
		SpriteSheet headSprites = new SpriteSheet(Resource.get("head-sprites"), 16, 16);
		headMap = new HashMap<>();
		for (int i = 0; i < Direction.values().length; i++)
			headMap.put(Direction.values()[i], headSprites.getSprite(i, 0));
	}

	public void draw() {
		switch (body.currentAction.getAnimation().getFrame()) {
			case 1:
			case 3:
			case 5:
			case 7:
				offset.x = 0;
				offset.y =  7;
				break;
			case 2:
				offset.x = 1;
				offset.y =  6;
				break;
			case 6:
				offset.x =  -1;
				offset.y =  6;
				break;
			default:
				offset.x = 0;
				offset.y =  8;
			}
		if (body.getDirection() == Direction.EAST)
			offset.x += 1;
		else if (body.getDirection() == Direction.WEST)
			offset.x -= 1;
		Camera.draw(headMap.get(Direction.eight(sightAngle)),
				body.getX() + offset.getX(),
				body.getY() + offset.getY());
	}

	public double getSightAngle() {
		return sightAngle;
	}

	public void setSightAngle(double sightAngle) {
		this.sightAngle = sightAngle;
	}
}
