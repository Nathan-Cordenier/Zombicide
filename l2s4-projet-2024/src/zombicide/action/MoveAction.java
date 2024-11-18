package zombicide.action;

import zombicide.actor.Actor;
import zombicide.actor.zombie.Zombie;
import zombicide.city.City;
import zombicide.city.area.Area;
import zombicide.city.area.room.ThePharmacy;
import zombicide.util.Direction;
import zombicide.util.Position;
import zombicide.util.listchooser.ListChooser;
import zombicide.util.listchooser.RandomListChooser;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * An abstract class representing a move action for an Actor in the game.
 * This class provides methods to calculate movement directions and positions
 * for an Actor based on the game's rules.
 *
 * @param <T> The type of Actor performing the move action.
 */
public abstract class MoveAction<T extends Actor> implements Action<T> {
    private static final ListChooser<Direction> DIRECTION_CHOOSER =
            new RandomListChooser<>();

    /**
     * Gets a list of open Directions from the specified Area.
     *
     * @param area The Area to check for open Directions.
     * @return A list of open Directions from the specified Area.
     */
    protected List<Direction> getOpenDirectionsFrom(Area area) {
        return Stream.of(Direction.values())
                .filter(d -> area.getDoor(d).isOpen())
                .collect(Collectors.toList());
    }

    /**
     * Chooses a random open Direction from the specified Area.
     *
     * @param area The Area to choose a random open Direction from.
     * @return A random open Direction from the specified Area.
     */
    protected Direction randomOpenDirectionFrom(Area area) {
        return DIRECTION_CHOOSER.choose(getOpenDirectionsFrom(area));
    }

    /**
     * Chooses a random Direction.
     *
     * @return A random Direction.
     */
    protected Direction randomDirection() {
        return DIRECTION_CHOOSER.choose(List.of(Direction.values()));
    }

    /**
     * Calculates the new Position of an Actor after moving in the specified direction.
     *
     * @param actor The Actor to calculate the new Position for.
     * @return The new Position after moving the Actor.
     */
    protected Position positionAfterMoving(Actor actor) {
        if (actor == null)
            return null;

        Direction direction = getDirectionFrom(actor , actor.getArea());

        int x = actor.getArea().getX();
        int y = actor.getArea().getY();

        if (direction == null){
            return new Position(x, y);
        } else {

            int i = x + direction.getX();
            int j = y + direction.getY();

            System.out.println(actor.getName()+" moved ");

            return new Position(i, j);
        }
    }

    /**
     * Gets the direction of movement from the specified Area.
     *
     * @param area The Area from which to get the direction of movement.
     * @return The direction of movement from the specified Area.
     */
    protected abstract Direction getDirectionFrom(Actor actor , Area area);

    /**
     * Performs the move action for the Actor.
     *
     * @param actor The Actor performing the move action.
     */
    @Override
    public void doSomething(T actor) {
        Position p = positionAfterMoving(actor);
        City city = actor.getCity();
        actor.setArea(city.getArea(p.getX(), p.getY()));

        if(actor.getArea().isPharmacy()){
            ThePharmacy pharmacy = (ThePharmacy) actor.getArea();
            pharmacy.addHealingFiask();
            System.out.println(actor.getName()+" entered the pharmacy, a healing fiask appeared");
        }

        actor.removeActionPoint();
    }

}
