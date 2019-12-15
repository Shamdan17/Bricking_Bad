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
  private BrickFactory bf= new BrickFactory();

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

  /**
   * OVERVIEW: Adds a brick to map
   * MODIFIES: map
   * REQUIRES: map not null, Brick specific type supplied
   * EFFECT: adds a new brick of 'type' type to the map if type is not null
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
   * OVERVIEW: removes a brick located at a given position
   * MODIFIES: map
   * REQUIRES: map not null
   * EFFECT: remove a brick from map that exists in the given position
   * @param pos position of brick to be removed
   * @return true if removal was successful, or false otherwise
   */
  public boolean removeBrick(Position pos) {
    boolean isRemoved = map.remove(pos);
    return isRemoved;
  }

  /**
   * OVERVIEW: moves a brick to another location
   * MODIFIES: map
   * REQUIRES: map not null
   * EFFECT: change position of a brick to a given position
   * @param from original position of brick
   * @param to destination of brick
   * @return true if move was successful, or false otherwise
   */
  public boolean moveBrick(Position from, Position to) {
    boolean isMoved = map.move(from, to);
    return isMoved;
  }

    /**
     * OVERVIEW: saves map to storage
     * MODIFIES: storageManager, unixTimestamp
     * REQUIRES: saveManager not null
     * EFFECT: stores a MapBuildData instance in storage manager with unixTimestamp
     * as key
     */
  // TODO: update save and load logic to associate each save with a different key
  public void save() {
    MapBuildData data = map.getData();
    unixTimestamp = Instant.now().getEpochSecond();
    storageManager.put(unixTimestamp, data);
  }

    /**
     * OVERVIEW: loads a map
     * REQUIRE, a previous storage of unixTimestamp exists
     * EFFECT: returns the most recent stored instance of MapBuildData
     * @return instance of MapBuildData which was most recently stored
     */
  public MapBuildData load() {
    MapBuildData data = (MapBuildData) storageManager.get(unixTimestamp);
    return data;
  }

    /**
     * OVERVIEW: returns map data
     * EFFECT: returns an instance of MapBuildData extracted from map
     * @return instance of MapBuildData containing map information
     */
  public MapBuildData getData() {
    MapBuildData data = new MapBuildData(map.getMovables());
    return data;
  }
}
