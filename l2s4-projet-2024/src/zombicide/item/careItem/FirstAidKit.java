package zombicide.item.careItem;

import zombicide.action.survivor.HealAction;
import zombicide.actor.survivor.Survivor;
import zombicide.city.area.Area;
import zombicide.util.listchooser.ListChooser;
import zombicide.util.listchooser.RandomListChooser;

import java.util.List;

/**
 * FirstAidKit class represents a first aid kit care item in the game.
 * It allows a survivor to heal another survivor in the same area.
 */
public class FirstAidKit extends CareItem {

    public FirstAidKit() {
        super();
    }

    /**
     * Returns a string representation of the FirstAidKit.
     *
     * @return The string "first aid kit".
     */
    public String toString() {
        return "first aid kit";
    }

    /**
     * Performs the action of using the first aid kit.
     * It randomly chooses a survivor from the same area as the current survivor and performs a healing action on them.
     */
    @Override
    public void use() {
        HealAction healAction = new HealAction();
        healAction.setLifePointsToAdd(this.lifePointsToAdd);

        Survivor targetSurvivor = chooseAPlayer();
        if (targetSurvivor != null) {
            healAction.doSomething(targetSurvivor);
            System.out.println(targetSurvivor.getName()+" has now "+targetSurvivor.getLifePoints()+" life point(s)");
        } else {
            System.out.println("No other survivors in the area to heal.");
        }
    }

    /**
     * Randomly chooses a survivor from the same area as the current survivor.
     *
     * @return The chosen survivor from the current area, or null if no survivors are present.
     */
    private Survivor chooseAPlayer() {
        Area currentArea = this.survivor.getArea();
        List<Survivor> survivorsInArea = currentArea.getSurvivors();

        if (survivorsInArea.isEmpty()) {
            return null;
        }

        ListChooser<Survivor> chooser = new RandomListChooser<>();
        return chooser.choose(survivorsInArea);
    }
}
