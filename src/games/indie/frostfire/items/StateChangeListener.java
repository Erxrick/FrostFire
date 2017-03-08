package games.indie.frostfire.items;

import games.indie.frostfire.entities.human.BodyPart;

/**
 * stateChange() called when an InventorySlot holding this Item is right clicked.
 * Use for performing an inventory function or changing a setting.
 * Ex: Change Sword motion between Stab and Swing
 * Ex: Consume consumable
 * Ex: Swap gear
 * @author Wesley Barlow
 */
public interface StateChangeListener {
	public void stateChange(BodyPart part);
}
