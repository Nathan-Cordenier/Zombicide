package zombicide.actor.survivor;

import zombicide.action.Action;
import zombicide.action.survivor.special.Snooper;
import zombicide.actor.Actor;
import zombicide.actor.survivor.backpack.BackPack;
import zombicide.city.City;
import zombicide.city.area.Area;
import zombicide.item.Item;
import zombicide.item.attackItem.weapon.Pistol;
import zombicide.util.Expertise;
import zombicide.util.listchooser.RandomListChooser;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Represents a survivor actor in the game.
 */
public class Survivor extends Actor {
    private static final int ACTION_POINTS = 3;
    private static final int LIFE_POINTS = 5;
    private static final RandomListChooser<Action<Survivor>> SURVIVOR_ACTION_CHOOSER =
            new RandomListChooser<>();

    /** The skill points of the survivor. */
    private int skillPoints;

    /** The backpack of the survivor to carry items. */
    private final BackPack backpack;

    /** The item currently handled by the survivor. */
    private Item itemHeld;

    /** The roles associated with the survivor. */
    private final List<Action<Survivor>> roles;

    private String name;

    public Survivor(City city) {
        this(new ArrayList<>(), city);
    }

    /**
     * Creates a new survivor with the given roles.
     * You can pass either an array of roles, or as many roles in a row.
     * @param city - the city of the survivor
     * @param actions The actions of the survivor.
     */
    public Survivor(List<Action<Survivor>> actions, City city) {
        super(city, LIFE_POINTS, ACTION_POINTS);
        this.skillPoints = 0;
        this.backpack = new BackPack(this);
        Pistol pistol = new Pistol();
        pistol.setSurvivor(this);
        this.itemHeld = pistol;
        this.itemHeld.setSurvivor(this);
        this.setArea(this.city.getSpawn());
        this.roles = new ArrayList<>(actions);
    }

    public void setName(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }

    /**
     * Gets the skill points of the survivor.
     *
     * @return The skill points.
     */
    public int getSkillPoints() {
        return skillPoints;
    }

    /**
     * Gets the backpack of the survivor.
     *
     * @return The backpack.
     */
    public BackPack getBackpack() {
        return backpack;
    }

    /**
     * Gets the item currently held by the survivor.
     *
     * @return The held item.
     */
    public Item getItemHeld() {
        return itemHeld;
    }

    /**
     * Sets the item held by the survivor.
     *
     * @param i The item to be held.
     */
    public void setItemHeld(Item i) {

        if (this.itemHeld != null) {
            this.backpack.addItem(this.itemHeld);
        }

        if(i != null)
            i.setSurvivor(this);;

        this.itemHeld = i;
    }

    /**
     * Checks if the survivor is holding an item.
     *
     * @return True if the survivor is holding an item, false otherwise.
     */
    public boolean holdAnItem() {
        return this.itemHeld != null;
    }

    /**
     * Checks if the survivor has roles.
     *
     * @return True if the survivor has roles, false otherwise.
     */
    public boolean hasRoles() {
        return !this.roles.isEmpty();
    }

    /**
     * Gets the roles associated with the survivor.
     *
     * @return The list of roles.
     */
    public List<Action<Survivor>> getRoles() {
        return roles;
    }

    /**
     * Increases the skill points of the survivor.
     */
    public void increaseSkillPoints() {
        if (this.skillPoints < 30) {
            this.skillPoints++;
        }
    }

    /**
     * Checks if the survivor has reached a new level.
     *
     * @return True if the survivor has reached a new level, false otherwise.
     */
    public boolean isLevelReached() {
        return Expertise.getStage(skillPoints) != null;
    }

    /**
     * Sets the area for the survivor, adding the survivor to the area.
     *
     * @param area The area where the survivor is located.
     */
    @Override
    public void setArea(Area area) {
        if (this.area != null) {
            this.area.removeActor(this);
        }
        this.area = area;
        area.addSurvivor(this);
    }

    /**
     * Adds skill points to the survivor.
     *
     * @param n The number of skill points to add.
     */
    public void addSkillPoints(int n) {
        this.skillPoints += n;
    }

    /**
     * Handles the action of the survivor.
     * Chooses a random action from the available roles and performs it.
     */
    @Override
    public void handleAction() {
        Action<Survivor> action = SURVIVOR_ACTION_CHOOSER.choose(roles);
        // System.out.println();
        if (action != null) {
            System.out.println(action);
            action.doSomething(this);
        } else {
            System.out.println(this.getName()+" decided to do nothing");
            this.removeActionPoint();
        }

        Iterator<Action<Survivor>> it = this.roles.iterator();
        boolean found = false;
        while (it.hasNext() && !found) {
            Action<Survivor> a = it.next();
            if (a instanceof Snooper) {
                System.out.println(a);
                a.doSomething(this);
                found = true;
            }
        }
    }
}
