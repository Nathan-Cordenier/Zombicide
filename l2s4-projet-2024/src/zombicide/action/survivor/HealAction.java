package zombicide.action.survivor;

import zombicide.action.Action;
import zombicide.actor.survivor.Survivor;

/**
 * An action representing a Survivor healing themselves by adding life points.
 * The Survivor can use this action to increase their life points by a specified amount.
 */
public class HealAction implements Action<Survivor> {
    private int lifePointsToAdd;

    /**
     * Performs the action of healing the Survivor by adding life points.
     * This action increases the Survivor's life points by the specified amount.
     *
     * @param survivor The Survivor performing the healing action.
     */
    public void doSomething(Survivor survivor) {
        heal(survivor);
    }

    /**
     * Sets the number of life points to add when healing.
     *
     * @param lifePointsToAdd The number of life points to add.
     */
    public void setLifePointsToAdd(int lifePointsToAdd) {
        this.lifePointsToAdd = lifePointsToAdd;
    }

    /**
     * Heals the Survivor by adding the specified number of life points.
     *
     * @param survivor The Survivor to heal.
     */
    public void heal(Survivor survivor) {
        survivor.addLifePoints(lifePointsToAdd);
    }

    public String toString() {
        return "Heal action";
    }
}
