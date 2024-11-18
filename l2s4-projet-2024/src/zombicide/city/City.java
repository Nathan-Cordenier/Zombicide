package zombicide.city;


import zombicide.actor.survivor.Survivor;
import zombicide.actor.zombie.Zombie;
import zombicide.city.area.Area;
import zombicide.city.area.room.Room;
import zombicide.city.area.room.TheContinental;
import zombicide.city.area.room.ThePharmacy;
import zombicide.city.area.street.Manhole;
import zombicide.city.area.street.Street;
import zombicide.city.area.door.Door;
import zombicide.item.InfraredGlasses;
import zombicide.item.Item;
import zombicide.item.Map;
import zombicide.item.MasterKey;
import zombicide.item.careItem.FirstAidKit;
import zombicide.item.careItem.HealingFiask;
import zombicide.item.attackItem.weapon.*;
import zombicide.util.Direction;
import zombicide.util.Position;
import zombicide.util.listchooser.RandomListChooser;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class City {
    protected final Area[][] areas;
    private final Random random;
    protected Street spawn;
    private TheContinental theContinental;
    private ThePharmacy thePharmacy;
    private static final String CLOSE_DOWN = "------";
    private List<Room> rooms;
    private List<Item> items;
    protected List<Manhole> manholes;
    private List<Survivor> survivors;
    private List<Zombie> zombies;

    private static final int MINIMAL_ITEMS = 1;

    private static final int MAXIMAL_ITEMS = 5;

    /**
     * Constructs a new City object with the specified width and height.
     *
     * @param width  the width of the city
     * @param height the height of the city
     */
    public City(int width, int height) {
        this.areas = new Area[height][width];
        this.random = new Random();
        this.rooms = new ArrayList<>();
        this.items = new ArrayList<>();
        this.manholes = new ArrayList<>();
        this.survivors = new ArrayList<>();
        this.zombies = new ArrayList<>();
        initCity();
    }

    /**
     * Retrieves all the survivors present in the city.
     *
     * @return A list of all survivors in the city.
     */
    public List<Survivor> getSurvivors() {
        this.survivors.clear();

        for (int i = 0; i < getHeight(); i++) {
            for (int j = 0; j < getWidth(); j++) {
                Area area = this.getArea(j, i);
                this.survivors.addAll(area.getSurvivors());
            }
        }
        return this.survivors;
    }

    /**
     * Retrieves all the zombies present in the city.
     *
     * @return A list of all zombies in the city.
     */
    public List<Zombie> getZombies() {
        this.zombies.clear();

        for (int i = 0; i < getHeight(); i++) {
            for (int j = 0; j < getWidth(); j++) {
                Area area = this.getArea(j, i);
                this.zombies.addAll(area.getZombies());
            }
        }
        return this.zombies;
    }

    /**
     * Retrieves the array of areas representing the city.
     *
     * @return The array of areas representing the city.
     */
    public Area[][] getAreas() {
        return this.areas;
    }

    public Area getArea(int x, int y) {
        return this.areas[y][x];
    }

    /**
     * Initializes the city by splitting the areas and creating rooms.
     */
    private void initCity() {
        Position topLeftPos = getTopLeftPosition();
        Position bottomRightPos = getBottomRightPosition();
        splitAreas(topLeftPos, bottomRightPos);
        createRooms();
        dispatchItems();
     }

    /**
     * Retrieves the position of the bottom-right corner of the city.
     *
     * @return The position of the bottom-right corner of the city.
     */
    private Position getBottomRightPosition() {
        return new Position(getWidth() - 1, getHeight() - 1);
    }

    /**
     * Retrieves the position of the top-left corner of the city.
     *
     * @return The position of the top-left corner of the city.
     */
    private Position getTopLeftPosition() {
        return new Position(0, 0);
    }

    /**
     * Initializes doors for the rooms and areas.
     *
     * @param rooms The list of rooms in the city.
     */
    private void initDoors(List<Room> rooms) {
        createDoors();

        for (Room r : rooms) {
            for (Direction d : Direction.values()) {
                r.getDoor(d).close();
            }
        }

        for (int i = 0; i < this.getWidth(); i++) {
            this.getAreas()[0][i].getDoor(Direction.UP).close();
        }
        
         for (int i = 0 ; i < this.getHeight(); i++) {
        	 this.getAreas()[i][0].getDoor(Direction.LEFT).close();
         }

         for (int i=0; i<this.getWidth(); i++)
             this.getAreas()[this.getHeight()-1][i].getDoor(Direction.DOWN).close();

         for (int i=0; i<this.getHeight(); i++)
             this.getAreas()[i][getWidth()-1].getDoor(Direction.RIGHT).close();
    }

    /**
     * Creates a spawn street at the given position.
     *
     * @param p The position of the spawn street.
     */
    protected void createSpawnStreet(Position p) {
        int x = p.getX();
        int y = p.getY();
        this.spawn = new Street(x, y);
        Street.setSpawn(this.spawn);
        this.areas[y][x] = this.spawn;
    }

    /**
     * Generates a random position between the given bounds.
     *
     * @param pos1 The first position.
     * @param pos2 The second position.
     * @return A random position between the bounds.
     */
    private Position getRandomCrossRoadPos(Position pos1, Position pos2) {
        return getRandomPos(pos1, pos2, 2);
    }

    /**
     * Generates a random position between the given bounds.
     *
     * @param pos1 The first position.
     * @param pos2 The second position.
     * @return A random position between the bounds.
     */
    private Position getRandomRoomPos(Position pos1, Position pos2) {
        return getRandomPos(pos1, pos2, 0);
    }

    /**
     * Generates a random position between the given bounds.
     *
     * @param pos1      The first position.
     * @param pos2      The second position.
     * @param delimiter The delimiter to adjust randomness.
     * @return A random position between the bounds.
     */
    private Position getRandomPos(Position pos1, Position pos2, int delimiter) {
        int x = random.nextInt((pos2.getX() - delimiter + 1) - (pos1.getX() + delimiter)) + pos1.getX() + delimiter;
        int y = random.nextInt((pos2.getY() - delimiter + 1) - (pos1.getY() + delimiter)) + pos1.getY() + delimiter;
        return new Position(x, y);
    }

    /**
     * Splits the areas of the city recursively.
     *
     * @param topLeftPos      The top left position of the area.
     * @param bottomRightPos  The bottom right position of the area.
     */
    private void splitAreas(Position topLeftPos, Position bottomRightPos) {
        Position crossroadPos = getRandomCrossRoadPos(topLeftPos, bottomRightPos);

        if (!spawnAlreadyCreated()) {
            createSpawnStreet(crossroadPos);
            createManholes(crossroadPos, bottomRightPos);
        }
        createStreets(crossroadPos, topLeftPos, bottomRightPos);

        List<Position[]> areasPositions = getSplittedPositions(crossroadPos, topLeftPos, bottomRightPos);
        for (Position[] positions : areasPositions) {
            Position newTopLeftPos = positions[0];
            Position newBottomRightPos = positions[1];
            if (isAreaSplittable(newTopLeftPos, newBottomRightPos)) {
                splitAreas(newTopLeftPos, newBottomRightPos);
            }
        }
    }

    private boolean spawnAlreadyCreated() {
        return this.spawn != null;
    }
    
    /**
     * Retrieves an empty position for a room.
     *
     * @return An empty position for a room.
     */
    private Position getEmptyRoomPos() {
        Position pos;
        do {
            pos = getRandomRoomPos(getTopLeftPosition(), getBottomRightPosition());
        } while (this.getAreas()[pos.getY()][pos.getX()] != null);
        return pos;
    }

    /**
     * Creates a special room of the given type and adds it to the city.
     *
     * @param room  The class representing the special room.
     * @param rooms The list of rooms in the city.
     * @return The created special room.
     */
    private Room createSpecialRoom(Class<? extends Room> room, List<Room> rooms) {
        Position pos = getEmptyRoomPos();
        int x = pos.getX();
        int y = pos.getY();

        try {
            Room r = room.getDeclaredConstructor(int.class, int.class).newInstance(x, y);
            this.areas[y][x] = r;
            rooms.add(r);
            return r;
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * Initializes the city by creating special rooms and regular rooms.
     */
    private void createRooms() {

        this.theContinental = (TheContinental) createSpecialRoom(TheContinental.class, rooms);
        this.thePharmacy = (ThePharmacy) createSpecialRoom(ThePharmacy.class, rooms);

        for (int i = 0; i < this.getHeight(); i++) {
            for (int j = 0; j < this.getWidth(); j++) {
                if (this.areas[i][j] == null) {
                    Room r = new Room(j, i);
                    this.areas[i][j] = r;
                    rooms.add(r);
                }
            }
        }
        initDoors(rooms);
    }

    /**
     * Creates manhole streets at the extremities of the principal crossroad's streets.
     *
     * @param crossroadPos    The position of the principal crossroad where streets intersect.
     * @param bottomRightPos  The position representing the bottom-right corner of the area to be considered.
     */
    protected void createManholes(Position crossroadPos, Position bottomRightPos) {
        this.areas[0][crossroadPos.getX()] = new Manhole(crossroadPos.getX(), 0);
        this.areas[crossroadPos.getY()][bottomRightPos.getX()] = new Manhole(bottomRightPos.getX(), crossroadPos.getY());
        this.areas[bottomRightPos.getY()][crossroadPos.getX()] = new Manhole(crossroadPos.getX(), bottomRightPos.getY());
        this.areas[crossroadPos.getY()][0] = new Manhole(0, crossroadPos.getY());

        this.manholes.add((Manhole) this.areas[0][crossroadPos.getX()]);
        this.manholes.add((Manhole) this.areas[crossroadPos.getY()][bottomRightPos.getX()]);
        this.manholes.add((Manhole) this.areas[bottomRightPos.getY()][crossroadPos.getX()]);
        this.manholes.add((Manhole) this.areas[crossroadPos.getY()][0]);
    }

    /**
     * Gets the positions of the areas after splitting.
     *
     * @param crossroadPos    The position of the crossroad.
     * @param topLeftPos      The top left position of the area.
     * @param bottomRightPos  The bottom right position of the area.
     * @return A list of positions representing the splitted areas.
     */
    private List<Position[]> getSplittedPositions(Position crossroadPos, Position topLeftPos, Position bottomRightPos) {
        Position[] topLeftArea = { topLeftPos, new Position(crossroadPos.getX() - 1, crossroadPos.getY() - 1) };
        Position[] topRightArea = { new Position(crossroadPos.getX() + 1, topLeftPos.getY()), new Position(bottomRightPos.getX(), crossroadPos.getY() - 1) };
        Position[] bottomLeftArea = { new Position(topLeftPos.getX(), crossroadPos.getY() + 1), new Position(crossroadPos.getX() - 1, bottomRightPos.getY()) };
        Position[] bottomRightArea = { new Position(crossroadPos.getX() + 1, crossroadPos.getY() + 1), bottomRightPos };
        return List.of(topLeftArea, topRightArea, bottomLeftArea, bottomRightArea);
    }

    /**
     * Creates streets based on the crossroad position and the area bounds.
     *
     * @param crossRoadPos    The position of the crossroad.
     * @param topLeftPos      The top left position of the area.
     * @param bottomRightPos  The bottom right position of the area.
     */
    private void createStreets(Position crossRoadPos, Position topLeftPos, Position bottomRightPos) {
        int width = getAreasWidth(topLeftPos, bottomRightPos);
        int height = getAreasHeight(topLeftPos, bottomRightPos);

        int cX = crossRoadPos.getX();
        int cY = crossRoadPos.getY();

        int tX = topLeftPos.getX();
        int tY = topLeftPos.getY();


        for (int i = 0; i < width; i++) {
            if (this.areas[cY][tX + i] == null) {
                this.areas[cY][tX + i] = new Street(tX + i, cY);
            }
        }


        for (int i = 0; i < height; i++) {
            if (this.areas[tY + i][cX] == null) {
                this.areas[tY + i][cX] = new Street(cX, tY + i);
            }
        }
    }

    /**
     * Checks if an area is splittable based on its size.
     *
     * @param pos1 The top left position of the area.
     * @param pos2 The bottom right position of the area.
     * @return True if the area is splittable, false otherwise.
     */
    private boolean isAreaSplittable(Position pos1, Position pos2) {
        return getAreasWidth(pos1, pos2) >= 5 && getAreasHeight(pos1, pos2) >= 5;
    }


    /**
     * Displays the city by printing the areas.
     */
    public void display() {
        for (int i = 0; i < getHeight(); i++) {
            for (int n = 0; n < 3; n++) {
                for (int j = 0; j < getWidth(); j++) {
                    this.areas[i][j].display(n);
                }
                if (n != 0) {
                    System.out.println('|');
                } else {
                    System.out.println();
                }
            }
        }
        for (int s = 0; s < getWidth(); s++) {
            System.out.print(CLOSE_DOWN);
        }
        System.out.print('\n');
    }
    
    /**
     * Creates doors between areas.
     */
    private void createDoors() {
        for (int i = 0; i < this.getHeight(); i++) {
            for (int j = 0; j < this.getWidth(); j++) {
                Door upDoor = new Door();
                Door leftDoor = new Door();
                try {
                    Area area = this.areas[i][j];
                    area.addDoor(Direction.UP, upDoor);
                    area.addDoor(Direction.LEFT, leftDoor);
                } catch (ArrayIndexOutOfBoundsException ignored) {
                }
                try {
                    Area upArea = this.areas[i - 1][j];
                    upArea.addDoor(Direction.DOWN, upDoor);
                } catch (ArrayIndexOutOfBoundsException ignored) {
                }
                try {
                    Area leftArea = this.areas[i][j - 1];
                    leftArea.addDoor(Direction.RIGHT, leftDoor);
                } catch (ArrayIndexOutOfBoundsException ignored) {
                }
            }
        }
    }

    /**
     * Gets the width of the areas based on the given positions.
     *
     * @param pos1 The top left position of the area.
     * @param pos2 The bottom right position of the area.
     * @return The width of the areas.
     */
    private int getAreasWidth(Position pos1, Position pos2) {
        return pos2.getX() - pos1.getX() + 1;
    }

    /**
     * Gets the height of the areas based on the given positions.
     *
     * @param pos1 The top left position of the area.
     * @param pos2 The bottom right position of the area.
     * @return The height of the areas.
     */
    private int getAreasHeight(Position pos1, Position pos2) {
        return pos2.getY() - pos1.getY() + 1;
    }

    /**
     * Gets the width of the city.
     *
     * @return The width of the city.
     */
    public int getWidth() {
        return this.areas[0].length;
    }

    /**
     * Gets the height of the city.
     *
     * @return The height of the city.
     */
    public int getHeight() {
        return this.areas.length;
    }

    /**
     * Retrieves the area above a specified position.
     *
     * @param x The x-coordinate of the area.
     * @param y The y-coordinate of the area.
     * @return The area above the specified position.
     */
    public Area getCellUp(int x, int y) {
        return this.areas[y-1][x];
    }




    /**
     * Dispatches various items to the rooms in the city.
     * Each item type is randomly added to the city's items list a random number of times within a range.
     * Then, each item is added to a randomly chosen room in the city.
     *
     * Items include:
     * - Axe
     * - Chainsaw
     * - Crowbar
     * - Pistol
     * - Rifle
     * - First Aid Kit
     * - Healing Fiask
     * - Infrared Glasses
     * - Map
     * - Master Key
     *
     * The number of each item type added is determined by random dice rolls.
     * Each item is then placed in a randomly selected room in the city.
     */
    public void dispatchItems() {
        for (int i = 0; i < throwDice(MINIMAL_ITEMS, MAXIMAL_ITEMS + 1); i++) {
            Axe axe = new Axe();
            this.items.add(axe);
        }
        for (int i = 0; i < throwDice(MINIMAL_ITEMS, MAXIMAL_ITEMS + 1); i++) {
            Chainsaw chainsaw = new Chainsaw();
            this.items.add(chainsaw);
        }
        for (int i = 0; i < throwDice(MINIMAL_ITEMS, MAXIMAL_ITEMS + 1); i++) {
            Crowbar crowbar = new Crowbar();
            this.items.add(crowbar);
        }
        for (int i = 0; i < throwDice(MINIMAL_ITEMS, MAXIMAL_ITEMS + 1); i++) {
            Pistol pistol = new Pistol();
            this.items.add(pistol);
        }
        for (int i = 0; i < throwDice(MINIMAL_ITEMS, MAXIMAL_ITEMS + 1); i++) {
            Riffle rifle = new Riffle();
            this.items.add(rifle);
        }
        for (int i = 0; i < throwDice(MINIMAL_ITEMS, MAXIMAL_ITEMS + 1); i++) {
            FirstAidKit firstAidKit = new FirstAidKit();
            this.items.add(firstAidKit);
        }
        for (int i = 0; i < throwDice(MINIMAL_ITEMS, MAXIMAL_ITEMS + 1); i++) {
            HealingFiask healingFiask = new HealingFiask();
            this.items.add(healingFiask);
        }
        for (int i = 0; i < throwDice(MINIMAL_ITEMS, MAXIMAL_ITEMS + 1); i++) {
            InfraredGlasses infraredGlasses = new InfraredGlasses();
            this.items.add(infraredGlasses);
        }
        for (int i = 0; i < throwDice(MINIMAL_ITEMS, MAXIMAL_ITEMS + 1); i++) {
            Map map = new Map();
            this.items.add(map);
        }
        for (int i = 0; i < throwDice(MINIMAL_ITEMS, MAXIMAL_ITEMS + 1); i++) {
            MasterKey masterKey = new MasterKey();
            this.items.add(masterKey);
        }

        int sizeRooms = this.rooms.size();
        for (Item i : items) {
            int r = random.nextInt(sizeRooms);
            this.rooms.get(r).addItem(i);
        }
    }


    /**
     * Simulates throwing a dice with specified range.
     *
     * @param x The minimum value of the dice (inclusive).
     * @param y The maximum value of the dice (exclusive).
     * @return An integer representing the result of the dice throw.
     *         The result will be in the range [x, y).
     */
    private int throwDice(int x, int y) {
        return random.nextInt(y - x) + x;
    }


    public List<Room> getRooms() {
        return rooms;
    }

    public Street getSpawn() {
        return spawn;
    }

    public boolean containsRoom(Room r){
        return this.rooms.contains(r);
    }

    public List<Manhole> getManholes() {
        return manholes;
    }


    public List<Item> listOfItems(){
        return Arrays.asList(
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
    }

    public void dispatchItems2(){
        List<Item> items = listOfItems();
        RandomListChooser<Item> chooser = new RandomListChooser<>();

        for (Room r : this.rooms) {
            for (int x = 0; x < throwDice(MINIMAL_ITEMS, MAXIMAL_ITEMS + 1); x++) {
                Item item = chooser.choose(items);
                r.addItem(item);
            }
        }
    }

    public Area getAreaNoiseMax() {
        Area areaMaxNoise = this.spawn;
        int maxNoise = 0;
        for (int i=0; i<getWidth(); i++) {
            for (int j=0; j<getHeight(); j++) {
                int noiseArea = this.getArea(i, j).getNoise();
                if (noiseArea > maxNoise) {
                    areaMaxNoise = this.getArea(i, j);
                    maxNoise = noiseArea;
                }
            }
        }
        return areaMaxNoise;
    }
}
