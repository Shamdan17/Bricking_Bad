package domain.game;

import domain.game.collisionrules.CollisionRule;
import domain.game.collisionrules.CollisionRuleFactory;
import domain.mapbuild.MapBuildData;
import domain.model.*;
import domain.model.alien.Alien;
import domain.model.powerup.PowerUp;
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

// Overview: Board contains all movables, paddle and ball
public class Board {

  static final Logger logger = Logger.getLogger(Board.class);
  private List<MovableShape> movables;
  private Paddle paddle;
  private Queue<MovableShape> objectQueue = new LinkedList<>();
  private List<BrickPercentageListener> listeners = new ArrayList<>();
  private PhysicsEngine ps = PhysicsEngine.getInstance();
  private CollisionRule collisionRule = CollisionRuleFactory.getCollisionRule();
  private Inventory inventory;
  private double score;
  private int remainingLives;
  private long gameTime;
  private long maxGameTime;
  private long gameStartTime;
  private boolean isGameOver = false;
  private boolean isWin = false;
    private boolean testMode = false;
  private int totalBricksCount;

  /**
   * OVERVIEW: constructor for Board MODIFIES: paddle, ball, movables EFFECT: creates new instance
   * of Board using a given data instance
   *
   * @param data an object that contains necessary data to load a board
   */
  public Board(GameData data) {
    if (data == null) {
      throw new IllegalArgumentException();
    }
    movables = data.getMovables();
    paddle = getPaddle(movables);
    if (paddle != null) {
      paddle.applyMagnetPowerup(getBall());
    }
    inventory = new Inventory(this, data.getPowerupList());
    bindMovables();
    score = data.getScore();
    remainingLives = data.getRemainingLives();
    gameStartTime = System.currentTimeMillis() - data.getGameTime() * 1000;
    maxGameTime = data.getMaxGameTime();
    totalBricksCount = data.getTotalBricksCount();
    isGameOver = false;
  }

  public Board(MapBuildData data) {
    if (data == null) {
      throw new IllegalArgumentException();
    }
    movables = data.getMovables();
    totalBricksCount = 0;
    for (MovableShape ms : movables) {
      if (ms.getType() == Type.Brick) totalBricksCount++;
    }
    inventory = new Inventory(this);
    paddle = new Paddle(new Position((Constants.GAME_WIDTH / 2) - (Constants.L / 2), 900));
    Ball ball = new Ball(paddle.getCenter().incrementY(-200), Constants.BALL_DIAMETER / 2);
    ball.setVelocity(new Velocity(Constants.BALL_INITIAL_VX, -Constants.BALL_INITIAL_VY));
    paddle.applyMagnetPowerup(ball);
    movables.add(ball);
    movables.add(paddle);
    remainingLives = Constants.MAX_LIVES;
    score = 0;
    gameStartTime = System.currentTimeMillis();
    maxGameTime = Constants.MAX_GAME_TIME;
    bindMovables();
    isGameOver = false;
  }

  /**
   * OVERVIEW: Constructor for Board MODIFIES: ball, paddle, movables EFFECT: creates new instance
   * of Board using Default hardcoded values
   *
   * @throws IllegalArgumentException
   */
  public Board() throws IllegalArgumentException {
    movables = new ArrayList<>();
    inventory = new Inventory(this);
    remainingLives = Constants.MAX_LIVES;
    score = 0;
    gameStartTime = System.currentTimeMillis();
    maxGameTime = Constants.MAX_GAME_TIME;
    bindMovables();
  }

  /**
   * OVERVIEW: moves the game 1 cycle MODIFIES: paddle, ball, movables EFFECT: moves the cycle of
   * the game 1 step by making each object move according to its velocity and then it checks
   * collisions between objects and then removes destroyed objects as a result of previous steps
   */
  public void animate() {
    if (!isGameOver || testMode) {
      moveAllMovables();
      checkCollisions();
      removeDestroyedMovables();
      handleQueue();
      checkNumBalls();
      checkWin();
      checkGameOver();
      updateBrickPercentageListeners();
    }
  }

  private void checkWin() {
    for (MovableShape ms : movables) {
      if (ms.getType() == Type.Brick) return;
    }
    isWin = true;
  }

