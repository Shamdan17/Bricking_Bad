package domain.model.movement;

import org.apache.commons.lang3.SerializationUtils;
import utils.Constants;
import utils.Position;
import utils.Velocity;

import java.io.Serializable;

public class LinearMovement implements MovementBehavior, Serializable {

    Position curpos;
    Velocity curvel;

    public LinearMovement(Position initial, Velocity velocity) {
        curpos = initial;
        curvel = velocity;
    }

    @Override
    public Position getNextPosition() {
        curpos = curpos.incrementX(curvel.getX()).incrementY(curvel.getY());
        ensureObjectInBounds(curpos, curvel);
        return curpos;
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
    // Goes back 1/10 of the velocity
    public Position stepBack() {
        Velocity curvel = getCurrentVelocity();
        double dx = -curvel.getX() / Math.PI;
        double dy = -curvel.getY() / Math.PI;
        curpos = getCurrentPosition().incrementX(dx).incrementY(dy);
        return getCurrentPosition();
    }

    @Override
    public void setVelocity(Velocity newVel) {
        this.curvel = newVel;
    }

    @Override
    public void setPosition(Position newPos) {
        this.curpos = newPos;
    }

    @Override
    public void inverse() {
        this.curvel = new Velocity(-curvel.getX(), -curvel.getY());
    }

    public void ensureObjectInBounds(Position position, Velocity velocity) {
        Velocity oldVelocity = velocity;
        if (position.getX() > Constants.FRAME_WIDTH) {
            oldVelocity = new Velocity(-Math.abs(oldVelocity.getX()), oldVelocity.getY());
        }
        if (position.getX() < 0) {
            oldVelocity = new Velocity(Math.abs(oldVelocity.getX()), oldVelocity.getY());
        }
        if (position.getY() < 0) {
            oldVelocity = new Velocity(oldVelocity.getX(), Math.abs(oldVelocity.getY()));
        }
        setVelocity(oldVelocity);
    }

    @Override
    public MovementBehavior copy() {
        return SerializationUtils.clone(this);
    }
}
