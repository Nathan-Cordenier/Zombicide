package zombicide.city.area.room;

import zombicide.util.Color;

/**
 * Represents the special room "The Continental" in the game.
 */
public class TheContinental extends Room {
    private static final char name = 'C';
    private static final String purpleBgBrightCode = Color.PURPLE_BG_BRIGHT.getCode();


    /**
     * Constructs a new TheContinental room object with the specified position.
     *
     * @param x The X-coordinate position of the room.
     * @param y The Y-coordinate position of the room.
     */
    public TheContinental(int x, int y) {
        super(x, y);
    }

    @Override
    protected String getName() {
    	return purpleBgBrightCode + blackBoldColorCode + name + resetColorCode;
    }

    /**
     * Determines if fighting is allowed in this room. Fighting is not allowed in The Continental.
     *
     * @return Always returns false, as fighting is not allowed in The Continental room.
     */

    @Override
    public boolean isContinental() {
        return true;
    }
}
