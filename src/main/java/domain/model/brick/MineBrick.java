package domain.model.brick;

import domain.model.Movable;
import domain.model.shape.MovableShape;
import utils.Velocity;

public class MineBrick extends Brick {

    public MineBrick(int length, int width) {
        super(length, width);
    }

    @Override
    public void collide(MovableShape obj) {
        //TODO: Implement
    }

    public void collide(Movable obj) {
        //TODO: Implement
    }

    @Override
    public void move() {
        //TODO: Implement
    }

    @Override
    public Velocity getVelocity() {
        return null;
    }

    @Override
    public void setVelocity(Velocity ps) {
        //TODO: Implement
    }

    @Override
    public String toString() {
        return "Mine brick with"; //TODO add position info
    }

}
