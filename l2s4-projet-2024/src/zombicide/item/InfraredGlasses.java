package zombicide.item;

import zombicide.action.survivor.AreaAction;
import zombicide.actor.survivor.Survivor;

/**
 * Represents Infrared Glasses item in the game.
 * This item allows a survivor to display areas around them when used.
 */
public class InfraredGlasses extends Item {

	/**
	 * Returns a string representation of the Infrared Glasses item.
	 *
	 * @return The string "infrared glasses".
	 */
	public String toString() {
		return "infrared glasses";
	}

	/**
	 * Uses the Infrared Glasses item to display areas around the survivor.
	 * Creates an AreaAction and performs it on the survivor.
	 */
	public void use() {
		AreaAction areaAction = new AreaAction();
		areaAction.doSomething(survivor);
	}
}
