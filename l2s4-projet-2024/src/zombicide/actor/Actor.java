package zombicide.actor;

import zombicide.action.Action;
import zombicide.city.area.Area;
import zombicide.city.City;

import java.util.List;

/**
 * Abstract class representing an actor in the game.
 */
public abstract class Actor {
    /**
     * The current area where the actor is located.
     */
    protected Area area;
    /**
     * The life points of the actor.
     */
    protected int lifePoints;

    /**
     * The action points of the actor.
     */
    protected int actionPoints;

    private int maxActionPoints;

    protected City city;

    public Actor(City city, int lifePoints, int actionPoints) {
        this.city = city;
        this.lifePoints = lifePoints;
        this.actionPoints = actionPoints;
        this.maxActionPoints = actionPoints;
    }

    public Area getArea() {
        return area;
    }

    public abstract void setArea(Area area);

    /**
     * Retrieves the life points of the actor.
     *
     * @return the life points of the actor
     */
    public int getLifePoints() {
        return lifePoints;
    }

    public City getCity(){ return this.city; }

    /**
     * OK
     * 
     * Sets the life points of the actor.
     *
     * @param lifePoints the life points to set
     */
    public void addLifePoints(int lifePoints) {
        this.lifePoints += lifePoints;
    }

    /**
     * OK
     * 
     * Reduces the life points of the character by the specified amount of damage.
     *
     * @param damage The amount of damage to be subtracted from the character's life points.
     */
    public void removeLifePoints(int damage) {
        this.lifePoints -= damage;
        if(this.lifePoints < 0)
            this.lifePoints = 0;
    }

    public String getName() {
        return this.getClass().getSimpleName();
    }

    /**
     * OK
     */
    public void removeActionPoint() {
        this.actionPoints--;
    }

    /**
     * OK
     * @return
     */
    public int getActionPoints() {
        return actionPoints;
    }

    /**
     * OK
     * @param actionPoints
     */
    public void setActionPoints(int actionPoints) {
        this.actionPoints = actionPoints;
    }

    /**
     * K
     */
    public abstract void handleAction();

    /**
     * OK
     * @return
     */
    public boolean isDead() {
        return this.lifePoints <= 0;
    }

    /**
     * K
     */
    public void increaseActionPoints() {
        this.maxActionPoints++;
    }

    public void resetActionPoints() {
        this.actionPoints = this.maxActionPoints;
    }
}
