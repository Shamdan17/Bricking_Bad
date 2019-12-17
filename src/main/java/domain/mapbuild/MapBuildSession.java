package domain.mapbuild;

import domain.model.SpecificType;
import domain.model.brick.Brick;
import domain.model.brick.BrickFactory;
import domain.storage.BinaryStorage;
import domain.storage.StorageManager;
import utils.Constants;
import utils.Position;

import java.time.Instant;
import java.util.UUID;

public class MapBuildSession {

  private Map map;
  private StorageManager storageManager;
  private long unixTimestamp;
  private BrickFactory bf = new BrickFactory();

  /**
   * OVERVIEW: constructor for MapBuildSession MODIFIES: map, storageManager EFFECT: create a new
   * instance of MapBuildSession
   */
  public MapBuildSession() {
    map = new Map();
    // TODO: replace this string with a variable value
    storageManager = new BinaryStorage("MapBuild");
  }

  public boolean buildMap(int simple, int halfMetal, int mine, int wrapper) {

    if (simple < Constants.MIN_SIMPLE_BRICK) return false;
    if (halfMetal < Constants.MIN_HALF_METAL_BRICK) return false;
    if (mine < Constants.MIN_MINE_BRICK) return false;
    if (wrapper < Constants.MIN_WRAPPER_BRICK) return false;

    int x = Constants.BRICK_START_X;
    int y = Constants.BRICK_START_Y;

    for (int i = 0; i < simple; ++i) {
      addBrick(SpecificType.SimpleBrick, new Position(x, y));
      x += Constants.BRICK_OFFSET_X;
      if (x + Constants.BRICK_LENGTH > Constants.FRAME_WIDTH) {
        y += Constants.BRICK_OFFSET_Y;
        x = Constants.BRICK_START_X;
      }
    }

    for (int i = 0; i < halfMetal; ++i) {
      addBrick(SpecificType.HalfMetalBrick, new Position(x, y));
      x += Constants.BRICK_OFFSET_X;
      if (x + Constants.BRICK_LENGTH > Constants.FRAME_WIDTH) {
        y += Constants.BRICK_OFFSET_Y;
        x = Constants.BRICK_START_X;
      }
    }

    for (int i = 0; i < mine; ++i) {
      addBrick(SpecificType.MineBrick, new Position(x, y));
      x += Constants.BRICK_OFFSET_X;
      if (x + Constants.BRICK_LENGTH > Constants.FRAME_WIDTH){
          y += Constants.BRICK_OFFSET_Y;
          x = Constants.BRICK_START_X;
      }
    }

    for (int i = 0; i < wrapper; ++i) {
      addBrick(SpecificType.WrapperBrick, new Position(x, y));
      x += Constants.BRICK_OFFSET_X;
      if (x + Constants.BRICK_LENGTH > Constants.FRAME_WIDTH){
          y += Constants.BRICK_OFFSET_Y;
          x = Constants.BRICK_START_X;
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

  /**
   * OVERVIEW: saves map to storage MODIFIES: storageManager, unixTimestamp REQUIRES: saveManager
   * not null EFFECT: stores a MapBuildData instance in storage manager with unixTimestamp as key
   */
  // TODO: update save and load logic to associate each save with a different key
  public void save() {
    MapBuildData data = map.getData();
    unixTimestamp = Instant.now().getEpochSecond();
    storageManager.put(unixTimestamp, data);
  }

  /**
   * OVERVIEW: loads a map REQUIRE, a previous storage of unixTimestamp exists EFFECT: returns the
   * most recent stored instance of MapBuildData
   *
   * @return instance of MapBuildData which was most recently stored
   */
  public void load() {
    MapBuildData data = (MapBuildData) storageManager.get(unixTimestamp);
    map = new Map(data);
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
