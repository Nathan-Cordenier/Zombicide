package zombicide.item;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import zombicide.actor.survivor.Survivor;
import zombicide.city.City;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class MapTest {

    private Map map;

    @BeforeEach
    public void setUp() {
        map = new Map();
        map.setSurvivor(new Survivor(new City(5,5)));
    }

    @Test
    public void init() {
        assertFalse(map.canOpen());
        assertFalse(map.canAttack());
        assertFalse(map.isNoisyWhenUsedToOpenDoor());
        assertTrue(map.isNoisyWhenUsed());
    }

    @Test
    public void useTest(){
        map.use();
    }
    @Test
    public void testUseMap() {
        City city = new City(5, 5);
        Map map = new Map();
        Survivor survivor = new Survivor(city);
        survivor.setItemHeld(map);
        map.setSurvivor(survivor);
    }


}
