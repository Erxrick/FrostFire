package games.indie.frostfire.entities.human;

import java.util.HashMap;

import org.newdawn.slick.Image;
import org.newdawn.slick.SpriteSheet;

import games.indie.frostfire.Resource;
import games.indie.frostfire.world.Camera;
import games.indie.frostfire.world.Direction;

public class Head extends BodyPart {
	
	private double sightAngle;
	private HashMap<Direction, Image> headMap;
	private Image currentState;
	
	Head(Human body) {
		super(body);
		SpriteSheet headSprites = new SpriteSheet(Resource.get("head-sprites"), 16, 16);
		headMap = new HashMap<>();
		for (int i = 0; i < headSprites.getHorizontalCount(); i++)
			headMap.put(Direction.values()[i], headSprites.getSprite(i, 0));
		headMap.put(Direction.NORTH_WEST, headSprites.getSprite(3, 0).getFlippedCopy(true, false));
		headMap.put(Direction.WEST, headSprites.getSprite(2, 0).getFlippedCopy(true, false));
		headMap.put(Direction.SOUTH_WEST, headSprites.getSprite(1, 0).getFlippedCopy(true, false));
	}

	public void draw() {
		Camera.draw(headMap.get(Direction.towards(sightAngle)), 
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
