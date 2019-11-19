package domain.model.brick;

import domain.model.shape.MovableShape;
import utils.Position;
import utils.Velocity;

public class SimpleBrick extends Brick {
    //Position and velocity
    private Position position;
    private Velocity velocity;

    public SimpleBrick(int length, int width, Position pos) {
        super(length, width);
        this.position = pos;
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
        super.destroy();
    }
}
