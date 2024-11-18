package zombicide.action.survivor;

import org.junit.jupiter.api.BeforeEach;
import zombicide.action.Action;
import zombicide.action.zombie.ZombieMoveAction;
import zombicide.actor.survivor.Survivor;
import zombicide.actor.zombie.Abomination;
import zombicide.actor.zombie.Zombie;
import zombicide.city.City;
import zombicide.item.attackItem.weapon.Weapon;

import java.util.Arrays;
import java.util.List;

public class AttackZombieActionTest {

    private City city;
    private AttackZombieAction attackZombieAction;
    private Survivor survivor;
    private Zombie zombie;
    private Weapon weapon;

    @BeforeEach
    public void before() {
        List<Action<Survivor>> actionSurvivor = Arrays.asList(
                new AttackZombieAction()
        );
        List<Action<Zombie>> zombieSurvivor = Arrays.asList(
                new ZombieMoveAction()
        );

        this.city = new City(10, 10);
        survivor = new Survivor(actionSurvivor, city);
        zombie = new Abomination(zombieSurvivor, city);
        int posX = zombie.getArea().getX();
        int posY = zombie.getArea().getY();
        survivor.setArea(this.city.getArea(posX, posY));
    }


}
