package games.indie.frostfire.world;

import org.newdawn.slick.Renderable;
import org.newdawn.slick.geom.Point;
import org.newdawn.slick.geom.Vector2f;

import games.indie.frostfire.FrostFire;

/**
 * The Camera class is responsible for translating Map Coords to screen positions
 * 
 * @author Wesley Barlow
 */
public class Camera {
	
	private static Point center;
	
	public static void setCenter(float...coords) {
		Camera.center = new Point(coords[0], coords[1]);
	}
	
	public static void draw(Renderable sprite, float x, float y) {
		drawToScreen(sprite, onScreen(x, y));
	}
	public static void draw(Renderable sprite, Vector2f location) {
		drawToScreen(sprite, onScreen(location.getX(), location.getY()));
	}
	
	public static Vector2f onScreen(float x, float y) {
		return new Vector2f(FrostFire.NATIVE_WIDTH/2 + x - center.getX(), 
				FrostFire.NATIVE_HEIGHT/2 + center.getY() - y);
	}
	
	public static void drawToScreen(Renderable sprite, Vector2f position) {
		sprite.draw(position.getX(), position.getY());
	}
	
}
