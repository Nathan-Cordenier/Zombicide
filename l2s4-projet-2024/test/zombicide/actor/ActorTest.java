package zombicide.actor;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import zombicide.action.Action;
import zombicide.action.survivor.NoiseAction;
import zombicide.action.zombie.AttackSurvivorAction;
import zombicide.action.zombie.ZombieMoveAction;
import zombicide.actor.survivor.Survivor;
import zombicide.actor.zombie.Zombie;
import zombicide.actor.zombie.Abomination;
import zombicide.actor.zombie.Balaise;
import zombicide.actor.zombie.Runner;
import zombicide.actor.zombie.Walker;
import zombicide.city.area.Area;
import zombicide.city.area.room.Room;
import zombicide.city.City;
import zombicide.item.Item;
import zombicide.item.attackItem.weapon.*;
import zombicide.action.survivor.special.Fighter;
import zombicide.action.survivor.special.Lucky;

import java.util.List;

import java.util.Arrays;


public class ActorTest {
    private Survivor survivor;
    private Zombie balaise;
    private Zombie runner;
    private Zombie walker;
    private Zombie abomination;
    private Item riffle;
    private Area room;
    private NoiseAction noiseAction;
    private Fighter fighter;
    private Lucky lucky;
    private City city;

    @BeforeEach
    public void before(){
        List<Action<Zombie>> zombieSurvivor = Arrays.asList(
                new ZombieMoveAction(),
                new AttackSurvivorAction()
        );
        this.city = new City(5,5);
        this.survivor = new Survivor(this.city);
        this.balaise = new Balaise(zombieSurvivor , this.city);
        this.abomination = new Abomination(zombieSurvivor , this.city);
        this.walker = new Walker(zombieSurvivor , this.city);
        this.runner = new Runner(zombieSurvivor , this.city);
        this.riffle = new Riffle();
        this.room = new Room(6,7);
        this.noiseAction = new NoiseAction();
        this.fighter = new Fighter();
        this.lucky = new Lucky();
    }

    @Test
    void testActorInitialization(){
        assertNotNull(balaise);
        assertNotNull(abomination);
        assertNotNull(walker);
        assertNotNull(runner);
        assertNotNull(survivor);
    }

    @Test
    void testGetZombieAttack(){
        assertEquals(balaise.getAttackPoints(), 2);
        assertEquals(abomination.getAttackPoints(), 3);
        assertEquals(walker.getAttackPoints(), 1);
        assertEquals(runner.getAttackPoints(), 1);
    }

    @Test
    void testGetIsStrong(){
        assertTrue(balaise.getIsStrong());
        assertTrue(abomination.getIsStrong());
        assertFalse(walker.getIsStrong());
        assertFalse(runner.getIsStrong());
    }

    @Test
    void testHandleItem(){
        survivor.setItemHeld(riffle);
        assertEquals(riffle, survivor.getItemHeld());
    }

    @Test
    void testSkillPoints(){
        assertEquals(survivor.getSkillPoints(), 0);
        survivor.increaseSkillPoints();
        assertEquals(survivor.getSkillPoints(), 1);
    }

    @Test
    void testLevelReached(){
        assertFalse(survivor.isLevelReached());
        for(int i = 0; i < 3; i++){
            survivor.increaseSkillPoints();
        }
        assertEquals(survivor.getSkillPoints(), 3);
        assertTrue(survivor.isLevelReached());
        for(int i = 0; i < 4; i++){
            survivor.increaseSkillPoints();
        }
        assertEquals(survivor.getSkillPoints(), 7);
        assertTrue(survivor.isLevelReached());
        for(int i = 0; i < 4; i++){
            survivor.increaseSkillPoints();
        }
        assertEquals(survivor.getSkillPoints(), 11);
        assertTrue(survivor.isLevelReached());
    }

    @Test
    void testGetLifePointsOfActor(){
        assertEquals(survivor.getLifePoints(), 5);
        assertEquals(abomination.getLifePoints(), 6);
    }

    @Test
    void testAddLifePointsOfActor(){
        assertEquals(survivor.getLifePoints(), 5);
        survivor.addLifePoints(1);
        assertEquals(survivor.getLifePoints(), 6);
    }

    @Test
    void testGetActionPointsForActor(){
        assertEquals(survivor.getActionPoints(), 3);
    }

    @Test
    void testSetAndGetAreaOfActor(){
        survivor.setArea(room);
        assertEquals(survivor.getArea().getY(), room.getY());
        assertEquals(survivor.getArea().getX(), room.getX());
    }

    @Test
    void testMakeNoiseFromSurvivor(){
        survivor.setArea(room);
        noiseAction.setNoiseLevel(1);
        assertEquals(0, room.getNoise());
        noiseAction.doSomething(survivor);
        assertEquals(1, room.getNoise());
    }

    @Test
    void testGetBackpackOfSurvivor(){
        assertNotNull(survivor.getBackpack());
    }

    @Test
    void testIfSurvivorHasRoles(){
        assertFalse(survivor.hasRoles());
        survivor.getRoles().add(fighter);
        assertTrue(survivor.hasRoles());
    }

    @Test
    public void testSetArea() {
        survivor.setArea(room);
        assertEquals(room, survivor.getArea());
    }

    @Test
    public void testRemoveLifePoints() {
        survivor.addLifePoints(5);
        assertEquals(survivor.getLifePoints(), 10);
        survivor.removeLifePoints(5);
        assertEquals(survivor.getLifePoints(), 5);
    }

    @Test
    public void testAddSkillPoints(){
        survivor.addSkillPoints(5);
        assertEquals(5, survivor.getSkillPoints());
    }

    @Test
    public void testHoldAnItemOfSurvivor(){
        survivor.setItemHeld(riffle);
        assertTrue(survivor.holdAnItem());
    }

    @Test
    void testDisplay(){
        System.out.println(abomination.toString());
        System.out.println(survivor.toString());
    }

    @Test
    void testHandleAction(){
        abomination.setArea(this.city.getArea(3,2));
        survivor.setArea(this.city.getSpawn());
        abomination.handleAction();
        survivor.handleAction();
    }

    @Test
    public void testIsDead() {
        survivor.removeLifePoints(5);
        assertTrue(survivor.isDead());
    }

    @Test
    public void testIncreaseActionPointsOnSurvivor(){
        assertEquals(survivor.getSkillPoints(), 0);
        survivor.increaseSkillPoints();
        assertEquals(survivor.getSkillPoints(), 1);
    }

    @Test
    public void testSetActionPointsOnActor(){
        survivor.setActionPoints(5);
        assertEquals(survivor.getActionPoints(), 5);
    }


}
