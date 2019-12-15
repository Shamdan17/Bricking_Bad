package domain.model.brick;

import domain.model.Type;
import domain.model.movement.MovementBehavior;
import domain.model.shape.MovableShape;
import domain.model.shape.Shape;
import utils.Velocity;

import java.io.Serializable;

public abstract class Brick extends MovableShape {
    public Shape getShape() {
        return Shape.Rectangle;
    }

    public Brick(MovementBehavior movBeh, int length, int width) {
        super(movBeh, length, width);
    }

    public Type getType() {
        return Type.Brick;
    }

    /**
     * Crucial information for copy:
     * - position
     * @return a copy of current brick
     */

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

    @Override
    public void setVelocity(Velocity v){
        super.getMovementBehavior().inverse();
    }

    public boolean RepOk() {
        if( getType() != Type.Brick)
            return false;
        if(getLength() <= 0 || getWidth() <= 0 || getAngle() != 0)
            return false;

        return true;
    }
}
