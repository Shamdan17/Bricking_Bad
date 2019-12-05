package domain.mapbuild;

import domain.model.SpecificType;
import domain.model.brick.Brick;
import domain.model.brick.BrickFactory;
import domain.storage.BinaryStorage;
import domain.storage.StorageManager;
import utils.Position;

import java.time.Instant;


public class MapBuildSession {

    private Map map;
    private StorageManager storageManager;
    private long unixTimestamp;

    public MapBuildSession() {
        map = new Map();
        // TODO: replace this string with a variable value
        storageManager = new BinaryStorage("MapBuild");
    }

    public boolean addBrick(SpecificType type, Position pos) {

        Brick brick = BrickFactory.get(type, pos);
        boolean isAdded = map.add(brick, pos);

        return isAdded;
    }

    public boolean removeBrick(Position pos) {
        boolean isRemoved = map.remove(pos);
        return isRemoved;
    }

    public boolean moveBrick(Position from, Position to) {
        boolean isMoved = map.move(from, to);
        return isMoved;
    }

    // TODO: update save and load logic to associate each save with a different key
    public void save(){
        MapBuildData data = map.getData();
        unixTimestamp = Instant.now().getEpochSecond();
        storageManager.put(unixTimestamp, data);
    }

    public MapBuildData load(){
        MapBuildData data = (MapBuildData) storageManager.get(unixTimestamp);
        return data;
    }


    public MapBuildData getData() {
        MapBuildData data = new MapBuildData(map.getMovables());
        return data;
    }

}
