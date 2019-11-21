package domain.model;

import domain.model.brick.SimpleBrick;
import domain.model.shape.MovableShape;
import org.apache.log4j.Logger;
import utils.Position;
import utils.physics.PhysicsEngine;

import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;


//TODO move all movable objects - ones has hasNextPosition() == true
// check all movable objects whether they are collided or not
// remove all collided objects
// 

// Board contains all movables
public class Board implements Runnable {

    final static Logger logger = Logger.getLogger(Board.class);

    PhysicsEngine physicsEngine = PhysicsEngine.getInstance();
    private List<MovableShape> movables;
    private Paddle paddle;
    private Ball ball;
    //private Map map;
    //brick list ?

    private int score = 0;
    private int lives = 3; // TODO parametrize MAX_LIVES
    private int bricksLeft; //TODO get this from list

    private Thread game;
    private AtomicBoolean isPaused = new AtomicBoolean(true);

    // Constructor
    public Board() {
        // assumed res is 960*640 we need to define global variables and parametrize them
        paddle = new Paddle(new Position(430,610)); // 10px above from bottom paddle length = 100
        ball = new Ball(8); //todo diameter of ball is 17px. need to change radius definition

        movables.add(paddle);
        movables.add(ball);

        for (int i = 0; i < 10; i++) {
            movables.add(new SimpleBrick(20,20, new Position(i * 25 + 10, 50))); // need to get from map
        }

        game = new Thread(this);
        game.start();
        logger.debug("Game thread is started");
        isPaused.set(false);
        //TODO game might start with a button press ? on the upper level ?
    }

    @Override
    public void run() {
        // move all objects then check collisions
        moveAllMovables();
        checkCollisions();


        //TODO need to check whether ball is dropped or not then check remaining lives


        try {
            game.sleep(30); //TODO step function wait time ??
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    private void moveAllMovables() {
        for(MovableShape movable : movables) {
            // move all objects once
            movable.move();
        }
    }

    private void checkCollisions() {
        // all possible collisions : ball-paddle, ball-brick, brick-brick
        // all objects should behave according to collision type
        for(MovableShape movable : movables) {
            for(MovableShape movable1 : movables) {
                if (physicsEngine.isCollided(movable, movable1)) {
                    logger.debug(movable.toString() + " is collided with " + movable1.toString());
                    movable.collide(movable1);
                    movable1.collide(movable);
                    //remove destroyed elements
                    if (movable.isDestroyed()){
                        movables.remove(movable);
                        logger.debug(movable.toString() + " is destroyed.");
                    }
                    if (movable1.isDestroyed()){
                        movables.remove(movable1);
                        logger.debug(movable1.toString() + " is destroyed.");
                    }
                }
            }
        }



        //TODO total time complexity can be reduced,
        // ball can collide with only one movable at a step
        // paddle cannot collide with a brick etc.
        // all possible collisions : ball-paddle, ball-brick, brick-brick


            /*
            // check ball-brick and ball-paddle collision and destroy brick, only simple bricks are considered
            // logic should be more complex for different kind of bricks
            if (movable.getType() != MovableShape.Type.Ball) {
                // movable might be brick or paddle here
                if (physicsEngine.isCollided(ball, movable)) {
                    // collide leads destroy flag marked as true for bricks nothing for paddle
                    movable.collide(ball);
                    // ball should update its velocity according to collision
                    ball.collide(movable);
                }
            }
            */

    }






}
