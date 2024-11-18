package zombicide.game;

import zombicide.action.Action;
import zombicide.action.zombie.AttackSurvivorAction;
import zombicide.action.zombie.ZombieMoveAction;
import zombicide.actor.zombie.*;
import zombicide.city.area.street.Manhole;
import zombicide.item.Item;
import zombicide.actor.Actor;
import zombicide.actor.survivor.Survivor;
import zombicide.city.City;
import zombicide.item.InfraredGlasses;
import zombicide.item.Map;
import zombicide.item.MasterKey;
import zombicide.item.attackItem.weapon.*;
import zombicide.item.careItem.FirstAidKit;
import zombicide.item.careItem.HealingFiask;
import zombicide.util.listchooser.RandomListChooser;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Arrays;

/**
 * Represents a Zombicide game session.
 */
public class Game {

    private City city;
    private List<Survivor> survivors;
    private List<Zombie> zombies;
    private Phase currentPhase;

    Scanner scanner = new Scanner(System.in);

    /**
     * Creates a new game session with the given city.
     *
     * @param city The city for the game.
     */
    public Game(City city){
        this.city = city;
        this.survivors = new ArrayList<>();
        this.zombies = new ArrayList<>();
        this.currentPhase = Phase.SURVIVORS;
    }

    public void initGame(){
        /*for (Survivor survivor : this.survivors)
            survivor.setArea(this.city.getSpawn());*/
        this.city.dispatchItems2();
    }


    /**
     * Adds a survivor to the game.
     *
     * @param s The survivor to add.
     */
    public void addSurvivor(Survivor s){
        this.survivors.add(s);
    }

    /**
     * Adds a zombie to the game.
     *
     * @param z The zombie to add.
     */
    public void addZombie(Zombie z){
        this.zombies.add(z);
    }

    /**
     * Checks if the game should end.
     *
     * @return true if the game should end, false otherwise.
     */
    public boolean endGame(){
        return allSurvivorAreDead() || allZombiesAreDead() || areThePlayersHaveReachedStage();
    }

    /**
     * Checks if all survivors are dead.
     *
     * @return true if all survivors are dead, false otherwise.
     */
    public boolean allSurvivorAreDead(){
        return this.survivors.isEmpty();
    }


    /**
     * Checks if all zombies are dead.
     *
     * @return true if all zombies are dead, false otherwise.
     */
    public boolean allZombiesAreDead(){
        return this.zombies.isEmpty();
    }

    /**
     * Checks if the players have reached the winning stage.
     *
     * @return true if the players have reached the winning stage, false otherwise.
     */
    public boolean areThePlayersHaveReachedStage(){
        return getTotalNumberOfSkillPoints() >= 30;
    }

    /**
     * Plays the first tour of the game.
     * Initializes the game, plays the survivors' phase, spawns a zombie,
     * resets action points, displays the city, and sets the current phase to survivors.
     */
    private void playFirstTour() {
        initGame();
        System.out.println("TOUR 1");
        playSurvivorsPhase();
        this.spawnAZombie();
        resetActionPoints();
        this.city.display();
        currentPhase = Phase.SURVIVORS;
    }


    /**
     * Runs the game loop until the end conditions are met.
     */
    public void play(){
        playFirstTour();
        int i = 2;
        while(!endGame()){
            if(currentPhase == Phase.SURVIVORS){
                System.out.println("TOUR "+i);
                System.out.println("Survivors' tour ("+this.countOfSurvivorsAlive()+" alive)");
                playSurvivorsPhase();
            }
            else if(currentPhase == Phase.ZOMBIES){
                System.out.println("Zombies' tour ("+this.countOfZombiesAlive()+" alive)");
                playZombiesPhase();
            }
            else {
                playEndPhase();
                System.out.println();
                System.out.println("Voici l'état des survivants :");
                for (int ignored = 0; ignored < survivors.size(); ignored++) {
                    System.out.printf("Le survivant %d a %d points de vie", ignored+1, survivors.get(ignored).getLifePoints());
                    System.out.println();
                }
                i++;
            }
            this.city.display();
        }
    }

    /**
     * Handles the end phase of the game.
     * Removes dead survivors and zombies, resets noise levels, and spawns new zombies.
     */
    private void playEndPhase() {
        /*survivors.removeIf(Survivor::isDead);
        zombies.removeIf(Zombie::isDead);*/
        survivors.removeIf(Actor::isDead);
        zombies.removeIf(Actor::isDead);

        resetNoise();;

        if(!allZombiesAreDead()){
            int nbZombie = getNumberOfZombiesToSpawn();
            int k;
            for(k = 0 ; k < nbZombie ; k++){
                this.spawnAZombie();
            }
            System.out.println(nbZombie+" zombies spawned");
        }

        resetActionPoints();

        this.currentPhase = Phase.SURVIVORS;

        // scanner.next();
    }

