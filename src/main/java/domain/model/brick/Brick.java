package domain.model.brick;

import domain.model.movement.MovementBehavior;
import domain.model.shape.MovableShape;
import utils.Position;

public abstract class Brick extends MovableShape {
    public Shape getShape() {
        return Shape.Rectangle;
    }

    public Brick(MovementBehavior movBeh, int length, int width) {
        super(movBeh, length, width);
    }

    public final MovableShape.Type getType() {
        return MovableShape.Type.Brick;
    }

    @Override
    // Bricks can't rotate so angle is always 0
    public final double getAngle() {
        return 0;
    }

    @Override
    // SetAngle meaningless
    public final void setAngle(double angle) {
    }

    @Override
    // increment angle meaningless
    public final void incrementAngle(double dif) {
    }
}
