package zombicide.action.survivor;

import zombicide.action.MoveAction;
import zombicide.actor.Actor;
import zombicide.actor.survivor.Survivor;
import zombicide.city.area.Area;
import zombicide.util.Direction;

/**
 * An action representing a Survivor moving in a specified direction.
 * This action allows the Survivor to move to an adjacent area in the specified direction,
 * provided that the door in that direction is open.
 */
public class SurvivorMoveAction extends MoveAction<Survivor> {

    /**
     * Calculates the direction for the Survivor to move based on the current area.
     * If the door in the selected direction is closed, the Survivor cannot move in that direction.
     *
     * @param area The current area of the Survivor.
     * @return The direction for the Survivor to move, or null if the door is closed.
     */
    @Override
    protected Direction getDirectionFrom(Actor actor , Area area) {
        Direction direction = randomDirection();

        if (!area.getDoor(direction).isOpen()) {
            System.out.printf("Door closed in direction: %s", direction);
            System.out.println();
            return null;
        }

        return direction;
    }

    public String toString() {
        return "Move action";
    }
}
