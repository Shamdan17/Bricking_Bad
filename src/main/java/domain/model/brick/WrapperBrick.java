package domain.model.brick;

import domain.model.shape.MovableShape;
import utils.Position;
import utils.Velocity;

public class WrapperBrick extends Brick {
    // TODO: Implement
    public WrapperBrick(Position position, int length, int width) {
        super(position, length, width);
    }


    public void collide(MovableShape obj) {
        //TODO: Implement
    }

    @Override
    public void move() {
        //TODO: Implement
    }

    @Override
    public void setVelocity(Velocity ps) {
        //TODO: Implement
    }
}
