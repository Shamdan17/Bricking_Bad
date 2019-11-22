package domain.model;

import domain.model.brick.SimpleBrick;
import domain.model.shape.MovableShape;
import utils.Position;
import utils.Velocity;

import java.util.List;
import java.util.ArrayList;

// Board contains all movables
public class Board {
  private List<MovableShape> movables;
  private Paddle paddle;

  public Board() {
//    movables = new ArrayList<>();
//    movables.add(new Ball(new Position(50,50),10));
//    paddle = new Paddle(new Position(500, 700));
//    paddle.setAngle(0);
//    movables.add(paddle);
     movables = defaultMovables();
  }

  private List<MovableShape> defaultMovables(){
    List<MovableShape> lst = new ArrayList<>();
    for(int i=0;i<5;i++){
      for(int j=0;j<3;j++){
        Position curpos = new Position(80*i+20, 40*j+10);
        lst.add(new SimpleBrick(curpos, 60, 20));
      }
    }
    paddle = new Paddle(new Position(300, 300));
    lst.add(paddle);
    Ball bll = new Ball(new Position(400, 200), 5);
    bll.setVelocity(new Velocity(-5,-5));
    lst.add(bll);
    return lst;
  }

  public void animate() {
    for (MovableShape m : movables) {
      m.move();
    }
  }

  public void movePaddleLeft() {
    paddle.moveLeft();
  }

  public void movePaddleRight() {
    paddle.moveRight();
  }

  public void rotatePaddleRight() {
    paddle.rotateRight();
  }

  public void rotatePaddleLeft() {
    paddle.rotateLeft();
  }


  public List<MovableShape> getMovables() {
    // TODO: Do not return the original, return an immutable copy
    return movables;
  }
}
