package tester;


import static org.lwjgl.glfw.GLFW.*;
import org.lwjgl.opengl.*;


import static org.lwjgl.opengl.GL11.*;



public class TestingWindowDriver {

	public TestingWindowDriver() {
		if(!glfwInit()) {
			System.err.println("GLFW failed to intialize!");
			System.exit(1);
		}
		
		long monitor = glfwGetPrimaryMonitor();
		
		long window = glfwCreateWindow(360 * 8, 180 * 8, "Window", monitor, 0);
		glfwShowWindow(window);
		glfwMakeContextCurrent(window);
		GL.createCapabilities();
		
		glEnable(GL_TEXTURE_2D);
		
		tester.Texture tex = new tester.Texture("./resources/images/body0.png");
		
		float x = 0;
		
		while(!glfwWindowShouldClose(window)) {
			if(glfwGetKey(window, GLFW_KEY_ESCAPE) == GL_TRUE) {
				glfwSetWindowShouldClose(window, true);
			}
			if (glfwGetKey(window, GLFW_KEY_A) == GL_TRUE) {
				x += 0.0001f;
				System.out.println(x);
			}
			
			glfwPollEvents();
			
			glClear(GL_COLOR_BUFFER_BIT);
			
			tex.bind();
			glBegin(GL_QUADS);
				glTexCoord2f(0, 0);
				glVertex2d(-0.5f, 0.5f);
				
				glTexCoord2f(1, 0);
				glVertex2d(0.5f, 0.5f);
				
				glTexCoord2f(1, 1);
				glVertex2d(0.5f, -0.5f);
				
				glTexCoord2f(0, 1);
				glVertex2d(-0.5f, -0.5f);
			glEnd();
				
			glfwSwapBuffers(window);
		}
		
		glfwTerminate();
	}
	public static void main(String[] args) {
		new TestingWindowDriver();
	}
	
	
	
	
	
	
}
