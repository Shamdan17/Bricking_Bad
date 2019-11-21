package domain.model;

import utils.Position;

import java.util.List;
import java.util.ArrayList;

// Board contains all movables
public class Board {
  private List<Movable> movables;

  public Board() {
    movables = new ArrayList<>();
    movables.add(new Ball(new Position(50,50),10));
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
