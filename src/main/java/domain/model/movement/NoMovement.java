package domain.model.movement;

import org.apache.commons.lang3.SerializationUtils;
import utils.Position;
import utils.Velocity;

import java.io.Serializable;

public class NoMovement implements MovementBehavior, Serializable {
    Position curpos;
    Velocity curvel;

    public NoMovement(Position initial) {
        curpos = initial;
        curvel = new Velocity(0, 0);
    }

    @Override
    public Position getNextPosition() {
        return getCurrentPosition();
    }

    @Override
    public Velocity getCurrentVelocity() {
        return curvel;
    }

    @Override
    public Position getCurrentPosition() {
        return curpos;
    }

    @Override
    public Position stepBack() {
        return getCurrentPosition();
    }

    @Override
    public void setVelocity(Velocity newVel) {
        // Do nothing
    }

    @Override
    public void setPosition(Position newPos) {
        this.curpos = newPos;
    }

    @Override
    public void inverse() {
        // Do nothing
    }

    @Override
    public MovementBehavior copy() {
        return SerializationUtils.clone(this);
    }
}
