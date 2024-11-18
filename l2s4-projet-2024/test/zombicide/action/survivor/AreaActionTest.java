package zombicide.action.survivor;

import org.junit.jupiter.api.Test;
import zombicide.action.survivor.AreaAction;
import zombicide.actor.survivor.Survivor;
import zombicide.city.City;
import zombicide.city.area.room.Room;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AreaActionTest {

    @Test
    public void testDoSomething() {
        Survivor s = new Survivor(new City(10, 10));
        s.setArea(new Room(5, 4));
        AreaAction action = new AreaAction();
        int actionPoints = s.getActionPoints();
        action.doSomething(s);
        assertEquals("Area action", action.toString());
    }
}
