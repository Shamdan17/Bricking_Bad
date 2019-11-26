package domain.model;

import domain.model.brick.HalfMetalBrick;
import domain.model.brick.SimpleBrick;
import domain.model.shape.MovableShape;
import domain.model.shape.MovableShape.Type;
import domain.storage.BinaryStorage;
import domain.storage.StorageManager;
import org.apache.log4j.Logger;
import utils.Constants;
import utils.Position;
import utils.Velocity;
import utils.physics.PhysicsEngine;

import java.time.Instant;
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

        this.sm = (StorageManager) new BinaryStorage(username);
        movables = new ArrayList<>();
        //    movables.add(new Ball(new Position(50,50),10));
        //    paddle = new Paddle(new Position(500, 700));
        //    paddle.setAngle(0);
        //    movables.add(paddle);
        defaultMovables();
    }

    private void defaultMovables() {
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 6; j++) {
                Position curpos = new Position(80 * i + 20, 40 * j + 10);
                if(i%2==1)movables.add(new SimpleBrick(curpos, 60, 20));
                else movables.add(new HalfMetalBrick(curpos, 60, 20));
            }
            //private Map map;
            //brick list ?
        }

    paddle = new Paddle(new Position(300, 700));
    movables.add(paddle);
    bll = new Ball(new Position(310, 300), Constants.BALL_DIAMETER/2);
    bll.setVelocity(new Velocity(Constants.BALL_INITIAL_VX, Constants.BALL_INITIAL_VY));
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
      if(movableShape.getType() == Type.Ball){
        if(movableShape.getPosition().getY()>Constants.maxY){
          movableShape.setPosition(paddle.getPosition().incrementY(-100).incrementX(paddle.getLength()/2));
          movableShape.setVelocity(Constants.defaultRespawnVelocity);
        }
      }
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
        for (MovableShape m : movables) {
            MovableShape c;
            if (m.getType() == Type.Ball) {
                c = new Ball(m.getPosition(), m.getWidth() / 2);
                c.setVelocity(bll.getVelocity());
            } else if (m.getType() == Type.Brick) {
                c = new SimpleBrick(m.getPosition(), 60, 20);
            } else if (m.getType() == Type.Paddle) {
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
