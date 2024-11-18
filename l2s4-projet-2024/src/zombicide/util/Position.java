package zombicide.util;

/**
 * Represents a position on a grid.
 */
public class Position {
    /** The x-coordinate of the position. */
    private int x;

    /** The y-coordinate of the position. */
    private int y;

    /**
     * Constructor for creating a position with given x and y coordinates.
     *
     * @param x The x-coordinate of the position.
     * @param y The y-coordinate of the position.
     */
    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Gets the x-coordinate of the position.
     *
     * @return The x-coordinate.
     */
    public int getX() {
        return this.x;
    }

    /**
     * Gets the y-coordinate of the position.
     *
     * @return The y-coordinate.
     */
    public int getY() {
        return this.y;
    }
}
