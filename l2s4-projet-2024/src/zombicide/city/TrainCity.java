package zombicide.city;

import zombicide.city.area.street.Manhole;
import zombicide.city.area.street.Street;
import zombicide.util.Position;

public class TrainCity extends City {
    private static final int WIDTH = 5;
    private static final int HEIGHT = 5;

    public TrainCity() {
        super(WIDTH, HEIGHT);
    }

    @Override
    protected void createSpawnStreet(Position p) {
        int x = p.getX();
        int y = p.getY();
        this.spawn = new Manhole(x, y);
        Street.setSpawn(this.spawn);
        this.areas[y][x] = this.spawn;
    }

    @Override
    protected void createManholes(Position crossroadPos, Position bottomRightPos) {
        this.manholes.add((Manhole) this.getSpawn());
    }
}
