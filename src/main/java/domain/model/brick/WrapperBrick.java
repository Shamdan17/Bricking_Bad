package domain.model.brick;

import domain.model.shape.MovableShape;
import utils.Velocity;

public class WrapperBrick extends Brick {
    // TODO: Implement
    public WrapperBrick(int length, int width) {
        super(length, width);
    }


    public void collide(MovableShape obj) {
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
        return "Wrapper brick with "; //TODO add position info
    }


}
