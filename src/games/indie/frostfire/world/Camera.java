package games.indie.frostfire.world;

import org.newdawn.slick.Renderable;

import games.indie.frostfire.FrostFire;

/**
 * The Camera class is responsible for translating Map Coords to screen positions
 * Static variables are used because there can only be one Camera
 * @author Wesley Barlow
 */
public class Camera {
	
	static private Coord center = new Coord();
	
	public static void setCenter(Coord center) {
		Camera.center = center;
	}
	
	public static void draw(Renderable sprite, Coord location) {
		draw(sprite, location.getX(), location.getY());
	}
	public static void draw(Renderable sprite, float x, float y) {
		sprite.draw(FrostFire.NATIVE_WIDTH/2 + x - center.getX(), FrostFire.NATIVE_HEIGHT/2 + center.getY() - y);
	}
	
	public static Coord onScreen(Coord location) {
		return new Coord(FrostFire.NATIVE_WIDTH/2 + location.getX() - center.getX(), 
				FrostFire.NATIVE_HEIGHT/2 + center.getY() - location.getY());
	}
	
}
