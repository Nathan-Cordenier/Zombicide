package zombicide.item;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import zombicide.action.zombie.ZombieMoveAction;
import zombicide.actor.zombie.Zombie;
import zombicide.city.City;
import zombicide.action.Action;
import zombicide.action.zombie.AttackSurvivorAction;
import zombicide.actor.survivor.Survivor;
import zombicide.actor.zombie.Abomination;
import zombicide.item.attackItem.weapon.*;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class WeaponTest {
    private Pistol pistol;
    private Axe axe;
    private Chainsaw chainsaw;
    private Crowbar crowbar;
    private Riffle riffle;
    private Survivor s;
    private Abomination z1;
    private Abomination z2;
    private Abomination z3;
    private City c;

    @BeforeEach
    public void before(){
        this.pistol = new Pistol();
        this.axe = new Axe();
        this.chainsaw = new Chainsaw();
        this.riffle = new Riffle();
        this.crowbar = new Crowbar();

        c = new City(5,5);
        s = new Survivor(c);

        List<Action<zombicide.actor.zombie.Zombie>> zombieSurvivor = Arrays.asList(
                new ZombieMoveAction(),
                new AttackSurvivorAction()
        );
        z1 = new Abomination(zombieSurvivor , c);
        z2 = new Abomination(zombieSurvivor , c);
        z3 = new Abomination(zombieSurvivor , c);


        s.setArea(c.getArea(2,2));
        z1.setArea(c.getArea(2,2));
        z2.setArea(c.getArea(2,1));
        z3.setArea(c.getArea(2,3));


    }


    @Test
    void testGetMinAndMaxHittingRange(){
        int minRange = pistol.getMinHittingRange();
        int maxRange = pistol.getMaxHittingRange();
        assertEquals(minRange, 0);
        assertEquals(maxRange, 1);
    }

    @Test
    public void testShootRange(){
        riffle.setSurvivor(s);
        axe.setSurvivor(s);

        List<Zombie> listWithRiffle = riffle.shootRange();
        List<Zombie> listWithAxe = axe.shootRange();

        assertEquals(listWithRiffle.size() , 2);
        assertEquals(listWithAxe.size() , 1);
    }
}
