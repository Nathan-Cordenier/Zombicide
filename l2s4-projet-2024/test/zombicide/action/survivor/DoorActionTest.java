package zombicide.action.survivor;

import org.junit.jupiter.api.BeforeEach;
import zombicide.actor.survivor.Survivor;
import zombicide.city.City;
import zombicide.item.MasterKey;
import zombicide.item.attackItem.weapon.Pistol;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class DoorActionTest {

    private DoorAction d;

    private Survivor s;
    private Survivor s2;


    private City c;


    @BeforeEach
    void setUp(){
        d = new DoorAction();
        c = new City(5,5);
        s = new Survivor(c);
        s2 = new Survivor(c);
        s.setArea(c.getArea(0,0));
        s2.setArea(c.getArea(4,4));
        s.setItemHeld(new MasterKey());
        s2.setItemHeld(new Pistol());
    }

//    @Test
//    void testDoSomething(){
//        d.doSomething(s);
//        d.doSomething(s2);
//
//        assertTrue(s.getArea().getDoor(Direction.RIGHT).isOpen() || s.getArea().getDoor(Direction.DOWN).isOpen());
//
//        assertFalse(s2.getArea().getDoor(Direction.LEFT).isOpen() && s2.getArea().getDoor(Direction.UP).isOpen());
//    }

}
