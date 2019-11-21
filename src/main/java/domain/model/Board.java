package domain.model;

import java.util.List;
import java.util.ArrayList;

// Board contains all movables
public class Board {
  List<Movable> movables;

  public Board() {
    movables = new ArrayList<Movable>();
    movables.add(new Ball(10));
  }

  public void animate() {
    for (Movable m : movables) {
      m.move();
    }
  }

  public List<Movable> getMovables() {
    // TODO: Do not return the original, return an immutable copy
    return movables;
  }
}
