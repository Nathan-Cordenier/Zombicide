package zombicide.action.survivor;

import zombicide.action.Action;
import zombicide.actor.survivor.backpack.BackPack;
import zombicide.actor.survivor.Survivor;
import zombicide.item.Item;

/**
 * An action representing a Survivor interacting with their backpack.
 * This action allows the Survivor to take an item from their backpack and equip it in their hand.
 * If the Survivor already has an item in their hand, the action swaps it with a randomly chosen item from the backpack.
 * If the Survivor's hand is empty, the action equips a randomly chosen item from the backpack.
 */
public class BackPackAction implements Action<Survivor> {

    /**
     * Performs the action of interacting with the Survivor's backpack.
     * The Survivor holds a randomly chosen item from the backpack.
     *
     * @param survivor The Survivor performing the action.
     */
    @Override
    public void doSomething(Survivor survivor) {
        holdRandomItem(survivor);
        survivor.removeActionPoint();
    }

    /**
     * Allows the survivor to take an item from their backpack and equip it in their hand.
     * If the survivor already has an item in their hand, the method swaps it with a randomly chosen item from the backpack.
     * If the survivor's hand is empty, the method equips a randomly chosen item from the backpack.
     *
     * @param survivor The Survivor performing the action.
     */
    private void holdRandomItem(Survivor survivor) {
        BackPack backpack = survivor.getBackpack();
        if (backpack.getItems().isEmpty()) {
            System.out.println(survivor.getName()+" has nothing in his backpack");
            return;
        }

        Item i = backpack.getRandomItem();
        survivor.setItemHeld(i);
        survivor.getBackpack().removeItem(i);
        System.out.println(survivor.getName()+" took a(n) "+i.toString()+" from his backpack");
        System.out.println(survivor.getName()+" 's backpack : "+survivor.getBackpack().displayItems());
    }

    public String toString() {
        return "BackPack action";
    }
}
