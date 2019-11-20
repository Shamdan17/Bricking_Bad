package domain.model.alien;

import domain.model.shape.MovableShape;
import utils.Position;

//TODO: Implement
public abstract class Alien{

    private Position position;

    public abstract void behave();

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

//    @Override
//    // Bricks can't rotate so angle is always 0
//    public final double getAngle() {
//        return 0;
//    }
//
//    @Override
//    // SetAngle meaningless
//    public final void setAngle(double angle) {}
}
