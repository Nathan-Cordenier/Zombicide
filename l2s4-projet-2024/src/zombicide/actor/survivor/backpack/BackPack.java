package zombicide.actor.survivor.backpack;

import zombicide.actor.survivor.Survivor;
import zombicide.item.Item;
import zombicide.util.listchooser.RandomListChooser;

import java.util.ArrayList;
import java.util.List;

/**
 * Class representing a backpack that can hold items.
 */
public class BackPack {
    private final Survivor survivor;

    /**
     * List to store items in the backpack.
     */
    private final List<Item> items;
    private static final RandomListChooser<Item> ITEM_CHOOSER = new RandomListChooser<>();

    /**
     * Constructor for the BackPack class. Initializes an empty list of items.
     * @param survivor The survivor who gets the backpack
     */
    public BackPack(Survivor survivor) {
        this.survivor = survivor;
        this.items = new ArrayList<>();
    }


    /**
     * Adds the specified item to the backpack if there is still space.
     * If the backpack is full, swaps a randomly chosen item with the specified item.
     *
     * @param item The item to be added to the backpack.
     * @return The item that was removed from the backpack.
     */
    public Item addItem(Item item) {
        item.setSurvivor(this.survivor);
        if (stillHaveSpace()) {
            this.items.add(item);
            return null;
        }
        return swapRandomItemWith(item);
    }

    /**
     * Removes the specified item from the backpack if the backpack is not empty.
     *
     * @param item The item to be removed from the backpack.
     */
    public void removeItem(Item item) {
        if (!this.items.isEmpty()) {
            this.items.remove(item);
        }
    }

    /**
     * Returns the list of items currently held or stored by the survivor.
     *
     * @return A List of Item objects representing the items held or stored by the survivor.
     */
    public List<Item> getItems() {
        return items;
    }

    /**
     * Swaps a randomly chosen item from the backpack with the item from the room.
     * Removes the item from the backpack and adds the room item to the backpack.
     *
     * @param item The item from the room to be placed in the backpack.
     * @return The item that was removed from the backpack.
     */
    private Item swapRandomItemWith(Item item) {
        Item droppedItem = ITEM_CHOOSER.choose(this.items);
        this.items.remove(droppedItem);
        this.items.add(item);
        return droppedItem;
    }

    /**
     * Checks if the backpack can hold more items.
     *
     * @return true if the backpack can hold more items, false otherwise.
     */
    public boolean stillHaveSpace() {
        return this.items.size() < 5;
    }

    public Item getRandomItem() {
        return ITEM_CHOOSER.choose(this.items);
    }

    public String displayItems(){
        String message = "";
        for(Item i : this.items){
            message += "| " + i.toString() + " |";
        }
        return message;
    }
}
