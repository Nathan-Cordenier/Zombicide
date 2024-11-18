package zombicide.item;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import zombicide.action.survivor.DoorAction;
import zombicide.action.survivor.HealAction;
import zombicide.actor.survivor.Survivor;
import zombicide.city.City;
import zombicide.city.area.room.Room;
import zombicide.item.careItem.FirstAidKit;
import zombicide.item.careItem.HealingFiask;
import zombicide.util.Direction;

import static org.junit.jupiter.api.Assertions.*;

public class FirstAidKitTest {

    private FirstAidKit firstAidKit;

    private Survivor s;
    private Survivor s2;

    private City c;
    private HealAction h;



    @BeforeEach
    public void setUp() {
        firstAidKit = new FirstAidKit();
        firstAidKit.setLifePointsToAdd(1);

        c = new City(5,5);
        s = new Survivor(c);
        s2 = new Survivor(c);

        firstAidKit.setSurvivor(s);

        s.setArea(new Room(4,4));
        s2.setArea(new Room(4,4));

        h = new HealAction();
    }

    @Test
    public void init() {
        assertFalse(firstAidKit.canOpen());
        assertFalse(firstAidKit.canAttack());
        assertFalse(firstAidKit.isNoisyWhenUsedToOpenDoor());
        assertTrue(firstAidKit.isNoisyWhenUsed());
    }

    @Test
    public void testUse(){
        firstAidKit.use();
        assertTrue(s.getLifePoints() == 6 || s2.getLifePoints() == 6);
    }


}
