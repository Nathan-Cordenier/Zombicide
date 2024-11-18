package zombicide.action.survivor;

import zombicide.action.Action;
import zombicide.actor.survivor.Survivor;
import zombicide.city.City;
import zombicide.city.area.Area;
import zombicide.city.area.room.Room;
import zombicide.util.Direction;

/**
 * An action representing a Survivor inspecting the areas around their current position.
 * This action displays the actors and items in adjacent areas.
 */
public class AreaAction implements Action<Survivor> {

    /**
     * Displays the actors and items in the areas surrounding the Survivor's current position.
     *
     * @param survivor The Survivor performing the action.
     */
    private void displayAreasAround(Survivor survivor) {
        City city = survivor.getCity();
        for (Direction d : Direction.values()) {
            System.out.println(d);
            int i = d.getX();
            int j = d.getY();

            int x = survivor.getArea().getX();
            int y = survivor.getArea().getY();

            if ((y + j) <0 || (y + j)>=city.getHeight() || (x + i) <0 || (x + i)>=city.getWidth()) {
                continue;
            }
            Area a = city.getAreas()[y + j][x + i];

            a.displayActors();
            // System.out.println();

            if (!a.isARoom()) {
                continue;
            }

            Room r = (Room) a;
            if (!r.isContinental()) {
                r.displayItems();
                // System.out.println();
            }
            System.out.println();
        }
    }

    /**
     * Performs the action of displaying areas around the Survivor.
     * This action consumes one action point from the Survivor.
     *
     * @param survivor The Survivor performing the action.
     */
    @Override
    public void doSomething(Survivor survivor) {
        displayAreasAround(survivor);
    }

    public String toString() {
        return "Area action";
    }
}
