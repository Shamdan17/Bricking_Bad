package domain.model.brick;

import domain.model.SpecificType;
import domain.model.alien.AlienFactory;
import domain.model.movement.CircularMovement;
import domain.model.movement.LinearMovement;
import domain.model.movement.MovementBehavior;
import domain.model.movement.NoMovement;
import domain.model.shape.MovableShape;
import utils.Constants;
import utils.Position;
import utils.Velocity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;

import static utils.Constants.POWERUP_MOVEMENT_SPEED;

public class BrickFactory {

    private Queue<MovableShape> movQueue;
    private ArrayList<MovableShape> WrapperBrickItems;
    private static final MovementBehavior powerupMovBeh = new LinearMovement(new Position(0, 0), new Velocity(0, POWERUP_MOVEMENT_SPEED));

    public BrickFactory() {
        WrapperBrickItems = defaultItems();
        this.movQueue = new LinkedList<>();
    }

    private ArrayList<MovableShape> defaultItems() {
        ArrayList<MovableShape> res = new ArrayList<>();


        res.add(AlienFactory.get(SpecificType.DrunkAlien, null));
        
        Collections.shuffle(res);
        return res;
    }

    public BrickFactory(Queue<MovableShape> movQueue) {
        WrapperBrickItems = defaultItems();
        this.movQueue = movQueue;
    }

    public Brick get(SpecificType type, Position pos) {
        boolean moving = Math.random() < Constants.MOVING_PROBABILITY;
        MovementBehavior movBeh = new NoMovement(pos);
        Brick result;
        switch (type) {
            case SimpleBrick:
                if (moving)
                    movBeh = new LinearMovement(pos, new Velocity(Constants.BRICK_VELOCITY, 0));
                result = new SimpleBrick(movBeh, Constants.BRICK_LENGTH, Constants.BRICK_WIDTH);
                break;
            case MineBrick:
                if (moving)
                    movBeh = new CircularMovement(pos, 30);
                result = new MineBrick(movBeh);
                break;
            case HalfMetalBrick:
                if (moving)
                    movBeh = new LinearMovement(pos, new Velocity(Constants.BRICK_VELOCITY, 0));
                result = new HalfMetalBrick(movBeh, Constants.BRICK_LENGTH, Constants.BRICK_WIDTH);
                break;
            case WrapperBrick:
                MovableShape containedObject = getNextWrapperBrickItem(pos);
                containedObject.setQueue(movQueue);
                result = new WrapperBrick(movBeh, Constants.BRICK_LENGTH, Constants.BRICK_WIDTH, containedObject);
                break;
            default:
                throw new IllegalArgumentException("not brick type supplied");
        }
        result.setQueue(movQueue);
        return result;
    }

    private int numWrapperBricksCreated = 0;

    private MovableShape getNextWrapperBrickItem(Position pos) {
        MovableShape curItem = WrapperBrickItems.get(numWrapperBricksCreated % WrapperBrickItems.size());
        numWrapperBricksCreated++;
        curItem.setPosition(pos);
        return curItem.copy();
    }

}