    /**
     * Resets the noise level of all areas in the city to 0.
     */
    private void resetNoise() {
        for (int i = 0; i < this.city.getWidth(); i++) {
            for (int j = 0; j < this.city.getHeight(); j++) {
                this.city.getArea(i, j).setNoise(0);
            }
        }
    }

    /**
     * Resets the action points of all survivors and zombies in the game.
     */
    private void resetActionPoints() {
        for (Survivor survivor : survivors) {
            survivor.resetActionPoints();
        }

        for (Zombie zombie : zombies) {
            zombie.resetActionPoints();
        }
    }


    /**
     * Handles the zombies' phase of the game.
     * Zombies take their actions.
     */
    private void playZombiesPhase() {
        for(Zombie z : zombies){
            if(!z.isDead()){
                while (z.getActionPoints() > 0) {
                    z.handleAction();
                }
            }

        }
        this.currentPhase = Phase.END;
    }

    /**
     * Handles the survivors' phase of the game.
     * Survivors take their actions.
     */
    private void playSurvivorsPhase() {
        for(Survivor s : survivors){
            if(!s.isDead()) {
                System.out.println("It's " + s.getName() + "'s turn ! He has "+s.getLifePoints() +" life point(s)");

                if(s.getItemHeld() != null)
                    System.out.println(s.getName()+" is holding a(n) "+s.getItemHeld().toString());

                System.out.println("Backpack : " + s.getBackpack().displayItems());
                while (s.getActionPoints() > 0) {
                    // scanner.next();
                    s.handleAction();
                    System.out.println(s.getName() + " has " + s.getActionPoints() + " pts d'action");
                }
                System.out.println();
            }
        }
        this.currentPhase = Phase.ZOMBIES;
    }

    /**
     * Calculates the number of zombies to spawn based on total survivor skill points.
     *
     * @return The number of zombies to spawn.
     */
    public int getNumberOfZombiesToSpawn(){
        double result = Math.ceil((double) getTotalNumberOfSkillPoints() / 3);
        return result == 0 ? 1 : (int) result;
    }


    /**
     * Calculates the total number of skill points from all survivors.
     *
     * @return The total number of skill points.
     */
    public int getTotalNumberOfSkillPoints(){
        int sum = 0;
        for(Survivor s : survivors){
            sum += s.getSkillPoints();
        }
        return sum;
    }

    public City getCity() {
        return  this.city;
    }

    public void distributeItems() {
        List<Item> listOfItems = Arrays.asList(
                new Riffle(),
                new Pistol(),
                new HealingFiask(),
                new Map(),
                new InfraredGlasses(),
                new FirstAidKit(),
                new Chainsaw(),
                new MasterKey(),
                new Crowbar(),
                new Axe()
        );

        for(Survivor s : this.survivors) {
            RandomListChooser<Item> chooser = new RandomListChooser<>();
            Item item1 = chooser.choose(listOfItems);
            Item item2 = chooser.choose(listOfItems);

            s.getBackpack().addItem(item1);
            s.getBackpack().addItem(item2);
        }
    }

    public void spawnAZombie() {
        RandomListChooser<Manhole> chooser = new RandomListChooser<>();
        Manhole manhole = chooser.choose(this.city.getManholes());

        List<Action<Zombie>> zombieActions = Arrays.asList(
                new ZombieMoveAction(),
                new AttackSurvivorAction()
        );

        List<Zombie> zombiesTypes = Arrays.asList(
                new Runner(zombieActions,this.city),
                new Balaise(zombieActions,this.city),
                new Abomination(zombieActions,this.city),
                new Walker(zombieActions,this.city)
        );

        RandomListChooser<Zombie> ch = new RandomListChooser<>();
        Zombie zombie = ch.choose(zombiesTypes);

        zombie.setArea(manhole);
        // manhole.addZombie(zombie);
        this.zombies.add(zombie);
    }



    private int countOfZombiesAlive(){
        return (int) this.zombies.stream().filter(zombie -> !zombie.isDead()).count();
    }

    private int countOfSurvivorsAlive(){
        return (int) this.survivors.stream().filter(survivor -> !survivor.isDead()).count();
    }
}
