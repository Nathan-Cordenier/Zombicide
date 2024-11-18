package zombicide.city.area.room;

import zombicide.city.area.Area;
import zombicide.actor.survivor.backpack.BackPack;
import zombicide.item.Item;
import zombicide.util.listchooser.RandomListChooser;

import java.util.ArrayList;
import java.util.List;

/**
 * The {@code Room} class represents a room in the Zombicide game.
 * Each room can have multiple doors.
 */
public class Room extends Area {
	private static final char name = 'R';
	private static final RandomListChooser<Item> ITEM_CHOOSER = new RandomListChooser<>();

	public List<Item> items;


	/**
	 * Constructs a room at the specified position.
	 *
	 * @param x The X-coordinate position of the room.
	 * @param y The Y-coordinate position of the room.
	 */
	public Room(int x, int y) {
		super(x, y);
		this.items = new ArrayList<>();
	}

	@Override
	protected String getName() {
		return "" + name;
	}

	public List<Item> getItems()  {
		return this.items;
	}

	/**
	 * Adds the specified item to this room.
	 *
	 * @param item The item to be added to this room.
	 */
	public void addItem(Item item) {
		this.items.add(item);
	}

	/**
	 * Moves all items from the specified backpack to this room.
	 *
	 * @param backpack The backpack from which items are moved to this room.
	 */
	public void letItems(BackPack backpack) {
		List<Item> list = backpack.getItems();
		for(Item item : list)
			addItem(item);
	}

	/**
	 * Displays the items in this room.
	 */
	public void displayItems() {
		System.out.print("There are : ");
		if (this.items.isEmpty()) {
			System.out.print("nothing");
		}
		else {
			for(Item item : this.items) {
				System.out.print("| " + item.toString() + " |");
			}
		}
		System.out.println('\n');
	}

	/**
	 * Retrieves a random item from this room.
	 *
	 * @return A random item from this room if there are any, null otherwise.
	 */
	public Item getRandomItem() {
		return ITEM_CHOOSER.choose(this.items);
	}

	public boolean hasItems() {
		return !this.items.isEmpty();
	}

	@Override
	public boolean isARoom() {
		return true;
	}
}

