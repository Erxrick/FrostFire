package games.indie.frostfire.user.ui;

import org.newdawn.slick.Font;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.gui.GUIContext;
import org.newdawn.slick.gui.TextField;

import games.indie.frostfire.Resource;

public class TextEntry extends UIComponent {
	
	private static Font standard = Resource.loadFont("res/input.fnt", Resource.getImage("input_0"));
	private TextField box;
	private GUIContext container;
	
	public TextEntry(GUIContext container, int x, int y) {
		this.container = container;
		setLocation(x, y);
		setIcon(Resource.getImage("text-entry"));
		box = new TextField(container, standard, x + 8, y + 2, (int) width - 16, (int) height);
		box.setBackgroundColor(null);
		box.setBorderColor(null);
		box.setCursorVisible(true);
		box.setFocus(true);
	}
	
	public TextField getBox() {
		return box;
	}
	
	public void draw(Graphics screen) {
		super.draw();
		box.render(container, screen);
	}

}
