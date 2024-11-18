package zombicide.util;

/**
 * Enumeration representing directions.
 */
public enum Direction {
    UP(0, -1),
    RIGHT(1, 0),
    DOWN(0, 1),
    LEFT(-1, 0);

    private final int x;
    private final int y;
    private Direction reverse;  // Reverse direction for each direction

    /**
     * Constructs a Direction with the given x and y coordinates.
     *
     * @param x The x coordinate for the direction.
     * @param y The y coordinate for the direction.
     */
    Direction(int x, int y) {
        this.x = x;
        this.y = y;
    }

    static {
        UP.reverse = DOWN;
        RIGHT.reverse = LEFT;
        DOWN.reverse = UP;
        LEFT.reverse = RIGHT;
    }

    /**
     * Gets the x coordinate of the direction.
     *
     * @return The x coordinate.
     */
    public int getX() {
        return x;
    }

    /**
     * Gets the y coordinate of the direction.
     *
     * @return The y coordinate.
     */
    public int getY() {
        return y;
    }

    /**
     * Gets the reverse direction of this direction.
     *
     * @return The reverse direction.
     */
    public Direction getReverse() {
        return this.reverse;
    }
}
