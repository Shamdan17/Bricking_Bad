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

  /**
   * Adds a brick to map
   *
   * @param type type of brick to be added
   * @param pos position to which brick will be added
   * @return true if addition was successful, or false otherwise
   */
  public boolean addBrick(SpecificType type, Position pos) {

    Brick brick = BrickFactory.get(type, pos);
    boolean isAdded = map.add(brick, pos);

    return isAdded;
  }

  /**
   * removes a brick located at a given position
   *
   * @param pos position of brick to be removed
   * @return true if removal was successful, or false otherwise
   */
  public boolean removeBrick(Position pos) {
    boolean isRemoved = map.remove(pos);
    return isRemoved;
  }

  /**
   * moves a brick to another location
   *
   * @param from original position of brick
   * @param to destination of brick
   * @return true if move was successful, or false otherwise
   */
  public boolean moveBrick(Position from, Position to) {
    boolean isMoved = map.move(from, to);
    return isMoved;
  }

    /**
     * saves map to storage
     */
  // TODO: update save and load logic to associate each save with a different key
  public void save() {
    MapBuildData data = map.getData();
    unixTimestamp = Instant.now().getEpochSecond();
    storageManager.put(unixTimestamp, data);
  }

    /**
     * loads a map
     * @return instance of MapBuildData
     */
  public MapBuildData load() {
    MapBuildData data = (MapBuildData) storageManager.get(unixTimestamp);
    return data;
  }

    /**
     * returns map data
     * @return instance of MapBuildData containing map information
     */
  public MapBuildData getData() {
    MapBuildData data = new MapBuildData(map.getMovables());
    return data;
  }
}
