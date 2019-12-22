package domain.mapbuild;

import domain.model.shape.MovableShape;
import utils.Position;
import utils.physics.PhysicsEngine;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Map {

  // TODO: seek adding IDs to objects

  List<MovableShape> objects;
  PhysicsEngine pEngine = PhysicsEngine.getInstance();

  public Map(MapBuildData data) {
    objects = data.getMovables();
  }

  /** OVERVIEW: constructor for Map MODIFIES: objects EFFECTS: construct a map instance */
  public Map() {
    objects = new ArrayList();
  }

  /**
   * OVERVIEW: Adds a new object to map, before addition it checks if the given object collides with
   * any of the existing objects
   *
   * <p>MODIFIES: objects EFFECT: if given position is positive and does not collide with any other
   * object then add it to list of objects
   *
   * @param msh the object to be added
   * @param pos the positoin of addition of that object
   * @return true if addition is successful, or false otherwise
   */
  public boolean add(MovableShape msh, Position pos) {
    if (pos.getX() < 0 || pos.getY() < 0) return false;
    for (int i = 0; i < objects.size(); ++i) {
      if (pEngine.isCollided(msh, objects.get(i))) return false;
    }
    objects.add(msh);

    return true;
  }

  /**
   * OVERVIEW: Removes an object at a particular position MODIFIES: objects EFFECT: if there is an
   * object with given position, then remove it from object list
   *
   * @param ID ID of object to be removed
   * @return true if removal was successful, or false otherwise
   */
  public boolean remove(UUID ID) {
    for (int i = 0; i < objects.size(); ++i) {
      if (ID.equals(objects.get(i).getID())) {
        objects.remove(i);
        return true;
      }
    }

    return false;
  }

  /**
   * OVERVIEW: moves an object from a place to another MODIFIES: objects, movableShape that exists
   * in from EFFECT: if object exists at position from, then move it to position to if it does not
   * collide with any object at the new position
   *
   * @param ID ID of object
   * @param to destination of object
   * @return true if moving is successful, or false otherwise
   */
  public boolean move(UUID ID, Position to) {

    MovableShape msh = null;
    Position originalPos = new Position(0, 0);
    // check if object exists in movables
    for (MovableShape ms : objects) {
      if (ID.equals(ms.getID())) {
        originalPos = new Position(ms.getPosition().getX(), ms.getPosition().getY());
        ms.setPosition(to);
        msh = ms;
        break;
      }
    }
    // if object does not exist terminate
    if (msh == null) return false;

    // check if destination does not collide with any other object
    for (MovableShape ms : objects) {
      if (ms.getID().equals(msh.getID())) continue;
      if (pEngine.isCollided(msh, ms)) {
        msh.setPosition(originalPos);
        return false;
      }
    }

    return true;
  }

  public void drag(UUID ID, Position to) {
    for (MovableShape ms : objects) {
      if (ms.getID().equals(ID)) ms.setPosition(to);
    }
  }

  public boolean isCollided(MovableShape brick) {
    for (MovableShape ms : objects) {
      if (pEngine.isCollided(brick, ms)) {
        return false;
      }
    }
    return true;
  }

  /**
   * OVERVIEW: getter for movables
   *
   * @return list of movables contained in the map
   */
  public List<MovableShape> getMovables() {
    return objects;
  }

  /**
   * OVERVIEW: wraps objects in a MapBuildData instance EFFECT: creates a new MapBuildData instance
   * and returns it
   *
   * @return a MapBuildData instance
   */
  public MapBuildData getData() {
    List<MovableShape> list = new ArrayList<>();
    for (MovableShape ms : objects) {
      list.add(ms.copy());
    }
    MapBuildData data = new MapBuildData(list);
    return data;
  }
}
