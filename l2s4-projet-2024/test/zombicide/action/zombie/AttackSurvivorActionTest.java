package zombicide.action.zombie;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import zombicide.action.Action;
import zombicide.action.MoveAction;
import zombicide.action.zombie.AttackSurvivorAction;
import zombicide.actor.survivor.Survivor;
import zombicide.actor.zombie.Abomination;
import zombicide.actor.zombie.Zombie;
import zombicide.city.City;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class AttackSurvivorActionTest {
    private City city;
    private Zombie zombie;
    private Survivor survivor;
    private AttackSurvivorAction action;

    @BeforeEach
    public void setup() {
        city = new City(10, 10);
        List<Action<Zombie>> zombieSurvivor = Arrays.asList(
                new AttackSurvivorAction()
        );
        zombie = new Abomination(zombieSurvivor , city);
        survivor = new Survivor(city);
        action = new AttackSurvivorAction();
    }

    @Test
    public void testDoSomethingWhenOnlyOneSurvivorIsPresent() {
        zombie.setArea(city.getArea(0, 0));
        survivor.setArea(city.getArea(0, 0));

        int initialLifePoints = survivor.getLifePoints();
        int attackPoints = zombie.getAttackPoints();

        action.doSomething(zombie);

        assertEquals(initialLifePoints - attackPoints, survivor.getLifePoints());
    }

    @Test
    public void testDoSomethingWhenMultipleSurvivorsArePresent() {
        zombie.setArea(city.getArea(0, 0));
        survivor.setArea(city.getArea(0, 0));
        Survivor survivor2 = new Survivor(city);
        survivor2.setArea(city.getArea(0, 0));

        int initialLifePoints = survivor.getLifePoints();
        int initialLifePoints2 = survivor2.getLifePoints();
        int attackPoints = zombie.getAttackPoints();

        action.doSomething(zombie);

        boolean isSurvivor1Attacked = survivor.getLifePoints() == initialLifePoints - attackPoints;
        boolean isSurvivor2Attacked = survivor2.getLifePoints() == initialLifePoints2 - attackPoints;

        assertTrue((isSurvivor1Attacked && !isSurvivor2Attacked) ||
                (!isSurvivor1Attacked && isSurvivor2Attacked));
    }

    @Test
    public void testDoSomethingWhenNoSurvivorArePresent() {
        zombie.setArea(city.getArea(0, 0));
        survivor.setArea(city.getArea(0, 0));

        int initialLifePoints = survivor.getLifePoints();

        action.doSomething(zombie);

        assertEquals(survivor.getLifePoints(),2);
    }
}
