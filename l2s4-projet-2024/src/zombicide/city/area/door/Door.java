package zombicide.city.area.door;

/**
 * Represents a door on the board.
 */
public class Door {
    /** Flag indicating whether the door is open or closed. */
    private boolean isOpen;

    /**
     * Constructor for the Door class. Initializes the door as open.
     */
    public Door() {
        isOpen = true;
    }

    /**
     * Opens the door.
     */
    public void open() {
        isOpen = true;
    }

    /**
     * Closes the door.
     */
    public void close() {
        isOpen = false;
    }

    /**
     * Checks if the door is open.
     *
     * @return true if the door is open, false otherwise.
     */
    public boolean isOpen() {
        return isOpen;
    }
}
