package zombicide.item;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import zombicide.action.survivor.DoorAction;
import zombicide.action.survivor.HealAction;
import zombicide.actor.survivor.Survivor;
import zombicide.city.City;
import zombicide.city.area.room.Room;
import zombicide.item.careItem.HealingFiask;
import zombicide.util.Direction;

import static org.junit.jupiter.api.Assertions.*;

public class HealingFiaskTest {

    private HealingFiask healingFiask;

    private Survivor s;


    @BeforeEach
    public void setUp() {
        healingFiask = new HealingFiask();
        healingFiask.setLifePointsToAdd(1);
        s = new Survivor(new City(5 , 5));
        healingFiask.setSurvivor(s);
        s.setArea(new Room(4,4));
        HealAction h = new HealAction();
        h.setLifePointsToAdd(1);
    }

    @Test
    public void init() {
        assertFalse(healingFiask.canOpen());
        assertFalse(healingFiask.canAttack());
        assertFalse(healingFiask.isNoisyWhenUsedToOpenDoor());
        assertTrue(healingFiask.isNoisyWhenUsed());
    }

    @Test
    public void testUse(){
        healingFiask.use();
        assertEquals(s.getLifePoints() , 6);
    }


}
