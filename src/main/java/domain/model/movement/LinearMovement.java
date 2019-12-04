package domain.model.movement;

import utils.Position;
import utils.Velocity;

import java.io.Serializable;

public class LinearMovement implements MovementBehavior, Serializable {

    Position curpos;
    Velocity curvel;

    public LinearMovement(Position initial, Velocity velocity){
        curpos = initial;
        curvel = velocity;
    }

    @Override
    public Position getNextPosition() {
        curpos = curpos.incrementX(curvel.getX()).incrementY(curvel.getY());
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
    // Goes back 1/Pi of the velocity
    public Position stepBack() {
        Velocity curvel = getCurrentVelocity();
        double dx = -curvel.getX()/Math.PI;
        double dy = -curvel.getY()/Math.PI;
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

}
