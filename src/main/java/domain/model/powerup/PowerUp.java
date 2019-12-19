package domain.model.powerup;

import domain.model.Type;
import domain.model.movement.MovementBehavior;
import domain.model.shape.MovableShape;
import domain.model.shape.Shape;
import org.apache.commons.lang3.SerializationUtils;

public abstract class PowerUp extends MovableShape implements Activatable {

    public PowerUp(MovementBehavior mb, int length, int width) {
        super(mb, length, width);
    }

    @Override
    public Type getType() {
        return Type.Powerup;
    }

    @Override
    public Shape getShape() {
        return Shape.Rectangle;
    }

    @Override
    public MovableShape copy() {
        return SerializationUtils.clone(this);
    }
}
