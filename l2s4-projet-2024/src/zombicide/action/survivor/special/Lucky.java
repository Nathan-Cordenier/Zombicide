package zombicide.action.survivor.special;

import zombicide.action.survivor.AttackZombieAction;
import zombicide.actor.survivor.Survivor;
import zombicide.actor.zombie.Zombie;
import zombicide.item.attackItem.AttackItem;

public class Lucky extends AttackZombieAction {

    @Override
    protected void shootZombie(Survivor survivor, Zombie zombie, AttackItem attackItem) {
        System.out.println(survivor.getName()+" uses his lucky skill ! He has two dice throws !");
        int highestDieValue = getHighestDieValue(attackItem.getNbDiceThrows() + 1);

        if (attackItem.shotHitsTarget(highestDieValue)) {
            attackZombie(survivor, zombie, attackItem);
        }
    }
}
