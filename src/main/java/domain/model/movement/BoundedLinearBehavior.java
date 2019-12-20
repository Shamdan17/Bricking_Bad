package domain.model.movement;

import org.apache.commons.lang3.SerializationUtils;
import utils.Position;
import utils.Velocity;

public class BoundedLinearBehavior extends LinearMovement {

    private double minx;
    private double maxx;
    private double miny;
    private double maxy;

    public BoundedLinearBehavior(Position initial, Velocity velocity, double minx, double maxx, double miny, double maxy) {
        super(initial, velocity);
        this.minx = minx;
        this.maxx = maxx;
        this.miny = miny;
        this.maxy = maxy;
    }

    @Override
    public Position getNextPosition() {
        curpos = curpos.incrementX(curvel.getX()).incrementY(curvel.getY());
        ensureObjectInBounds(curpos, curvel);
        return curpos;
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
        if (position.getX() > maxx) {
            oldVelocity = new Velocity(-Math.abs(oldVelocity.getX()), oldVelocity.getY());
        }
        if (position.getX() < minx) {
            oldVelocity = new Velocity(Math.abs(oldVelocity.getX()), oldVelocity.getY());
        }
        if (position.getY() < miny) {
            oldVelocity = new Velocity(oldVelocity.getX(), Math.abs(oldVelocity.getY()));
        }
        if (position.getY() > maxy) {
            oldVelocity = new Velocity(oldVelocity.getX(), -Math.abs(oldVelocity.getY()));
        }
        setVelocity(oldVelocity);
    }

    @Override
    public MovementBehavior copy() {
        return SerializationUtils.clone(this);
    }
}
