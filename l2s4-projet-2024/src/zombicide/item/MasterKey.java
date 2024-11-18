package zombicide.item;

import zombicide.action.survivor.DoorAction;
import zombicide.actor.survivor.Survivor;

/**
 * Represents a Master Key item in the game.
 * This item allows a survivor to open a door when used.
 */
public class MasterKey extends Item {

	public MasterKey() {
		super();
		this.canOpen = true;
	}

	/**
	 * Returns a string representation of the MasterKey item.
	 *
	 * @return The string "master key".
	 */
	public String toString() {
		return "master key";
	}

	/**
	 * Uses the MasterKey item to open a door.
	 * Creates a DoorAction and performs the action using the survivor.
	 */
	public void use() {
		DoorAction doorAction = new DoorAction();
		doorAction.doSomething(this.survivor);
	}
}
