package domain.model;

import java.time.Instant;
import domain.StorageManager;
import domain.BinaryStorage;
import domain.model.brick.Brick;
import domain.model.brick.SimpleBrick;
import domain.model.shape.MovableShape;
import domain.model.shape.MovableShape.Type;
import org.apache.log4j.Logger;
import utils.Position;
import utils.Velocity;
import utils.physics.PhysicsEngine;

import java.util.ArrayList;
import java.util.List;


//TODO move all movable objects - ones has hasNextPosition() == true
// check all movable objects whether they are collided or not
// remove all collided objects

// Board contains all movables, paddle and ball
public class Board {

  final static Logger logger = Logger.getLogger(Board.class);
  PhysicsEngine physicsEngine = PhysicsEngine.getInstance();
  private ArrayList<MovableShape> movables;
  private StorageManager sm;
  private Paddle paddle;
  private Ball bll;

  long unixTimestamp;

  private int bricksLeft; //TODO get this from list

  public Board(String username) throws IllegalArgumentException {
    if (username == null) {
      throw new IllegalArgumentException();
    }

    this.sm = new BinaryStorage(username);
    movables = new ArrayList<>();
    //    movables.add(new Ball(new Position(50,50),10));
    //    paddle = new Paddle(new Position(500, 700));
    //    paddle.setAngle(0);
    //    movables.add(paddle);
    defaultMovables();
  }

  private void defaultMovables(){
    for(int i=0;i<10;i++){
      for(int j=0;j<6;j++){
        Position curpos = new Position(80*i+20, 40*j+10);
        movables.add(new SimpleBrick(curpos, 60, 20));
      }
      //private Map map;
      //brick list ?
    }

    paddle = new Paddle(new Position(300, 700));
    movables.add(paddle);
    bll = new Ball(new Position(310, 300), 12);
    bll.setVelocity(new Velocity(0,8));
    movables.add(bll);
  }

  public void animate() {
    // advance all movables one step and check collisions and remove collided ones
    moveAllMovables();
    checkCollisions();
    removeDestroyedMovables();
    //TODO need to check whether ball is dropped or not then check remaining lives
  }

  private void moveAllMovables() {
    // move all objects once
    for(MovableShape movableShape : movables) {
      movableShape.move();
    }
  }

  private void checkCollisions() {
    // check all movables pair-wise whether they are collided or not
    for (int i = 0; i < movables.size(); i++){
      for (int j = i + 1; j < movables.size(); j++){
        CollisionRuleEngine.collide(movables.get(i), movables.get(j));
      }
    }
  }

  private void removeDestroyedMovables() {
    movables.removeIf(movableShape -> {
      if (movableShape.isDestroyed())
        logger.debug(movableShape + " is destroyed.");
      return movableShape.isDestroyed();
    });
    logger.debug("# of remaining movables: " +  movables.size());
  }

  public void addMovable(MovableShape mshape){
    movables.add(mshape);
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
  public void save() {
    unixTimestamp = Instant.now().getEpochSecond();
    ArrayList<MovableShape> copy = new ArrayList<>();
    for(MovableShape m : movables) {
      MovableShape c;
      if (m.getType() == Type.Ball) {
        c = new Ball(m.getPosition(), m.getWidth()/2);
        c.setVelocity(bll.getVelocity());
      } else if(m.getType() == Type.Brick) {
        c = new SimpleBrick(m.getPosition(), 60, 20);
      } else if(m.getType() == Type.Paddle) {
        c = new Paddle(paddle.getPosition());
        c.setAngle(paddle.getAngle());
      } else {
        System.out.println("Does not know type of object");
        return;
      }

      copy.add(c);
    }

    sm.put(unixTimestamp, copy);
  }

  public void load() {
    if (sm.get(unixTimestamp) == null) {
      return;
    }

    movables = (ArrayList<MovableShape>) sm.get(unixTimestamp);

    for (MovableShape m : movables) {
      if (m.getType() == Type.Paddle) {
        paddle = (Paddle) m;
      }
      if (m.getType() == Type.Ball) {
        bll = (Ball) m;
      }
    }
  }
}
