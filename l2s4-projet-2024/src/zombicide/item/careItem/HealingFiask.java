package zombicide.item.careItem;

import zombicide.action.survivor.HealAction;

/**
 * HealingFiask class represents a healing flask care item in the game.
 * It allows a survivor to heal themselves when used.
 */
public class HealingFiask extends CareItem {

    public HealingFiask() {
        super();
    }

    /**
     * Returns a string representation of the HealingFiask.
     *
     * @return The string "healing flask".
     */
    public String toString() {
        return "healing flask";
    }

    /**
     * Performs the action of using the healing flask, which triggers a HealAction.
     */
    @Override
    public void use() {
        HealAction healAction = new HealAction();
        healAction.setLifePointsToAdd(this.lifePointsToAdd);
        healAction.doSomething(survivor);
        System.out.println(this.survivor.getName()+" has now "+this.survivor.getLifePoints()+" life point(s)");
    }
}
