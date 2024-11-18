package zombicide.item;

import zombicide.actor.survivor.Survivor;

/**
 * Abstract class representing an item that can be used by a Survivor.
 */
public abstract class Item {

    /** The Survivor using the item. */
    protected Survivor survivor;

    /** Indicates if the item can open doors. */
    protected boolean canOpen;

    /** Indicates if the item can be used for attacking. */
    protected boolean canAttack;

    /** Indicates if the item is noisy when used to open a door. */
    protected boolean isNoisyDoor;

    /** Indicates if the item is noisy when used. */
    protected boolean isNoisyUse;

    /**
     * Constructor for the Item class.
     * Initializes item properties.
     */
    public Item(){
        this.survivor = null;
        this.canAttack = false;
        this.canOpen = false;
        this.isNoisyDoor = false;
        this.isNoisyUse = false;
    }

    /**
     * Sets the Survivor using the item.
     *
     * @param s The Survivor using the item.
     */
    public void setSurvivor(Survivor s){
        this.survivor = s;
    }

    /**
     * Gets the Survivor using the item.
     *
     * @return The Survivor using the item.
     */
    public Survivor getSurvivor(){
        return this.survivor;
    }

    /**
     * Abstract method to define the use of the item.
     * Each specific item type will define its own use.
     */
    public abstract void use();

    /**
     * Checks if the item can open doors.
     *
     * @return true if the item can open doors, false otherwise.
     */
    public boolean canOpen() {
        return this.canOpen;
    }

    /**
     * Checks if the item can be used for attacking.
     *
     * @return true if the item can be used for attacking, false otherwise.
     */
    public boolean canAttack() {
        return this.canAttack;
    }

    /**
     * Checks if the item is noisy when used to open a door.
     *
     * @return true if the item is noisy when used to open a door, false otherwise.
     */
    public boolean isNoisyWhenUsedToOpenDoor(){
        return this.isNoisyDoor;
    }

    /**
     * Checks if the item is noisy when used.
     *
     * @return true if the item is noisy when used, false otherwise.
     */
    public boolean isNoisyWhenUsed(){
        return this.isNoisyUse;
    }

    public abstract String toString();
}
