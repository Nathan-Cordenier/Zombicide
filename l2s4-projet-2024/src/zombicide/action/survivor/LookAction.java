package zombicide.action.survivor;

import zombicide.action.Action;
import zombicide.actor.survivor.Survivor;
import zombicide.city.area.Area;
import zombicide.util.Direction;

/**
 * An action representing a Survivor looking around their current area.
 * The Survivor can use this action to inspect the actors and doors in their area.
 */
public class LookAction implements Action<Survivor> {

    /**
     * Performs the action of looking around the Survivor's current area.
     * This action displays the actors present and the status of doors in the area.
     *
     * @param survivor The Survivor performing the look action.
     */
    public void doSomething(Survivor survivor) {
        System.out.println(survivor.getName()+" is looking around");
        look(survivor);
    }

    /**
     * Displays the actors and door statuses in the Survivor's current area.
     *
     * @param survivor The Survivor inspecting the area.
     */
    private void look(Survivor survivor) {
        Area area = survivor.getArea();
        if(area.isContinental()){
            System.out.printf(survivor.getName()+" is in the continental, he can't see anything !");
        }

        area.displayActors();
        for (Direction direction : Direction.values()) {
            System.out.println("The door " + direction.name() + " is " + (area.getDoor(direction).isOpen() ? "open" : "closed"));
        }
    }

    public String toString() {
        return "Look action";
    }
}