  private void checkGameOver() {
    gameTime = (System.currentTimeMillis() - gameStartTime) / 1000;
    if (remainingLives <= 0) {
      isGameOver = true;
    }
    if (gameTime >= maxGameTime) {
      isGameOver = true;
    }
  }

  /**
   * OVERVIEW: Bind movables gives a reference of the local queue to all the movables MODIFIES:
   * movables EFFECT: gives an instance of the objectQueue to each movable
   */
  private void bindMovables() {
    for (MovableShape ms : movables) {
      ms.setQueue(objectQueue);
    }
  }

  /** Checks the number of balls on in the board, respawns a ball if none left. */
  private void checkNumBalls() {
    int numBalls = 0;
    for (MovableShape ms : movables) {
      // TODO: get back to this
      if (ms instanceof Ball) {
        numBalls++;
      }
    }
    if (numBalls == 0 && (remainingLives > 0 || testMode)) {
      remainingLives--;
      if (remainingLives != 0 || testMode) {
        movables.add(
            new Ball(
                paddle.getCenter().incrementY(-Constants.BALL_SPAWNING_HEIGHT),
                Constants.BALL_RADIUS));
        paddle.applyMagnetPowerup(getBall());
      }
    }
  }

  /**
   * OVERVIEW: handleQueue handles the objects in the queue, only adding them to the board if they
   * are in a valid position MODIFIES: movables EFFECT: takes objects from the queue, checks if
   * adding them violates any game rule, and if not adds it to list of movables
   */
  private void handleQueue() {
    while (!objectQueue.isEmpty()) {
      MovableShape cur = objectQueue.remove();
      if (cur instanceof BrickPercentageListener) listeners.add((BrickPercentageListener) cur);
      if (cur.getType() == Type.Powerup
          || cur.getType() == Type.Alien
          || cur.getType() == Type.Ball) {
        movables.add(cur);
      } else {
        for (MovableShape ms : movables) {
          if (collisionRule.isCollided(cur, ms)) {
            cur.setDestroyed(true);
          }
        }
        if (!cur.isDestroyed()) movables.add(cur);
      }
    }
  }

  /**
   * OVERVIEW: This function iterates over movables and calls move() function on each MODIFIES:
   * movables EFFECT: calls move function for each movable inside movables
   */
  private void moveAllMovables() {
    // move all objects once
    for (MovableShape movableShape : movables) {
      movableShape.move();
    }
  }

  /**
   * OVERVIEW: This function iterates over brick percentage listeners and calls updateScore()
   * function on each MODIFIES: movables EFFECT: calls move function for each movable inside
   * movables
   */
  private void updateBrickPercentageListeners() {
    // move all objects once
    int curBricksCount = 0;
    for(MovableShape ms : movables){
      if(ms.getType() == Type.Brick)
        curBricksCount++;
    }
    double percentage = (1.0 * curBricksCount / totalBricksCount) ;

    for (BrickPercentageListener listener : listeners) {
      System.out.println(((Alien)listener).getSpecificType());
      listener.updateBrickPercentage(percentage);
    }
  }

  /**
   * OVERVIEW: This function checks pairwise collisions between movable objects inside movables list
   * MODIFIES: movables EFFECT: calls collide function for each movable if it collides with another
   * object
   */
  private void checkCollisions() {
    // check all movables pair-wise whether they are collided or not
    for (int i = 0; i < movables.size(); i++) {
      for (int j = i + 1; j < movables.size(); j++) {
        collisionRule.collide(movables.get(i), movables.get(j));
      }
    }
  }

  /**
   * OVERVIEW: adds a new movable to movables MODIFIES: movables EFFECT: This function add movable
   * shape if it is not colliding with any object already in movables
   */
  public void addMovable(MovableShape mshape) {
    for (MovableShape movableShape : movables) {
      if (ps.isCollided(movableShape, mshape)) return;
    }
    movables.add(mshape);
  }

