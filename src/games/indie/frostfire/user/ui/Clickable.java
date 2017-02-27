package games.indie.frostfire.user.ui;

/**
 * 
 * @author Wesley Barlow
 *
 */
public interface Clickable {
	/**
	 * Returned boolean used for overriding right and left punches
	 * @return Clickable does something because of this mouse press
	 */
	public boolean mousePressed(int button, int x, int y);
}
