package domain.game;

import domain.game.collisionrules.CollisionRule;
import domain.game.collisionrules.CollisionRuleFactory;
import domain.mapbuild.MapBuildData;
import domain.model.Ball;
import domain.model.Paddle;
import domain.model.SpecificType;
import domain.model.Type;
import domain.model.alien.AlienFactory;
import domain.model.brick.BrickFactory;
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
    private PhysicsEngine ps = PhysicsEngine.getInstance();
    private CollisionRule collisionRule = CollisionRuleFactory.getCollisionRule();
    private Inventory inventory;

    /**
     * OVERVIEW: constructor for Board
     * MODIFIES: paddle, ball, movables
     * EFFECT: creates new instance of Board using a given data instance
     *
     * @param data an object that contains necessary data to load a board
     */
    public Board(GameData data) {
        if (data == null) {
            throw new IllegalArgumentException();
        }
        movables = data.getMovables();
        paddle = getPaddle(movables);
        inventory = new Inventory(this);
        bindMovables();
    }

    public Board(MapBuildData data){
        if(data == null){
            throw new IllegalArgumentException();
        }
        movables = data.getMovables();
        inventory = new Inventory(this);
        paddle = new Paddle(new Position((Constants.FRAME_WIDTH / 2) - (Constants.L / 2), 900));
        Ball ball = new Ball(paddle.getCenter().incrementY(-200), Constants.BALL_DIAMETER / 2);
        ball.setVelocity(new Velocity(Constants.BALL_INITIAL_VX, -Constants.BALL_INITIAL_VY));

        movables.add(ball);
        movables.add(paddle);
        bindMovables();
    }

    /**
     * OVERVIEW: Constructor for Board
     * MODIFIES: ball, paddle, movables
     * EFFECT: creates new instance of Board using Default hardcoded values
     *
     * @throws IllegalArgumentException
     */
    public Board() throws IllegalArgumentException {
        movables = new ArrayList<>();
        inventory = new Inventory(this);
        bindMovables();
    }

    /**
     * OVERVIEW: Adds default data to board
     * MODIFIES: ball, paddle, movables
     * EFFECTS: creates new objects using hardcoded values
     */
    private void defaultMovables() {
        for (int i = 0; i < 10; i++) {
            if (i == 0) movables.add(AlienFactory.get(SpecificType.CooperativeAlien, new Position(40, 50 * i)));
            if (i % 3 == 2)
                movables.add(bf.get(SpecificType.MineBrick, new Position(100 * i - 100, 300)));
            for (int j = 0; j < 5; j++) {
                if (j == 4) {
                    movables.add(bf.get(SpecificType.WrapperBrick, new Position(70 * i - 100, 50 * j + 40)));
                } else {
                    movables.add(bf.get(SpecificType.SimpleBrick, new Position(70 * i - 100, 50 * j + 40)));
                }
            }
        }
        // TODO: remove constants from here
        Ball ball = new Ball(new Position(310, 300), Constants.BALL_DIAMETER / 2);
        ball.setVelocity(new Velocity(Constants.BALL_INITIAL_VX, Constants.BALL_INITIAL_VY));
        paddle = new Paddle(new Position(300, 700));
        paddle.setMagnet(true);
        paddle.applyMagnetPowerup(ball);
        //ball = new Ball(new NoMovement(new Position(paddle.getPosition().getX() + util.round(L / 2 - ball.getRadius() / 2), paddle.getPosition().getY() - ball.getRadius() * 2 - 1)), Constants.BALL_DIAMETER / 2);
        //ball.setMovementBehavior(new NoMovement(new Position(paddle.getPosition().getX() + util.round(L / 2 - ball.getRadius() / 2), paddle.getPosition().getY() - ball.getRadius() * 2 - 1)));

    // TODO: ball and paddle are added to movables for now for sake of collision checking
    movables.add(ball);
    movables.add(paddle);
  }

    /**
     * OVERVIEW: moves the game 1 cycle
     * MODIFIES: paddle, ball, movables
     * EFFECT: moves the cycle of the game 1 step by making each object move according to its
     * velocity and then it checks collisions between objects and then removes destroyed objects as a
     * result of previous steps
     */
    public void animate() {
        moveAllMovables();
        checkCollisions();
        removeDestroyedMovables();
        handleQueue();
        checkNumBalls();
        // TODO need to check whether ball is dropped or not then check remaining lives
    }

    /**
     * OVERVIEW: Bind movables gives a reference of the local queue to all the movables
     * MODIFIES: movables
     * EFFECT: gives an instance of the objectQueue to each movable
     */
    private void bindMovables() {
        for (MovableShape ms : movables) {
            ms.setQueue(objectQueue);
        }
    }

    /**
     * Checks the number of balls on in the board, respawns a ball if none left.
     */
    private void checkNumBalls() {
        int numBalls = 0;
        for (MovableShape ms : movables) {
            // TODO: get back to this
            if (ms instanceof Ball) {
                numBalls++;
            }
        }
        if (numBalls == 0) {
            movables.add(new Ball(paddle.getCenter().incrementY(-200), Constants.BALL_RADIUS));
        }
    }

    /**
     * OVERVIEW: handleQueue handles the objects in the queue, only adding them to the board if they are in a valid position
     * MODIFIES: movables
     * EFFECT: takes objects from the queue, checks if adding them violates any game rule, and if not adds it to list of movables
     */
    private void handleQueue() {
        while (!objectQueue.isEmpty()) {
            MovableShape cur = objectQueue.remove();
            if (cur.getType() == Type.Powerup || cur.getType() == Type.Alien || cur.getType() == Type.Ball) {
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
     * OVERVIEW: This function iterates over movables and calls move() function on each
     * MODIFIES: movables
     * EFFECT: calls move function for each movable inside movables
     */
    private void moveAllMovables() {
        // move all objects once
        for (MovableShape movableShape : movables) {
            movableShape.move();
        }
    }

    /**
     * OVERVIEW: This function checks pairwise collisions between movable objects inside movables list
     * MODIFIES: movables
     * EFFECT: calls collide function for each movable if it collides with another object
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
     * OVERVIEW: adds a new movable to movables
     * MODIFIES: movables
     * EFFECT: This function add movable shape if it is not colliding with any object already in movables
     */
    public void addMovable(MovableShape mshape) {
        for (MovableShape movableShape : movables) {
            if (ps.isCollided(movableShape, mshape))
                return;
        }
        movables.add(mshape);
    }

    /**
     * OVERVIEW: This function iterates over movables and removes destroyed objects
     * MODIFIES: movables
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
                movables.remove(ms);
                i--;
            }
        }
    }

    /**
     * OVERVIEW: This function moves the paddle to the left
     * MODIFIES: paddle
     */
    public void movePaddleLeft() {
        paddle.moveLeft();
    }

    /**
     * OVERVIEW: This function moves the paddle to the right
     * MODIFIES: paddle
     */
    public void movePaddleRight() {
        paddle.moveRight();
    }

    /**
     * OVERVIEW: This function rotates the paddle to the right
     * MODIFIES: paddle
     */
    public void rotatePaddleRight() {
        paddle.rotateRight();
    }

    /**
     * OVERVIEW: This function rotates the paddle to the left
     * MODIFIES: paddle
     */
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
            if (movableShape.getType() == Type.Ball)
                return (Ball) movableShape;
        }
        return null;
    }

    //TODO: Replace with a better function like getData but with references instead of copies

    /**
     * OVERVIEW: This function returns a reference to the movables
     *
     * @return a list containing all the movables
     */
    public List<MovableShape> getMovables() {
        return movables;
    }

    /**
     * OVERVIEW: This function wraps board data into a GameData type and returns it
     * EFFECT: creates a copy of each movable and wraps them inside a GameData instance
     * and returns it
     *
     * @return a GameData instance containing copies of movables in this board
     */
    public GameData getData() {
        List<MovableShape> movableList = new ArrayList<>();
        for (MovableShape ms : movables) {
            movableList.add(ms.copy());
        }
        return new GameData(movableList);
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
