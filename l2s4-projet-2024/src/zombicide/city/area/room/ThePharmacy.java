package zombicide.city.area.room;

import zombicide.item.careItem.HealingFiask;
import zombicide.util.Color;

/**
 * Represents the special room "The Pharmacy" in the game.
 */
public class ThePharmacy extends Room {
    private static final char name = 'P';
    private static final String greenBgBrightCode = Color.GREEN_BG_BRIGHT.getCode();


    /**
     * Constructs a new ThePharmacy room object with the specified position.
     *
     * @param x The X-coordinate position of the room.
     * @param y The Y-coordinate position of the room.
     */
    public ThePharmacy(int x, int y) {
        super(x, y);
    }

    @Override
    protected String getName() {
    	return greenBgBrightCode + blackBoldColorCode + name + resetColorCode;
    }

    public void addHealingFiask(){
        this.addItem(new HealingFiask());
    }

    @Override
    public boolean isPharmacy() {
        return true;
    }

}
