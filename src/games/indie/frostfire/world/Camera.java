package games.indie.frostfire.world;

import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Line;
import org.newdawn.slick.geom.Vector2f;

import games.indie.frostfire.FrostFire;

/**
 * The Camera class is responsible for translating Map Coords to screen positions
 * 
 * @author Wesley Barlow
 */
public class Camera {
	
	private static Vector2f center;
	
	public static void setCenter(float...coords) {
		Camera.center = new Vector2f(coords);
	}
	
	public static void draw(Image sprite, float x, float y) {
		drawToScreen(sprite, onScreen(x, y));
	}
	public static void draw(Image sprite, Vector2f location) {
		drawToScreen(sprite, onScreen(location.getX(), location.getY()));
	}
	
	public static Vector2f onScreen(float x, float y) {
		return new Vector2f(FrostFire.NATIVE_WIDTH/2 + x - center.getX(), 
				FrostFire.NATIVE_HEIGHT/2 + center.getY() - y);
	}
	public static Line onScreen(Line line) {
		return new Line(onScreen(line.getX1(), line.getY1())
				, onScreen(line.getX2(), line.getY2()));
	}
	
	public static void drawToScreen(Image sprite, Vector2f position) {
		sprite.draw(position.getX(), position.getY());
	}
	
}
