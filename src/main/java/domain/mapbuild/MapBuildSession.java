package domain.mapbuild;

import domain.model.SpecificType;
import domain.model.brick.Brick;
import domain.model.brick.BrickFactory;
import domain.storage.BinaryStorage;
import domain.storage.StorageManager;
import utils.Constants;
import utils.Position;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

public class MapBuildSession {

    private Map map;
    private StorageManager storageManager;
    private String unixTimestamp;
    private BrickFactory bf = new BrickFactory();

    /**
     * OVERVIEW: constructor for MapBuildSession
     * MODIFIES: map, storageManager
     * EFFECT: create a new instance of MapBuildSession
     */
    public MapBuildSession() {
        map = new Map();
        // TODO: replace this string with a variable value
        storageManager = new BinaryStorage("MapBuild");
    }

    private boolean testMode = false;

    public boolean buildMap(int simple, int halfMetal, int mine, int wrapper) {

        if (simple < Constants.MIN_SIMPLE_BRICK && !testMode) return false;
        if (halfMetal < Constants.MIN_HALF_METAL_BRICK && !testMode) return false;
        if (mine < Constants.MIN_MINE_BRICK && !testMode) return false;
        if (wrapper < Constants.MIN_WRAPPER_BRICK && !testMode) return false;
        map = new Map();
        for (int i = 0; i < simple; ++i) {
            Random rand = new Random();
            int x = rand.nextInt(Constants.BRICK_RIGHT_BOUND);
            int y = rand.nextInt(Constants.HALF_METAL_BRICK_YLIMIT - Constants.BRICK_UPPER_BOUND) + Constants.BRICK_UPPER_BOUND;
            while(!addBrick(SpecificType.SimpleBrick, new Position(x, y))){
                x = rand.nextInt(Constants.BRICK_RIGHT_BOUND);
                y = rand.nextInt(Constants.HALF_METAL_BRICK_YLIMIT - Constants.BRICK_UPPER_BOUND) + Constants.BRICK_UPPER_BOUND;
            }
        }

        for (int i = 0; i < halfMetal; ++i) {
            Random rand = new Random();
            int x = rand.nextInt(Constants.BRICK_RIGHT_BOUND);
            int y = rand.nextInt(Constants.BRICK_LOWER_BOUND - Constants.HALF_METAL_BRICK_YLIMIT)  + Constants.HALF_METAL_BRICK_YLIMIT;
            while(!addBrick(SpecificType.HalfMetalBrick, new Position(x, y))){
                x = rand.nextInt(Constants.BRICK_RIGHT_BOUND);
                y = rand.nextInt(Constants.BRICK_LOWER_BOUND - Constants.HALF_METAL_BRICK_YLIMIT)  + Constants.HALF_METAL_BRICK_YLIMIT;
            }
        }

        for (int i = 0; i < mine; ++i) {
            Random rand = new Random();
            int x = rand.nextInt(Constants.BRICK_RIGHT_BOUND);
            int y = rand.nextInt(Constants.HALF_METAL_BRICK_YLIMIT - Constants.BRICK_UPPER_BOUND) + Constants.BRICK_UPPER_BOUND;
            while(!addBrick(SpecificType.MineBrick, new Position(x, y))){
                x = rand.nextInt(Constants.BRICK_RIGHT_BOUND);
                y = rand.nextInt(Constants.HALF_METAL_BRICK_YLIMIT - Constants.BRICK_UPPER_BOUND) + Constants.BRICK_UPPER_BOUND;
            }
        }

        for (int i = 0; i < wrapper; ++i) {
            Random rand = new Random();
            int x = rand.nextInt(Constants.BRICK_RIGHT_BOUND);
            int y = rand.nextInt(Constants.HALF_METAL_BRICK_YLIMIT - Constants.BRICK_UPPER_BOUND) + Constants.BRICK_UPPER_BOUND;
            while(!addBrick(SpecificType.WrapperBrick, new Position(x, y))){
                x = rand.nextInt(Constants.BRICK_RIGHT_BOUND);
                y = rand.nextInt(Constants.HALF_METAL_BRICK_YLIMIT - Constants.BRICK_UPPER_BOUND) + Constants.BRICK_UPPER_BOUND;
            }
        }

        return true;
    }


    /**
     * OVERVIEW: Adds a brick to map MODIFIES: map REQUIRES: map not null, Brick specific type
     * supplied EFFECT: adds a new brick of 'type' type to the map if type is not null
     *
     * @param type type of brick to be added
     * @param pos position to which brick will be added
     * @return true if addition was successful, or false otherwise
     */
    public boolean addBrick(SpecificType type, Position pos) {
        boolean isAdded = false;

        if (type != null) {
            Brick brick = bf.get(type, pos);
            isAdded = map.add(brick, pos);
        }

        return isAdded;
    }

    /**
     * OVERVIEW: removes a brick located at a given position MODIFIES: map REQUIRES: map not null
     * EFFECT: remove a brick from map that exists in the given position
     *
     * @param ID ID of brick to be removed
     * @return true if removal was successful, or false otherwise
     */
    public boolean removeBrick(UUID ID) {
        boolean isRemoved = map.remove(ID);
        return isRemoved;
    }

    /**
     * OVERVIEW: moves a brick to another location MODIFIES: map REQUIRES: map not null EFFECT: change
     * position of a brick to a given position
     *
     * @param ID ID of brick to be removed
     * @param to destination of brick
     * @return true if move was successful, or false otherwise
     */
    public boolean moveBrick(UUID ID, Position to) {
        boolean isMoved = map.move(ID, to);
        return isMoved;
    }

    public void dragBrick(UUID ID,Position to){
        map.drag(ID,to);
    }

    /**
     * OVERVIEW: saves map to storage
     * MODIFIES: storageManager, unixTimestamp
     * REQUIRES: saveManager not null
     * EFFECT: stores a MapBuildData instance in storage manager with unixTimestamp
     * as key
     */
    public void save(String name) {
        MapBuildData data = map.getData();
        storageManager.put(name, data);
    }

    public void save() {
        MapBuildData data = map.getData();
        unixTimestamp = String.valueOf(Instant.now().getEpochSecond());
        storageManager.put(unixTimestamp, data);
    }

    /**
     * OVERVIEW: loads a map REQUIRE, a previous storage of unixTimestamp exists
     * EFFECT: returns the most recent stored instance of MapBuildData
     *
     * @return instance of MapBuildData which was most recently stored
     */
    public void load() {
        MapBuildData data = (MapBuildData) storageManager.get(unixTimestamp);
        map = new Map(data);
    }

    public void load(String name) {
        MapBuildData data = (MapBuildData) storageManager.get(name);
        map = new Map(data);
    }

    public List<String> getMapList(){
        return new ArrayList<>(storageManager.keySet());
    }

    /**
     * OVERVIEW: returns map data EFFECT: returns an instance of MapBuildData extracted from map
     *
     * @return instance of MapBuildData containing map information
     */
    public MapBuildData getData() {
        return map.getData();
    }
}
