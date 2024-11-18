package zombicide.action.survivor.special;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import zombicide.action.Action;
import zombicide.actor.survivor.Survivor;
import zombicide.city.City;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class HealerTest {
    private Survivor survivor;
    private Healer healer;

    @BeforeEach
    void setUp() {
        this.healer = new Healer();
        List<Action<Survivor>> actions = List.of(this.healer);
        this.survivor = new Survivor(actions, new City(10, 10));
    }

    @Test
    void testAddLifePoints() {
        int initialLifePoints = this.survivor.getLifePoints();

        this.healer.doSomething(survivor);

        int expected = initialLifePoints + Healer.NB_LIFE_POINTS;
        int actual = this.survivor.getLifePoints();

        assertEquals(expected, actual);
    }
}
