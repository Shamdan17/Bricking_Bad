package domain.model;

import domain.model.shape.MovableShape;
import org.apache.log4j.Logger;
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

    private List<MovableShape> movables = new ArrayList<>();
    //private Map map;
    //brick list ?

    private int bricksLeft; //TODO get this from list

    // Constructor
    public Board() {
        // assumed res is 960*640 we need to define global variables and parametrize them
//        Paddle paddle = new Paddle(new Position(430, 610)); // 10px above from bottom paddle length = 100
//        Ball ball = new Ball(new Position(20, 20), 8); //todo diameter of ball is 17px. need to change radius definition
//        ball.setVelocity(new Velocity(0, 1));
//        movables.add(paddle);
//        movables.add(ball);
//        Brick brick = new SimpleBrick(new Position(10,40), 20, 20);
//        movables.add(brick);


        //TODO game might start with a button press ? on the upper level ?
    }

    public void advance() {
        // advance all movables one step and check collisions and remove collided ones
        moveAllMovables();
        checkCollisions();
        removeDestroyedMovables();
        //TODO need to check whether ball is dropped or not then check remaining lives
    }

    private void moveAllMovables() {
        // move all objects once
        for (MovableShape movableShape : movables) {
            movableShape.move();
        }
    }

    private void checkCollisions() {
        // check all movables pair-wise whether they are collided or not
        for (int i = 0; i < movables.size(); i++) {
            for (int j = i + 1; j < movables.size(); j++) {
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
        logger.debug("# of remaining movables: " + movables.size());
    }

    public void addMovable(MovableShape mshape) {
        movables.add(mshape);
    }


    public List<MovableShape> getMovables() {
        // TODO: Do not return the original, return an immutable copy
        return movables;
    }
}
