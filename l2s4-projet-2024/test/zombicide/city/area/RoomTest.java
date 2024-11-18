package zombicide.city.area;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import zombicide.actor.survivor.backpack.BackPack;
import zombicide.city.area.door.Door;
import zombicide.city.area.room.Room;
import zombicide.city.area.room.TheContinental;
import zombicide.item.careItem.FirstAidKit;
import zombicide.item.careItem.HealingFiask;
import zombicide.item.attackItem.weapon.Chainsaw;
import zombicide.item.attackItem.weapon.Pistol;
import zombicide.util.Direction;

class RoomTest {

	private Room room;
	private Door door;
	private BackPack backpack;

	@BeforeEach
	public void before() {
		room = new Room(5,6);
		door = new Door();
		backpack = new BackPack(null);

		room.addItem(new FirstAidKit());
		room.addItem(new Chainsaw());
	}
	
	@Test
	void testRoomInitialization() {
		assertNotNull(room);
	}
	
	@Test
	void testAddAllDoorInRoom() {		
		assertNotNull(room.getDoors());
		for(Direction direction : Direction.values()) {
			Door door = room.getDoor(direction);
			assertNotNull(door);
		}
	}

	@Test
	void testOpenAndCloseDoorInRoom(){
		room.addDoor(Direction.UP, door);
		assertTrue(door.isOpen());
		door.close();
		assertFalse(door.isOpen());
		door.open();
		assertTrue(door.isOpen());
	}

	@Test
	public void testGetReverse() {
		assertEquals(Direction.DOWN, Direction.UP.getReverse());
		assertEquals(Direction.LEFT, Direction.RIGHT.getReverse());
		assertEquals(Direction.UP, Direction.DOWN.getReverse());
		assertEquals(Direction.RIGHT, Direction.LEFT.getReverse());
	}

	@Test
	void testLetItems() {

		Pistol item1 = new Pistol();
		HealingFiask item2 = new HealingFiask();
		backpack.addItem(item1);
		backpack.addItem(item2);


		room.letItems(backpack);


		assertTrue(room.getItems().contains(item1));
		assertTrue(room.getItems().contains(item2));
	}

	@Test
	void testDisplayItems(){
		room.displayItems();
	}

	@Test
	void testHasItems(){
		assertTrue(room.hasItems());
	}

	@Test
	void testTheContinental(){
		TheContinental c = new TheContinental(0,0);
		assertTrue(c.isContinental());
	}

	@Test
	void testGetRandomItem(){
		assertNotNull(room.getRandomItem());
	}

}