  /**
   * OVERVIEW: This function iterates over movables and removes destroyed objects MODIFIES: movables
   * EFFECT: if an object return true for isDistroyed(), then remove it from movables
   */
  private void removeDestroyedMovables() {
    for (int i = 0; i < movables.size(); i++) {
      MovableShape ms = movables.get(i);
      if (ms.isDestroyed()) {
        if (ms.getType() == Type.Powerup) {
          ms.setQueue(objectQueue);
          inventory.addPowerup((PowerUp) ms);
        }
        // score logic is handled while removing bricks
        if (ms.getType() == Type.Brick) {
          score += 300 / ((System.currentTimeMillis() - gameStartTime) / 1000 + 1);
        }
        movables.remove(ms);
        i--;
      }
    }
  }

  /** OVERVIEW: This function moves the paddle to the left MODIFIES: paddle */
  public void movePaddleLeft() {
    paddle.moveLeft();
  }

  /** OVERVIEW: This function moves the paddle to the right MODIFIES: paddle */
  public void movePaddleRight() {
    paddle.moveRight();
  }

  /** OVERVIEW: This function rotates the paddle to the right MODIFIES: paddle */
  public void rotatePaddleRight() {
    paddle.rotateRight();
  }

  /** OVERVIEW: This function rotates the paddle to the left MODIFIES: paddle */
  public void rotatePaddleLeft() {
    paddle.rotateLeft();
  }

  public void activateChemicalBall() {
    inventory.activatePowerup(SpecificType.ChemicalBallPowerup);
  }

  /**
   * Shoot laser
   *
   * @return
   */
  public void shootLaser() {
    paddle.shootLaser();
  }

  /**
   * Activate Taller Paddle Power-up
   *
   * @return
   */
  public void activateTallerPaddle() {
    inventory.activatePowerup(SpecificType.TallerPaddlePowerup);
  }

  /**
   * Activate Magnet Power-up
   *
   * @return
   */
  public void activateMagnet() {
    inventory.activatePowerup(SpecificType.MagnetPowerup);
  }

  public void releaseBall() {
    paddle.releaseBall();
  }

  public Paddle getPaddle() {
    return paddle;
  }

  public Ball getBall() {
    for (MovableShape movableShape : movables) {
      if (movableShape instanceof Ball) return (Ball) movableShape;
    }
    return null;
  }

  // TODO: Replace with a better function like getData but with references instead of copies

  /**
   * OVERVIEW: This function returns a reference to the movables
   *
   * @return a list containing all the movables
   */
  public List<MovableShape> getMovables() {
    return movables;
  }

  /**
   * OVERVIEW: This function wraps board data into a GameData type and returns it EFFECT: creates a
   * copy of each movable and wraps them inside a GameData instance and returns it
   *
   * @return a GameData instance containing copies of movables in this board
   */
  public GameData getDataCopy() {
    List<MovableShape> movableList = new ArrayList<>();
    List<PowerUp> powerupCopy = new ArrayList<>();
    for (MovableShape ms : movables) {
      movableList.add(ms.copy());
    }
    for (MovableShape ms : inventory.getPowerupList()) {
      powerupCopy.add((PowerUp) ms.copy());
    }
    return new GameData(
        movableList,
        powerupCopy,
        Constants.COPY_MODE,
        score,
        remainingLives,
        gameTime,
        maxGameTime,
        paddle.getLaserCount(),
        (isGameOver && !testMode),
        (isWin && !testMode),
        totalBricksCount);
  }

  public GameData getData() {
    List<MovableShape> movableList = new ArrayList<>();
    for (MovableShape ms : movables) {
      movableList.add(ms);
    }
    return new GameData(
        movableList,
        inventory.getPowerupList(),
        Constants.REFERENCE_MODE,
        score,
        remainingLives,
        gameTime,
        maxGameTime,
        paddle.getLaserCount(),
        (isGameOver && !testMode),
        (isWin && !testMode),
        totalBricksCount);
  }

  private Paddle getPaddle(List<MovableShape> movables) {
    for (MovableShape ms : movables) {
      if (ms.getSpecificType() == SpecificType.Paddle) {
        return (Paddle) ms;
      }
    }
    return null;
  }

  public boolean repOK() {
    return paddle != null && this.movables != null && this.objectQueue != null;
  }
}
