package domain.model.brick;

import domain.model.movement.NoMovement;
import domain.model.shape.MovableShape;
import utils.Position;
import utils.Velocity;

public class SimpleBrick extends Brick {

    public SimpleBrick(Position position, int length, int width) {
        super(new NoMovement(position), length, width);
    }

    @Override
    public void move() {
        return;
    }

    @Override
    // Since simple bricks don't move the method is not used
    public void setVelocity(Velocity ps) {
        return;
    }

    public void collide(MovableShape obj) {
        if (obj.getType() == Type.Ball)
            super.destroy();
        //TODO if collide with another brick, change direction in order to move to the other way
    }

    @Override
    public String toString() {
        return "Simple brick with " + super.getPosition();
    }
}
