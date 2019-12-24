package domain.model;

import domain.model.misc.FireBallExplosion;
import domain.model.movement.MovementBehavior;
import domain.model.movement.NormalizingLinearMovement;
import domain.model.shape.Circle;
import domain.model.shape.MovableShape;
import org.apache.commons.lang3.SerializationUtils;
import utils.Constants;
import utils.Position;
import utils.Velocity;

public class Ball extends Circle {

    private SpecificType curType = SpecificType.Ball;

    private boolean hitPaddle;

    public Ball(Position pos, int radius) {
        super(new NormalizingLinearMovement(pos, Constants.defaultRespawnVelocity), radius);
    }

    public Ball(MovementBehavior movBeh, int radius) {
        super(movBeh, radius);
    }

    public void collide(MovableShape obj) {
        if (obj.getType() == Type.Paddle) {
            hitPaddle = true;
        } else {
            if (curType == SpecificType.FireBall)
                super.addToQueue(new FireBallExplosion(getCenter()));
        }
    }

    @Override
    public void move() {
        super.move();
        // Self destruct if out of screen
        if (getPosition().getY() > Constants.GAME_HEIGHT) {
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
    // if chemcial ball only change velocity if we hit a paddle
    public void setVelocity(Velocity v) {
        if (curType != SpecificType.ChemicalBall || hitPaddle) {
            hitPaddle = false;
            super.setVelocity(v);
        }
    }

    public void setSpecificType(SpecificType st) {
        this.curType = st;
    }

    @Override
    public final Type getType() {
        return Type.Ball;
    }

    @Override
    public SpecificType getSpecificType() {
        return curType;
    }

    @Override
    public String toString() {
        return "Ball at " + getPosition().toString() + " with " + getVelocity();
    }
}
