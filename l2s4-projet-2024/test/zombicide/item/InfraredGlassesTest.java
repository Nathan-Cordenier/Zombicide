package zombicide.item;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import zombicide.actor.survivor.Survivor;
import zombicide.city.City;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class InfraredGlassesTest {

    private InfraredGlasses infraredGlasses;
    private Survivor s;

    @BeforeEach
    public void setUp() {
        infraredGlasses = new InfraredGlasses();
        infraredGlasses.setSurvivor(new Survivor(new City(5,5)));
    }

    @Test
    public void init() {
        assertFalse(infraredGlasses.canOpen());
        assertFalse(infraredGlasses.canAttack());
        assertFalse(infraredGlasses.isNoisyWhenUsedToOpenDoor());
        assertFalse(infraredGlasses.isNoisyWhenUsed());
    }

    @Test
    public void useTest(){
        infraredGlasses.use();
    }
}
