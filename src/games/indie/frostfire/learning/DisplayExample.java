package games.indie.frostfire.learning;
import org.lwjgl.LWJGLException;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
 
public class DisplayExample {
 
    public void start() {
        try {
	    Display.setDisplayMode(new DisplayMode(800,600));
	    Display.create();
	    Display.setResizable(true);
	} catch (LWJGLException e) {
	    e.printStackTrace();
	    System.exit(0);
	}
 
	// init OpenGL here
 
	while (!Display.isCloseRequested()) {
 
		while (Keyboard.next()) {
		   System.out.println(Keyboard.getEventCharacter() + " " + Keyboard.getEventKeyState());
		}

	    Display.update();
	}
 
	Display.destroy();
    }
 
    public static void main(String[] argv) {
        DisplayExample displayExample = new DisplayExample();
        displayExample.start();
    }
}