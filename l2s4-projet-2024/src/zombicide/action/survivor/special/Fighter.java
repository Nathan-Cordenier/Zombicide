package zombicide.action.survivor.special;

import zombicide.action.survivor.AttackZombieAction;

public class Fighter extends AttackZombieAction {

    @Override
    protected int throwOneDie() {
        return RANDOM_DIE_VALUE.nextInt(5) + 2;
    }
}
