package zombicide.actor.survivor.role;

import org.junit.jupiter.api.BeforeEach;
import zombicide.action.survivor.special.Fighter;
import zombicide.action.survivor.special.Healer;
import zombicide.action.survivor.special.Lucky;
import zombicide.action.survivor.special.Snooper;
import zombicide.actor.survivor.Survivor;
import zombicide.city.City;

public class RoleTest {
    private Survivor survivor;
    private Fighter fighter;
    private Lucky lucky;
    private Snooper snooper;
    private Healer healer;
    private City city;

    @BeforeEach
    public void before(){
        this.city = new zombicide.city.City(5,5);
        this.survivor = new Survivor(this.city);
        this.fighter = new Fighter();
        this.snooper = new Snooper();
        this.lucky = new Lucky();
        this.healer = new Healer();
    }

}
