package zombicide.city;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import zombicide.action.Action;
import zombicide.action.MoveAction;
import zombicide.action.zombie.AttackSurvivorAction;
import zombicide.action.zombie.ZombieMoveAction;
import zombicide.actor.survivor.Survivor;
import zombicide.actor.zombie.Abomination;
import zombicide.actor.zombie.Zombie;
import zombicide.city.area.Area;
import zombicide.city.area.room.Room;
import zombicide.city.City;
import zombicide.city.area.street.Street;
import zombicide.util.Direction;

import java.util.Arrays;
import java.util.List;

class CityTest {

	private City city;
	private Survivor survivor1;
	private Survivor survivor2;
	private Abomination abomination1;
	private Abomination abomination2;
	private Abomination abomination3;

	@BeforeEach
	public void before() {
		city = new City(5, 5);

		survivor1 = new Survivor(city);
		survivor2 = new Survivor(city);

		List<Action<Zombie>> zombieActions = Arrays.asList(
				new ZombieMoveAction(),
				new AttackSurvivorAction()
		);

		abomination1 = new Abomination(zombieActions , city);
		abomination2 = new Abomination(zombieActions , city);
		abomination3 = new Abomination(zombieActions , city);

		survivor1.setArea(city.getArea(2,0));
		survivor2.setArea(city.getArea(1,1));

		abomination1.setArea(city.getArea(1, 1));
		abomination2.setArea(city.getArea(3, 3));
		abomination3.setArea(city.getArea(4, 0));
	}


	@Test
	void testInitializationCity() {
		assertNotNull(city);
	}
	
	@Test
	void testCheckSizeOfCity() {
		assertEquals(city.getHeight(), 5);
		assertEquals(city.getWidth(), 5);
	}
	
	@Test
	void testGetArea() {
		Area[][] area2 = city.getAreas();
		for (int i = 0; i < 5; i++) {
	        for (int j = 0; j < 5; j++) {
	            assertNotNull(area2[i][j]);
	        }
	    }
	}
	
	@Test
	void testGetCellUp() {
	    Area[][] areas = city.getAreas();
	    Area expectedArea = areas[1][2];
	    Area actualArea = city.getCellUp(2, 2);
	    assertEquals(expectedArea, actualArea);
	}

	@Test
	void testGetRoom() {
		assertTrue(city.getRooms() != null && !city.getRooms().isEmpty());
	}

	@Test
	void testGetSpawn() {
		assertNotNull(city.getSpawn());
		assertEquals(city.getSpawn().getX() , 2);
		assertEquals(city.getSpawn().getY() , 2);
	}

	@Test
	void testContainsRoom(){
		Room r = (Room) city.getArea(0,0);
		assertTrue(city.containsRoom(r));
	}

	@Test
	void testGetZombies(){
		assertEquals(city.getZombies().size() , 3);
	}

	@Test
	void testGetSurvivors(){
		assertEquals(city.getSurvivors().size() , 2);
	}



	/** TODO Revoir le test (isARoom ne considère pas une Room comme une Room)
	@Test
	void testIsARoom(){
		assertTrue(city.isARoom(new Room(0,0)));

		assertFalse(city.isARoom(new Street(1, 1)));

		assertFalse(city.isARoom(new Manhole(2, 2)));
	}
	**/
}

