package zombicide.actor.zombie;

import zombicide.action.Action;
import zombicide.action.zombie.AttackSurvivorAction;
import zombicide.action.zombie.ZombieMoveAction;
import zombicide.actor.Actor;
import zombicide.city.area.Area;
import zombicide.city.City;
import zombicide.city.area.street.Manhole;
import zombicide.util.listchooser.RandomListChooser;

import java.util.ArrayList;
import java.util.List;

/**
 * Abstract class representing a zombie actor in the game.
 */
public abstract class Zombie extends Actor {

    /** The attack points of the zombie. */
    protected int attackPoints;

    /** Indicates whether the zombie is strong or not. */
    protected boolean isStrong;
    protected List<Action<Zombie>> zombieActions;
    private static final RandomListChooser<Manhole> MANHOLE_CHOOSER = new RandomListChooser<>();
    protected static final RandomListChooser<Action<Zombie>> ACTION_CHOOSER = new RandomListChooser<>();

    public Zombie(List<Action<Zombie>> zombieActions, int attackPoints, int lifePoints, int actionPoints, boolean isStrong, City city) {
        super(city, lifePoints, actionPoints);
		this.attackPoints = attackPoints;
		this.isStrong = isStrong;
        this.zombieActions = new ArrayList<>(zombieActions);
	}

    /**
     * Chooses a random manhole for the zombie to spawn.
     *
     * @return The randomly chosen manhole.
     */
    private Manhole chooseRandomManhole() {
        return MANHOLE_CHOOSER.choose(this.city.getManholes());
    }

    /**
     * Checks if the zombie is strong.
     *
     * @return {@code true} if the zombie is strong, {@code false} otherwise.
     */
    public boolean getIsStrong() {
        return this.isStrong;
    }

    /**
     * Gets the attack points of the zombie.
     *
     * @return The attack points of the zombie.
     */
    public int getAttackPoints() {
        return this.attackPoints;
    }

    /**
     * Sets the area for the zombie, adding the zombie to the area.
     *
     * @param area The area where the zombie is located.
     */
    @Override
    public void setArea(Area area) {
        if (this.area != null) {
            this.area.removeActor(this);
        }
        this.area = area;
        area.addZombie(this);
    }

    /**
     * Handles the action of the zombie.
     * Chooses a random action from the available actions and performs it.
     */
    public void handleAction() {
        if (!this.getArea().getSurvivors().isEmpty()) {
            Action<Zombie> action = new AttackSurvivorAction();
            System.out.println(action.toString());
            action.doSomething(this);
        }
        else {
            Action<Zombie> action = new ZombieMoveAction();
            System.out.println(action.toString());
            action.doSomething(this);
        }
        /**
        Action<Zombie> action = ACTION_CHOOSER.choose(this.zombieActions);
        if (action != null)  {
            System.out.println(action.toString());
            action.doSomething(this);
        }
        else {
            System.out.println("No action (null)");
        }
         */
    }
}
