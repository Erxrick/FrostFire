package games.indie.frostfire.user.ui;

import games.indie.frostfire.Resource;

public class Button extends UIComponent {
	
	public Button(int screenX, int screenY, String imageName) {
		setIcon(Resource.getImage(imageName));
		setLocation(screenX, screenY);
	}

}
