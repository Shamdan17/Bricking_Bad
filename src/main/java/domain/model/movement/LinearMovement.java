package domain.model.movement;

import utils.Position;
import utils.Velocity;

public class LinearMovement implements MovementBehavior {

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
    public void setVelocity(Velocity newVel) {
        this.curvel = newVel;
    }

    @Override
    public void setPosition(Position newPos) {
        this.curpos = newPos;
    }

}
