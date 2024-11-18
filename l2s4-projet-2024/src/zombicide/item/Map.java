package zombicide.item;

import zombicide.actor.survivor.Survivor;
import zombicide.city.City;

/**
 * Represents a Map item in the game.
 * This item allows a survivor to display the city map when used.
 */
public class Map extends Item {

	public Map() {
		super();
		this.isNoisyUse = true;
	}

	/**
	 * Returns a string representation of the Map item.
	 *
	 * @return The string "map".
	 */
	public String toString() {
		return "map";
	}

	/**
	 * Uses the Map item to display the city map.
	 * Retrieves the city from the survivor and displays it.
	 */
	public void use() {
		City city = this.survivor.getCity();
		this.survivor.getArea().increaseNoiseLevel(1);
		System.out.println("Displaying the map 'Action map':");
		city.display();
	}
}
