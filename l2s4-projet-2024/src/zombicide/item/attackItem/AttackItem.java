package zombicide.item.attackItem;

import zombicide.actor.zombie.Zombie;
import zombicide.city.City;
import zombicide.city.area.Area;
import zombicide.item.Item;
import zombicide.util.Direction;

import java.util.ArrayList;
import java.util.List;

public abstract class AttackItem extends Item {
    protected int nbDiceThrows;
    protected int diceThreshold;
    protected int damage;
    protected int minHittingRange;
    protected int maxHittingRange;

    /**
     * Creates a new Weapon with specified characteristics.
     *
     * @param nbDiceThrows    The number of dice throws for the weapon.
     * @param diceThreshold   The threshold for a successful shot.
     * @param damage          The damage inflicted by the weapon.
     * @param minHittingRange The minimum hitting range of the weapon.
     * @param maxHittingRange The maximum hitting range of the weapon.
     */
    public AttackItem(int nbDiceThrows, int diceThreshold, int damage, int minHittingRange, int maxHittingRange) {
        super();
        this.nbDiceThrows = nbDiceThrows;
        this.diceThreshold = diceThreshold;
        this.damage = damage;
        this.minHittingRange = minHittingRange;
        this.maxHittingRange = maxHittingRange;
        this.canAttack = true;
        this.isNoisyDoor = true;
    }

    public boolean shotHitsTarget(int lastShotValue) {
        return lastShotValue >= this.diceThreshold;
    }

    /**
     * Gets the damage inflicted by the weapon.
     *
     * @return The damage value.
     */
    public int getDamage() {
        return damage;
    }

    /**
     * Gets the minimum hitting range of the weapon.
     *
     * @return The minimum hitting range.
     */
    public int getMinHittingRange() {
        return minHittingRange;
    }

    /**
     * Gets the maximum hitting range of the weapon.
     *
     * @return The maximum hitting range.
     */
    public int getMaxHittingRange() {
        return maxHittingRange;
    }

    /**
     * Finds the zombies within the shooting range of the weapon based on survivor's area.
     *
     * @return List of zombies within the weapon's shooting range.
     */
    public List<Zombie> shootRange() {
        Area areaWeapon = this.survivor.getArea();
        City city = this.survivor.getCity();
        int x = areaWeapon.getX();
        int y = areaWeapon.getY();
        List<Zombie> zombies = new ArrayList<>();

        if (this.getMinHittingRange() == 0) {
            List<Zombie> zom = city.getArea(x, y).getZombies();
            zombies.addAll(zom);
        }

        for (Direction d : Direction.values()) {
            for (int i = 1; i <= this.getMaxHittingRange(); i++) {
                int newX = x + i * d.getX();
                int newY = y + i * d.getY();
                if (this.getMinHittingRange() <= i) {
                    if (newX < city.getWidth() && newX >= 0 && newY < city.getHeight() && newY >= 0) {
                        if (!(areaWeapon.isContinental())) {
                            List<Zombie> zom = city.getArea(newX, newY).getZombies();
                            zombies.addAll(zom);
                        }
                    }
                }
            }
        }

        return zombies;
    }

    public int getNbDiceThrows() {
        return nbDiceThrows;
    }

    /**
     * Uses the weapon to check and display the zombies within its shooting range.
     */
    public void use() {
        List<Zombie> zombiesInRange = shootRange();
        System.out.print("Zombies in range: ");
        for (Zombie zombie : zombiesInRange) {
            System.out.print(zombie.toString() + " ");
        }
        System.out.println();
    }
}
