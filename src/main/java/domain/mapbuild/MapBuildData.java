package domain.mapbuild;

import domain.model.shape.MovableShape;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class MapBuildData implements Serializable {

  private List<MovableShape> movables;

  public MapBuildData(List<MovableShape> movables) {
    this.movables = movables;
  }

  public List<MovableShape> getMovables() {
    List<MovableShape> movablesCopy = new ArrayList<>();
    for (MovableShape ms : movables) movablesCopy.add(ms.copy());
    return movablesCopy;
  }
}
