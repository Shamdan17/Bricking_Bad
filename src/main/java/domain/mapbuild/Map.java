package domain.mapbuild;

import domain.model.shape.MovableShape;
import utils.Position;
import utils.physics.PhysicsEngine;

import java.util.ArrayList;
import java.util.List;

public class Map {

  // TODO: seek adding IDs to objects

  List<MovableShape> objects;
  PhysicsEngine pEngine = PhysicsEngine.getInstance();

  public Map() {
    objects = new ArrayList();
  }

  /**
   * Adds a new object to map, before addition it checks if the given object collides with any of
   * the existing objects
   *
   * @param msh the object to be added
   * @param pos the positoin of addition of that object
   * @return true if addition is successful, or false otherwise
   */
  public boolean add(MovableShape msh, Position pos) {
    if(pos.getX() < 0 || pos.getY() < 0)
        return false;
    for (int i = 0; i < objects.size(); ++i) {
      if (pEngine.isCollided(msh, objects.get(i))) return false;
    }
    objects.add(msh);

    return true;
  }

  /**
   * Removes an object at a particulare position
   *
   * @param pos the position of the object to be removed
   * @return true if removal was successful, or false otherwise
   */
  public boolean remove(Position pos) {
    for (int i = 0; i < objects.size(); ++i) {
      if (pos.equals(objects.get(i).getPosition())) {
        objects.remove(i);
        return true;
      }
    }

    return false;
  }

  /**
   * moves an object from a place to another
   *
   * @param from original place of object
   * @param to destination of object
   * @return true if moving is successful, or false otherwise
   */
  public boolean move(Position from, Position to) {

    MovableShape msh = null;
    // check if object exists in movables
    for (int i = 0; i < objects.size(); ++i) {
      if (from.equals(objects.get(i).getPosition())) {
        msh = objects.remove(i);
        break;
      }
    }
    // if object does not exist terminate
    if (msh == null) return false;

    // check if destination does not collide with any other object
    MovableShape newMsh = msh;
    newMsh.setPosition(to);
    for (int i = 0; i < objects.size(); ++i) {
      if (pEngine.isCollided(newMsh, objects.get(i))) {
        objects.add(msh);
        return false;
      }
    }

    objects.add(newMsh);

    return true;
  }

  public List<MovableShape> getMovables() {
    return objects;
  }

  public MapBuildData getData() {
    MapBuildData data = new MapBuildData(objects);
    return data;
  }
}
