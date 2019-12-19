package domain.model;

import domain.model.movement.LinearMovement;
import domain.model.movement.MovementBehavior;
import domain.model.shape.Circle;
import domain.model.shape.MovableShape;
import org.apache.commons.lang3.SerializationUtils;
import utils.Constants;
import utils.Position;

public class Ball extends Circle {

    public Ball(Position pos, int radius) {
        super(new LinearMovement(pos, Constants.defaultRespawnVelocity), radius);

        // TODO we need to remove constants, also we need to check every place
        // where we initialize variable, we don't want null thingies
        // (for example by design any setter should not accept null, contractors
        // should not accept null, etc..)
    }

    public Ball(MovementBehavior movBeh, int radius) {
        super(movBeh, radius);

        // TODO we need to remove constants, also we need to check every place
        // where we initialize variable, we don't want null thingies
        // (for example by design any setter should not accept null, contractors
        // should not accept null, etc..)
    }

    public void collide(MovableShape obj) {
        // TODO Implement this
        // if (obj.getType() == bottomWall) {
        //     destroy();
        // }
    }

    @Override
    public void move() {
        super.move();
        // Self destruct if out of screen
        if (getPosition().getY() > Constants.maxY) {
            destroy();
        }
    }

    /**
     * This method return a copy of the current ball
     * Crucial info to copy includes:
     * - position
     * - velocity
     *
     * @return a copy of current ball
     */
    @Override
    public MovableShape copy() {
        Ball copyBall = SerializationUtils.clone(this);
        return copyBall;
    }

    @Override
    public final Type getType() {
        return Type.Ball;
    }

    @Override
    public SpecificType getSpecificType() {
        return SpecificType.Ball;
    }

    @Override
    public String toString() {
        return "Ball at " + getPosition().toString() + " with " + getVelocity() ;
    }
}
