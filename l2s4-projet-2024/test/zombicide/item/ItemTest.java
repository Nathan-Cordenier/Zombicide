package zombicide.item;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import zombicide.actor.survivor.Survivor;
import zombicide.actor.survivor.backpack.BackPack;
import zombicide.city.City;
import zombicide.item.careItem.CareItem;
import zombicide.item.careItem.FirstAidKit;
import zombicide.item.careItem.HealingFiask;
import zombicide.item.attackItem.weapon.*;

public class ItemTest {
    //Actor
    private Weapon pistol;
    private Weapon axe;
    private Weapon chainsaw;
    private Weapon crowbar;
    private Weapon riffle;
    private CareItem healingFlask;
    private CareItem firstAidKit;
    private InfraredGlasses infraredGlasses;
    private Map map;
    private MasterKey masterKey;
    private BackPack backPack;
    private Survivor survivor;
    private City city;

    @BeforeEach
    public void before(){
        this.pistol = new Pistol();
        this.axe = new Axe();
        this.chainsaw = new Chainsaw();
        this.riffle = new Riffle();
        this.crowbar = new Crowbar();
        this.healingFlask = new HealingFiask();
        this.firstAidKit = new FirstAidKit();
        this.infraredGlasses = new InfraredGlasses();
        this.map = new Map();
        this.masterKey = new MasterKey();
        this.backPack = new BackPack(null);
        this.city = new City(5,5);
        this.survivor = new Survivor(this.city);
    }

    @Test
    void testToString() {
        String mapString = map.toString();
        assertEquals(mapString, "map");
        String masterKeyString = masterKey.toString();
        assertEquals(masterKeyString, "master key");
        String infraredGlassesString = infraredGlasses.toString();
        assertEquals(infraredGlassesString, "infrared glasses");
        String firstAidKitString = firstAidKit.toString();
        assertEquals(firstAidKitString, "first aid kit");
        String healingFiaskString = healingFlask.toString();
        assertEquals(healingFiaskString, "healing flask");
        String axeString = axe.toString();
        assertEquals(axeString, "axe");
        String riffleString = riffle.toString();
        assertEquals(riffleString, "riffle");
        String chainsawString = chainsaw.toString();
        assertEquals(chainsawString, "chainsaw");
        String crowbarString = crowbar.toString();
        assertEquals(crowbarString, "crowbar");
        String pistolString = pistol.toString();
        assertEquals(pistolString, "pistol");
    }


}
