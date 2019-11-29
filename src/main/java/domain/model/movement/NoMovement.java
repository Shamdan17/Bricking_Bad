package domain.model.movement;

import utils.Position;
import utils.Velocity;

public class NoMovement implements MovementBehavior{
    Position curpos;
    Velocity curvel;

    public NoMovement(Position initial){
        curpos = initial;
        curvel = new Velocity(0,0);
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
    public void setVelocity(Velocity newVel) {
        // Do nothing
    }

    @Override
    public void setPosition(Position newPos) {
        this.curpos = newPos;
    }
}
