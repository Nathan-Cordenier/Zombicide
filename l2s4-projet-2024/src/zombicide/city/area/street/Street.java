package zombicide.city.area.street;

import zombicide.city.area.Area;
import zombicide.util.Color;

/**
 * Represents a street area on the game board.
 */
public class Street extends Area {
    private static final char name = 'S';
    private static Street spawn;
    private static final String blueBoldCode = Color.BLUE_BOLD.getCode();

    /**
     * Constructs a new Street object with the specified position.
     *
     * @param posX The X-coordinate position of the street.
     * @param posY The Y-coordinate position of the street.
     */
    public Street(int posX, int posY) {
        super(posX, posY);
    }

    @Override
    protected String getName() {
        return blueBoldCode + name + resetColorCode;
    }

    public static void setSpawn(Street spawn) {
        Street.spawn = spawn;
    }
}
