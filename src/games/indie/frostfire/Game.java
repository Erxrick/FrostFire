package games.indie.frostfire;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;

public class Game {

	public void launch() {
		init();
		loop();
		dispose();
	}
	
	private void init() {
	    try {
		    Display.setDisplayMode(new DisplayMode(640,320));
		    Display.setTitle("FrostFire");
		    Display.setInitialBackground(32/255f, 144/255f, 141/255f);
		    Display.create();
		} catch (LWJGLException e) {
		    e.printStackTrace();
		    System.exit(0);
		}
	}
	
	private void loop() {
		boolean running = true;
		
		while (running) {
		    Display.update();
		    
		    if (Display.isCloseRequested())
		    	running = false;
		}
	}
	
	private void dispose() {
		Display.destroy();
	}
	
	public static void main(String[] args) {
		new Game().launch();
	}

}
