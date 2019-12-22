package domain.mapbuild;

import domain.model.shape.MovableShape;

import java.io.Serializable;
import java.util.*;
import java.util.Map;

public class MapBuildData implements Serializable {

  private List<MovableShape> movables;
  private Map<UUID, MovableShape> movablesIDMap;

  public MapBuildData(List<MovableShape> movables) {
    this.movables = movables;
    this.movablesIDMap = new HashMap<>();
    for(MovableShape ms : movables)
        movablesIDMap.put(ms.getID(),ms);
  }

  public List<MovableShape> getMovables() {
    List<MovableShape> movablesCopy = new ArrayList<>();
    for (MovableShape ms : movables) movablesCopy.add(ms.copy());
    return movablesCopy;
  }

  public Map<UUID,MovableShape> getMovablesIDMap(){
      return movablesIDMap;
  }
}
