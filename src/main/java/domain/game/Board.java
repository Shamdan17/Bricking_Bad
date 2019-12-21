package domain.game;

import domain.game.collisionrules.CollisionRule;
import domain.game.collisionrules.CollisionRuleFactory;
import domain.model.Ball;
import domain.model.Paddle;
import domain.model.SpecificType;
import domain.model.Type;
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
    private Ball ball;
    private Queue<MovableShape> objectQueue = new LinkedList<>();
    private BrickFactory bf = new BrickFactory(objectQueue);
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
        paddle = data.getPaddle();
        movables = data.getMovables();
        inventory = new Inventory(this);
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
        defaultMovables();
        bindMovables();
    }

    /**
     * OVERVIEW: Adds default data to board
     * MODIFIES: ball, paddle, movables
     * EFFECTS: creates new objects using hardcoded values
     */
    private void defaultMovables() {
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 5; j++) {
                if (i == 9 && j == 4) {
                    movables.add(bf.get(SpecificType.WrapperBrick, new Position(70 * i - 100, 50 * j + 40)));
                } else {
                    //movables.add(bf.get(SpecificType.SimpleBrick, new Position(70 * i - 100, 50*j+40)));
                }
            }
        }
        // TODO: remove constants from here
        ball = new Ball(new Position(310, 300), Constants.BALL_DIAMETER / 2);
        //ball.setMovementBehavior(new LinearMovement(ball.getPosition(), new Velocity(Constants.BALL_INITIAL_VX, Constants.BALL_INITIAL_VY)));
        ball.setVelocity(new Velocity(Constants.BALL_INITIAL_VX, Constants.BALL_INITIAL_VY));
        paddle = new Paddle(new Position(300, 700));
        paddle.setMagnet(true);
        paddle.applyMagnetPowerup(ball);
        //ball = new Ball(new NoMovement(new Position(paddle.getPosition().getX() + util.round(L / 2 - ball.getRadius() / 2), paddle.getPosition().getY() - ball.getRadius() * 2 - 1)), Constants.BALL_DIAMETER / 2);
        //ball.setMovementBehavior(new NoMovement(new Position(paddle.getPosition().getX() + util.round(L / 2 - ball.getRadius() / 2), paddle.getPosition().getY() - ball.getRadius() * 2 - 1)));

        // TODO: ball and paddle are added to movables for now for sake of collision checking
        movables.add(ball);
        movables.add(paddle);

        for (int i = 0; i < 10; i += 4) {
            for (int j = 3; j < 6; j += 10) {
                //Position curpos = new Position(80 * i + 20, 40 * j + 10);
                //if (i % 2 == 1) movables.add(bf.get(SpecificType.SimpleBrick, curpos));
                //else movables.add(bf.get(SpecificType.HalfMetalBrick, curpos));
            }
        }
    }

    /**
     * OVERVIEW: moves the game 1 cycle
     * MODIFIES: paddle, ball, movables
     * EFFECT: moves the cycle of the game 1 step by making each object move according to its
     * velocity and then it checks collisions between objects and then removes destroyed objects as a
     * result of previous steps
     */
    public void animate() {
        // advance all movables one step and check collisions and remove collided ones
        /*if (Math.random() < 0.02) {
            objectQueue.add(bf.get(SpecificType.SimpleBrick, new Position(Math.random() * 600, Math.random() * 600)));
        }
        if (Math.random() < 0.0005) {
            objectQueue.add(bf.get(SpecificType.WrapperBrick, new Position(Math.random() * 600, Math.random() * 600)));
        }*/
//    if(Math.random()<0.01){
//      objectQueue.add(bf.get(SpecificType.HalfMetalBrick, new Position(Math.random()*600, Math.random()*600)));
//    }
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
     * Checks the number of balls on in the board, respawns a ball if non left.
     */
    private void checkNumBalls() {
        int numBalls = 0;
        for (MovableShape ms : movables) {
            if (ms instanceof Ball) {
                numBalls++;
            }
        }
        if (numBalls == 0) {
            movables.add(new Ball(paddle.getCenter().incrementY(-200), Constants.RADIUS));
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
                        return;
                    }
                }
                movables.add(cur);
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
                    inventory.addPowerup((PowerUp) ms);
                }
                movables.remove(ms);
                i--;
            }
        }
//        movables.removeIf(
//                movableShape -> {
//                    if (movableShape.isDestroyed()) logger.debug(movableShape + " is destroyed.");
//                    return movableShape.isDestroyed();
//                });
        // logger.debug("# of remaining movables: " + movables.size());
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
        inventory.removePowerup(SpecificType.TallerPaddlePowerup);

    }
    /**
     * Activate Magnet Power-up
     *
     * @return
     */
    public void activateMagnet() {
        inventory.activatePowerup(SpecificType.MagnetPowerup);
        inventory.removePowerup(SpecificType.MagnetPowerup);
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
        Paddle p = (Paddle) paddle.copy();
        List<MovableShape> movableList = new ArrayList<>();
        for (MovableShape ms : movables) {
            movableList.add(ms.copy());
        }
        return new GameData(p, movableList);
    }

    public boolean repOK() {
        return paddle != null && this.movables != null && this.objectQueue != null;
    }
}
