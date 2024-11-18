package zombicide.city.area;

import zombicide.actor.Actor;
import zombicide.actor.survivor.Survivor;
import zombicide.actor.zombie.Zombie;
import zombicide.city.area.door.Door;
import zombicide.util.Color;
import zombicide.util.Direction;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Abstract class representing an area in the game.
 */
public abstract class Area {
    protected static final String resetColorCode = Color.RESET.getCode();
    protected static final String blackBoldColorCode = Color.BLACK_BOLD.getCode();
    private static final String greenBoldBrightCode = Color.GREEN_BOLD_BRIGHT.getCode();
    private static final String redBoldBrightColorCode = Color.RED_BOLD_BRIGHT.getCode();
    private static final String OPEN_UP = "-     ";
	private static final String CLOSE_UP = "------";
    private final static char ZOMBIE = 'Z';
    private final static char SURVIVOR = 'S';
    protected List<Survivor> survivors;
    protected List<Zombie> zombies;
    private final int posX;
    private final int posY;
    private int noise;
    protected final Map<Direction, Door> doors;

    /**
     * Constructor for the Area class.
     *
     * @param posX The X position of the area.
     * @param posY The Y position of the area.
     */
    public Area(int posX, int posY) {
        this.posX = posX;
        this.posY = posY;
        this.survivors = new ArrayList<>();
        this.zombies = new ArrayList<>();
        this.noise = 0;
        this.doors = new HashMap<>();
        for (Direction d : Direction.values()) {
            doors.put(d, new Door());
        }
    }

    public List<Survivor> getSurvivors() {
        return survivors;
    }

    public List<Zombie> getZombies() {
        return zombies;
    }

    /**
     * Retrieves the door in the specified direction.
     * 
     * @param direction The direction of the door.
     * @return The door in the specified direction.
     */
    public Door getDoor(Direction direction) {
        return this.doors.get(direction);
    }

    /**
     * Retrieves all doors in the area.
     * 
     * @return Map containing all doors in the area.
     */
    public Map<Direction, Door> getDoors() {
        return doors;
    }

    /**
     * Adds a door to the area in the specified direction.
     * 
     * @param direction The direction of the door.
     * @param door The door to add.
     */
    public void addDoor(Direction direction, Door door) {
        doors.put(direction, door);
    }

    /**
     * Retrieves the X position of the area.
     * 
     * @return The X position of the area.
     */
    public int getX() {
        return this.posX;
    }

    /**
     * Retrieves the Y position of the area.
     * 
     * @return The Y position of the area.
     */
    public int getY() {
        return this.posY;
    }

    /**
     * Checks if fighting is possible in the area.
     * 
     * @return true if fighting is possible, false otherwise.
     */
    public boolean canFight() {
        return true;
    }

    /**
     * Abstract method to display the area with specific parameters.
     * 
     * @param n Specific parameters for displaying the area.
     */
    public void display(int n) {
    	if (n == 0) {
			System.out.print(this.getDoor(Direction.UP).isOpen() ? OPEN_UP : CLOSE_UP);
    	}
    	else if (n == 1) {
    		System.out.print(this.getDoor(Direction.LEFT).isOpen() ? openLeft1() : closeLeft1());
    	}
    	else {
    		System.out.print(this.getDoor(Direction.LEFT).isOpen() ? openLeft2() : closeLeft2());
    	}
    }

    abstract protected String getName();

    public int getNbZombies() {
        return this.zombies.size();
    }

    public int getNbSurvivors() {
        return this.survivors.size();
    }

    /**
     * Checks if the area has zombies and returns a formatted string representing the count.
     * If there are no zombies, returns a placeholder.
     *
     * @return A formatted string showing the zombie count if present, or a placeholder.
     */
    private String hasZombies() {
        int z = getNbZombies();
        if (z == 0) {
            return "    ";  // Placeholder for no zombies
        }
        return " " + redBoldBrightColorCode + ZOMBIE + z + resetColorCode + displaySpace(z);
    }

    private String displaySpace(int n) {
        if (n > 9) {
            return "";
        }
        return " ";
    }

    /**
     * Checks if the area has survivors and returns a formatted string representing the count.
     * If there are no survivors, returns a placeholder.
     *
     * @return A formatted string showing the survivor count if present, or a placeholder.
     */
    private String hasSurvivors() {
        int s = getNbSurvivors();
        if (s == 0) {
            return "    ";  // Placeholder for no survivors
        }
        return " " + greenBoldBrightCode + SURVIVOR + s + resetColorCode + displaySpace(s);
    }

    /**
     * Adds a zombie to the area's list of zombies.
     *
     * @param z The Zombie to add.
     */
    public void addZombie(Zombie z) {
        // z.setArea(this);  // Set the area for the zombie (if needed)
        this.zombies.add(z);
    }

    /**
     * Adds a survivor to the area's list of survivors.
     *
     * @param s The Survivor to add.
     */
    public void addSurvivor(Survivor s) {
        // s.setArea(this);  // Set the area for the survivor (if needed)
        this.survivors.add(s);
    }

    /**
     * Removes an actor (zombie or survivor) from the area.
     * Removes the actor from both the zombie and survivor lists.
     *
     * @param a The Actor to remove.
     */
    public void removeActor(Actor a) {
        this.survivors.remove(a);
        this.zombies.remove(a);
    }

    public void displayActors() {
        if (!isContinental()) {
            System.out.println("Survivors:");
            if (!survivors.isEmpty()) {
                for (Survivor s : survivors) {
                    System.out.print("| " + s.getName() + " ");
                }
                System.out.println("|");
            } else {
                System.out.println("Nobody");
            }

            System.out.println("Zombies:");
            if (!zombies.isEmpty()) {
                for (Zombie z : zombies) {
                    System.out.print("| " + z.getName() + " ");
                }
                System.out.println("|");
            } else {
                System.out.println("Nobody");
            }
        }
    }



    public int getNoise(){
        return this.noise;
    }

    public void setNoise(int noise){
        this.noise = noise;
    }

    public void increaseNoiseLevel(int n){
        this.noise += n;
    }

    public String openLeft1() {
        return " " + getName() + hasZombies();
    }

    public String closeLeft1() {
        return "|" + getName() + hasZombies();
    }

    public String openLeft2() {
        return "  " + hasSurvivors();
    }

    public String closeLeft2() {
        return "| " + hasSurvivors();
    }

    public boolean isContinental() {
        return false;
    }
    public boolean isPharmacy() {
        return false;
    }

    public boolean isARoom() {
        return false;
    }
}
