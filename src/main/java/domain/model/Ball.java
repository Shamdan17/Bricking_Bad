package domain.model;

import domain.model.movement.LinearMovement;
import domain.model.shape.Circle;
import domain.model.shape.MovableShape;
import utils.Constants;
import utils.Position;
import utils.Velocity;

public class Ball extends Circle {

    public Ball(Position pos, int radius) {
        super(new LinearMovement(pos, Constants.defaultRespawnVelocity), radius);

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
        // Get parameters
        // TODO probability of future null thingy here
        Position oldPos = getPosition();

        // Calculate new position
        // Do we really need this (maybe we can provide oldPos.move(dx, dy)?
        Position newPos = oldPos.incrementX(getVelocity().getX()).incrementY(getVelocity().getY());
        ensureBallIsInBounds();
        setPosition(newPos);
    }

    //TODO: Move this out of ball
    public void ensureBallIsInBounds() {
        Velocity oldVelocity = getVelocity();
        if (getPosition().getX() + getLength() > Constants.maxX) {
            oldVelocity = new Velocity(-Math.abs(oldVelocity.getX()), oldVelocity.getY());
        }
        if (getPosition().getX() < 0) {
            oldVelocity = new Velocity(Math.abs(oldVelocity.getX()), oldVelocity.getY());
        }
        if (getPosition().getY() < 0) {
            oldVelocity = new Velocity(oldVelocity.getX(), Math.abs(oldVelocity.getY()));
        }
        setVelocity(oldVelocity);
    }

    /**
     * This method return a copy of the current ball
     * Crucial info to copy includes:
     * - position
     * - velocity
     * @return a copy of current ball
     */
    @Override
    public MovableShape copy(){
        Ball copyBall = new Ball(this.getPosition(),this.getRadius());
        copyBall.setVelocity(this.getVelocity());
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
        return "Ball with " + getPosition().toString();
    }
}
