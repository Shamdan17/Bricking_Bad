package domain.game;

import domain.game.collisionrules.CollisionRule;
import domain.game.collisionrules.CollisionRuleFactory;
import domain.model.Ball;
import domain.model.Paddle;
import domain.model.SpecificType;
import domain.model.Type;
import domain.model.alien.Alien;
import domain.model.alien.AlienFactory;
import domain.model.alien.ProtectingAlien;
import domain.model.brick.BrickFactory;
import domain.model.brick.HalfMetalBrick;
import domain.model.shape.MovableShape;
import org.apache.log4j.Logger;
import utils.Constants;
import utils.Position;
import utils.Velocity;
import utils.physics.PhysicsEngine;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

// TODO move all movable objects - ones has hasNextPosition() == true

// Board contains all movables, paddle and ball
public class Board {

  static final Logger logger = Logger.getLogger(Board.class);
  private List<MovableShape> movables;
  private Paddle paddle;
  private Ball ball;
  private Queue<MovableShape> objectQueue = new LinkedList<>();
  private BrickFactory bf = new BrickFactory(objectQueue);
  private PhysicsEngine ps = PhysicsEngine.getInstance();
  private CollisionRule collisionRule = CollisionRuleFactory.getCollisionRule();

  /**
   * This constructor is used to create a board with a given data
   *
   * @param data an object that contains necessary data to load a board
   */
  public Board(GameData data) {
    if (data == null) {
      throw new IllegalArgumentException();
    }
    paddle = data.getPaddle();
    ball = data.getBall();
    movables = data.getMovables();
    movables.add(ball);
    movables.add(paddle);
    bindMovables();
  }

  /**
   * This constrcutor is used to start a board with initial configuration
   *
   * @throws IllegalArgumentException
   */
  public Board() throws IllegalArgumentException {
    movables = new ArrayList<>();
    defaultMovables();
    bindMovables();
  }

  /** Adds default data to board */
  private void defaultMovables() {
    for (int i = 0; i < 5; i++) {
        movables.add(AlienFactory.get(SpecificType.RepairingAlien, new Position(40,50*i)));
      if (i % 3 == 2)
        movables.add(bf.get(SpecificType.MineBrick, new Position(100 * i - 100, 300)));
    }

    // TODO: remove constants from here
    ball = new Ball(new Position(310, 300), Constants.BALL_DIAMETER / 2);
    ball.setVelocity(new Velocity(Constants.BALL_INITIAL_VX, Constants.BALL_INITIAL_VY));
    paddle = new Paddle(new Position(300, 700));

    // TODO: ball and paddle are added to movables for now for sake of collision checking
    movables.add(ball);
    movables.add(paddle);
/*
    for (int i = 0; i < 10; i+=4) {
      for (int j = 3; j < 6; j+=10) {
        Position curpos = new Position(80 * i + 20, 40 * j + 10);
        if (i % 2 == 1) movables.add(bf.get(SpecificType.SimpleBrick, curpos));
        else movables.add(bf.get(SpecificType.HalfMetalBrick, curpos));
      }
    }

 */
  }

  /**
   * this function moves the cycle of the game 1 step by making each object move according to its
   * velocity and then it checks collisions between objects and then removes destroyed objects as a
   * result of previous steps
   */
  public void animate() {
    // advance all movables one step and check collisions and remove collided ones
    /*
    if(Math.random()<0.01){
      objectQueue.add(bf.get(SpecificType.SimpleBrick, new Position(Math.random()*600, Math.random()*600)));
    }
    if(Math.random()<0.0005){
      objectQueue.add(bf.get(SpecificType.MineBrick, new Position(Math.random()*600, Math.random()*600)));
    }
    if(Math.random()<0.01){
      objectQueue.add(bf.get(SpecificType.HalfMetalBrick, new Position(Math.random()*600, Math.random()*600)));
    }*/
    moveBall();
    moveAllMovables();
    checkCollisions();
    removeDestroyedMovables();
    handleQueue();
    // TODO need to check whether ball is dropped or not then check remaining lives
  }

  /*
    Bind movables gives a reference of the local queue to all the movables
   */
  private void bindMovables(){
    for(MovableShape ms : movables){
      ms.setQueue(objectQueue);
    }
  }

    /*
    handleQueue handles the objects in the queue, only adding them to the board if they are in a valid position
   */
  private void handleQueue(){
    while(!objectQueue.isEmpty()){
      MovableShape cur = objectQueue.remove();
      logger.debug("Handling: " + cur);
      if(cur.getType() == Type.Powerup || cur.getType() == Type.Alien || cur.getType() == Type.Ball){
        movables.add(cur);
      }else{
        for(MovableShape ms : movables){
          if(ps.isCollided(cur, ms)){
            return;
          }
        }
        movables.add(cur);
      }
    }
  }


  /**
   * This function is responsible for ball movements, and it checks extra logic related to ball like
   * respawning etc..
   */
  private void moveBall() {
    ball.move();
    if (ball.getPosition().getY() > Constants.maxY) {
      ball.setPosition(paddle.getPosition().incrementY(-100).incrementX(paddle.getLength() / 2));
      ball.setVelocity(Constants.defaultRespawnVelocity);
    }
  }

  /** This function iterates over movables and calls move() function on each */
  private void moveAllMovables() {
    // move all objects once
    for (MovableShape movableShape : movables) {
      movableShape.move();
    }
  }

  /** This function checks pairwise collisions between movable objects inside movables list */
  private void checkCollisions() {
    // check all movables pair-wise whether they are collided or not
    for (int i = 0; i < movables.size(); i++) {
      for (int j = i + 1; j < movables.size(); j++) {
        collisionRule.collide(movables.get(i), movables.get(j));
      }
    }
  }

  /** This function add movable shape if it is not colliding with any object already in movables  */
  public void addMovable(MovableShape mshape) {
    for (MovableShape movableShape : movables) {
      if (ps.isCollided(movableShape, mshape))
        return;
    }
    movables.add(mshape);
  }

  /** This function iterates over movables and removes destroyed objects */
  private void removeDestroyedMovables() {
    movables.removeIf(
        movableShape -> {
          if (movableShape.isDestroyed()) logger.debug(movableShape + " is destroyed.");
          return movableShape.isDestroyed();
        });
    // logger.debug("# of remaining movables: " + movables.size());
  }

  /** This function moves the paddle to the left */
  public void movePaddleLeft() {
    paddle.moveLeft();
  }

  /** This function moves the paddle to the right */
  public void movePaddleRight() {
    paddle.moveRight();
  }

  /** This function rotates the paddle to the right */
  public void rotatePaddleRight() {
    paddle.rotateRight();
  }

  /** This function rotates the paddle to the left */
  public void rotatePaddleLeft() {
    paddle.rotateLeft();
  }

  /**
   * This function wraps board data into a GameData type and returns it
   *
   * @return a GameData instance containing copies of movables in this board
   */
  // TODO: check copy behavior
  public GameData getData() {
    Paddle p = (Paddle) paddle.copy();
    Ball b = (Ball) ball.copy();
    List<MovableShape> movableList = new ArrayList<>();
    for (MovableShape ms : movables) {
      if (ms.getType() == Type.Paddle || ms.getType() == Type.Ball) continue;
      movableList.add(ms.copy());
    }
    return new GameData(p, b, movableList);
  }
}
