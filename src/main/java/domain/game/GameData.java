package domain.game;

import domain.model.Ball;
import domain.model.Paddle;
import domain.model.shape.MovableShape;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class GameData implements Serializable {

  // This class wraps all information that can be saved or sent to UI
  private final List<MovableShape> movables;
  private final Paddle paddle;
  private final Ball ball;

  public GameData(Paddle p, Ball b, List<MovableShape> ms) {
    this.paddle = p;
    this.ball = b;
    this.movables = ms;
  }

  public List<MovableShape> getMovables() {
    List<MovableShape> list = new ArrayList<>();
    for (MovableShape ms : movables) list.add(ms.copy());
    return list;
  }

  public Paddle getPaddle() {
    return (Paddle) paddle.copy();
  }

  public Ball getBall() {
    return (Ball) ball.copy();
  }

  public boolean equals(GameData data){
    if(movables.size() != data.getMovables().size())
        return false;
    for(int i=0 ; i<movables.size() ; ++i)
        if(!movables.get(i).equals(data.getMovables().get(i)))
            return false;
      return paddle.equals(data.getPaddle()) &&
              ball.equals(data.getBall());
  }
}
