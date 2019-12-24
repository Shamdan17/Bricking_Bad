package domain.model.brick;

import domain.model.SpecificType;
import domain.model.Type;
import domain.model.movement.MovementBehavior;
import domain.model.movement.NoMovement;
import domain.model.shape.MovableShape;
import org.apache.commons.lang3.SerializationUtils;

public class WrapperBrick extends Brick {
    MovableShape ms;

    public WrapperBrick(MovementBehavior movBeh, int length, int width, MovableShape ms) {
        super(new NoMovement(movBeh.getCurrentPosition()), length, width);
        ms.setPosition(getCenter().incrementX(-ms.getLength() / 2));
        this.ms = ms;
    }


    public void collide(MovableShape obj) {
        if (obj.getType() == Type.Ball && !isDestroyed()) {
            super.addToQueue(ms);
            super.destroy();
        }
    }

    @Override
    public SpecificType getSpecificType() {
        return SpecificType.WrapperBrick;
    }

    @Override
    public String toString() {
        return "Wrapper brick with "; //TODO add position info
    }

    @Override
    public MovableShape copy() {
        Brick copyBrick = SerializationUtils.clone(this);
        return copyBrick;
    }

}
