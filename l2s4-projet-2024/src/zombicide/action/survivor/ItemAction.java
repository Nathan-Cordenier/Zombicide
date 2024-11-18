package zombicide.action.survivor;

import zombicide.action.Action;
import zombicide.actor.survivor.Survivor;
import zombicide.item.Item;

/**
 * An action representing a Survivor using an item they are currently holding.
 * The Survivor can use this action to use the item currently in their hand.
 */
public class ItemAction implements Action<Survivor> {

    /**
     * Performs the action of using the item currently held by the Survivor.
     * This action triggers the use method of the item and consumes an action point.
     *
     * @param survivor The Survivor performing the item action.
     */
    @Override
    public void doSomething(Survivor survivor) {
        if(survivor.holdAnItem()){
            Item itemUsed = survivor.getItemHeld();
            System.out.println(survivor.getName()+" is using his "+itemUsed.toString());
            itemUsed.use();
            if(itemUsed.isNoisyWhenUsed()){
                survivor.getArea().increaseNoiseLevel(1);
                System.out.println("The use of the "+survivor.getItemHeld().toString()+" is noisy!");
            }

            if(!itemUsed.canAttack())
                survivor.setItemHeld(null);
        }else{
            System.out.println(survivor.getName()+" has no item in his hands");
        }

        survivor.removeActionPoint();
    }

    public String toString() {
        return "Item action";
    }
}
