package zombicide.action.survivor;

import zombicide.action.Action;
import zombicide.actor.survivor.Survivor;
import zombicide.city.area.Area;
import zombicide.city.area.room.Room;
import zombicide.actor.survivor.backpack.BackPack;
import zombicide.item.Item;

/**
 * An action representing a Survivor rummaging through items in the current room.
 * This action allows the Survivor to pick up items from the current room and manage their backpack.
 */
public class RummageAction implements Action<Survivor> {

    /**
     * Performs the action of rummaging through items in the Survivor's current room.
     * This action allows the Survivor to pick up items from the room and manage their backpack.
     *
     * @param survivor The Survivor performing the rummage action.
     */
    @Override
    public void doSomething(Survivor survivor) {
        System.out.println(survivor.getName()+" tried to rummage");
        rummage(survivor);
        survivor.removeActionPoint();
    }

    /**
     * Allows the survivor to rummage through the current room, picking up an item randomly.
     * If the survivor has space in their backpack, the chosen item from the room is added to the backpack.
     * If the backpack is full, the method swaps a randomly chosen item from the backpack with the chosen item from the room.
     *
     * @param survivor The Survivor performing the rummage action.
     */
    private void rummage(Survivor survivor) {
        Area area = survivor.getArea();

        if (!area.isARoom()) {
            System.out.println(survivor.getName()+" is in the street, he can't rummage");
            return;
        }

        Room room = (Room) area;
        BackPack backpack = survivor.getBackpack();

        if (!room.hasItems()) {
            System.out.println("There is no items in the current room");
            return;
        }

        Item roomItem = room.getRandomItem();

        Item oldBpItem = backpack.addItem(roomItem);

        if (oldBpItem != null) {
            room.addItem(oldBpItem);
            System.out.println(survivor.getName()+"'s backpack was full, his "+ oldBpItem +" has been dropped");
        }

        System.out.println(survivor.getName()+" picked up a(n) "+ roomItem);
    }

    public String toString() {
        return "Rummage action";
    }
}
