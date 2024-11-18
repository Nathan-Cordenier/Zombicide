package zombicide.action.survivor;

import zombicide.action.Action;
import zombicide.actor.survivor.Survivor;
import zombicide.city.City;
import zombicide.city.area.door.Door;
import zombicide.item.Item;
import zombicide.util.listchooser.RandomListChooser;
import zombicide.util.Direction;

import java.util.ArrayList;
import java.util.List;

/**
 * An action representing a Survivor opening a door in an adjacent area.
 * The Survivor can open a door in a random adjacent area around their current position.
 */
public class DoorAction implements Action<Survivor> {

    /**
     * Performs the action of opening a door in a random adjacent area around the Survivor's current position.
     * If the Survivor can open a door, this action selects a door from the adjacent areas and opens it.
     *
     * @param survivor The Survivor performing the action.
     */
    public void doSomething(Survivor survivor) {
        if (canOpen(survivor)) {
            List<Door> doors = doorsAround(survivor);
            if (!doors.isEmpty()) {
                RandomListChooser<Door> chooser = new RandomListChooser<>();
                Door door = chooser.choose(doors);
                door.open();
                if(survivor.getItemHeld().isNoisyWhenUsedToOpenDoor()){
                    survivor.getArea().increaseNoiseLevel(1);
                    System.out.println("The overture of the door is noisy!");
                }
                System.out.println(survivor.getName()+" opened a door");
            }else{
                System.out.println("there is no closed doors around");
            }
        }else{
            System.out.println(survivor.getName()+" can't open any door");
        }

        survivor.removeActionPoint();
    }

    /**
     * Checks if the Survivor has an item that can be used to open doors.
     *
     * @param survivor The Survivor to check.
     * @return true if the Survivor has an item that can open doors, false otherwise.
     */
    public boolean canOpen(Survivor survivor) {
        Item itemHeld = survivor.getItemHeld();
        return itemHeld != null && itemHeld.canOpen();
    }

    /**
     * Finds and returns a list of doors in the adjacent areas around the Survivor's current position
     * that are closed and can be opened.
     *
     * @param survivor The Survivor whose adjacent areas are checked for doors.
     * @return A list of doors that can be opened in the adjacent areas.
     */
    private List<Door> doorsAround(Survivor survivor) {
        List<Door> doors = new ArrayList<>();
        for (Direction direction : Direction.values()) {
            int i = direction.getX();
            int j = direction.getY();

            int x = survivor.getArea().getX();
            int y = survivor.getArea().getY();

            City city = survivor.getCity();

            if ((y + j) <0 || (y + j)>=city.getHeight() || (x + i) <0 || (x + i)>=city.getWidth()) {
                continue;
            }

            Door door = city.getAreas()[y + j][x + i].getDoor(direction.getReverse());
            if (door != null && !door.isOpen()) {
                doors.add(door);
            }
        }
        return doors;
    }

    public String toString() {
        return "Door action";
    }
}
