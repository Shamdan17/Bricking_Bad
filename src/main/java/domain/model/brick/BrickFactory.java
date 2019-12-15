package domain.model.brick;

import domain.model.SpecificType;
import domain.model.movement.CircularMovement;
import domain.model.movement.LinearMovement;
import domain.model.movement.MovementBehavior;
import domain.model.movement.NoMovement;
import domain.model.shape.MovableShape;
import utils.Constants;
import utils.Position;
import utils.Velocity;

import java.util.Queue;

public class BrickFactory {

    private Queue<MovableShape> movQueue;

    public BrickFactory() {

    }

    public BrickFactory(Queue<MovableShape> movQueue) {
        this.movQueue = movQueue;
    }

    public Brick get(SpecificType type, Position pos) {
        boolean moving = Math.random() < Constants.movingProbability;
        MovementBehavior movBeh = new NoMovement(pos);
        Brick result;
        switch (type) {
            case SimpleBrick:
                if (moving)
                    movBeh = new LinearMovement(pos, new Velocity(Constants.Brick_Velocity, 0));
                result = new SimpleBrick(movBeh, Constants.LENGTH, Constants.WIDTH);
                break;
            case MineBrick:
                if (moving)
                    movBeh = new CircularMovement(pos, 30);
                result = new MineBrick(movBeh);
                break;
            case HalfMetalBrick:
                if (moving)
                    movBeh = new LinearMovement(pos, new Velocity(Constants.Brick_Velocity, 0));
                result = new HalfMetalBrick(movBeh, Constants.LENGTH, Constants.WIDTH);
                break;
            default:
                throw new IllegalArgumentException("not brick type supplied");
        }
        result.setQueue(movQueue);
        return result;
    }

}
