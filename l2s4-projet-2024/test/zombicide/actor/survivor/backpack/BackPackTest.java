package zombicide.actor.survivor.backpack;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import zombicide.actor.survivor.Survivor;
import zombicide.action.survivor.special.Fighter;
import zombicide.city.City;
import zombicide.item.InfraredGlasses;
import zombicide.item.Item;
import zombicide.item.MasterKey;
import zombicide.item.careItem.FirstAidKit;
import zombicide.item.attackItem.weapon.Chainsaw;
import zombicide.item.attackItem.weapon.Pistol;
import zombicide.item.attackItem.weapon.Riffle;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class BackPackTest {

    private BackPack backpack;
    private Pistol item1;
    private FirstAidKit item2;
    private Riffle item3;
    private MasterKey item4;
    private InfraredGlasses item5;
    private Chainsaw item6;
    private Survivor survivor;

    @BeforeEach
    public void before() {
        backpack = new BackPack(null);
        item1 = new Pistol();
        item2 = new FirstAidKit();
        item3 = new Riffle();
        item4 = new MasterKey();
        item5 = new InfraredGlasses();
        item6 = new Chainsaw();
        survivor = new Survivor(List.of(new Fighter()), new City(5, 5));
    }

    @Test
    void testAddItemAndCheckIfBackpackWasFull(){
        assertNull(backpack.addItem(item1));
        assertNull(backpack.addItem(item2));
        assertNull(backpack.addItem(item3));
        assertNull(backpack.addItem(item4));
        assertNull(backpack.addItem(item5));

        Item itemDropped = backpack.addItem(item6);
        assertNotNull(itemDropped);
        assertTrue(itemDropped.equals(item1) || itemDropped.equals(item2) || itemDropped.equals(item3) || itemDropped.equals(item4) || itemDropped.equals(item5));
        assertTrue(backpack.getItems().contains(item6));
        assertFalse(backpack.getItems().contains(itemDropped));
        assertNull(itemDropped.getSurvivor());
    }

    @Test
    void testRemoveItem(){
        backpack.addItem(item1);
        backpack.addItem(item2);

        backpack.removeItem(item1);

        assertFalse(backpack.getItems().contains(item1));
    }

    @Test
    void testGetRandomItem(){
        backpack.addItem(item1);
        backpack.addItem(item2);
        backpack.addItem(item3);
        Item randomItem = backpack.getRandomItem();
        assertTrue(backpack.getItems().contains(randomItem));
    }

}