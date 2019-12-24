package domain.model.brick;

import domain.model.SpecificType;
import domain.model.Type;
import domain.model.misc.Explosion;
import domain.model.movement.MovementBehavior;
import domain.model.shape.MovableShape;
import domain.model.shape.Shape;
import org.apache.commons.lang3.SerializationUtils;
import utils.Constants;

public class MineBrick extends Brick {

    public MineBrick(MovementBehavior movBeh) {
        super(movBeh, Constants.MINE_BRICK_DIAMETER, Constants.MINE_BRICK_DIAMETER);
    }

    @Override
    public Shape getShape() {
        return Shape.Circle;
    }

    @Override
    public SpecificType getSpecificType() {
        return SpecificType.MineBrick;
    }

    public Type getType() {
        return Type.Brick;
    }

    @Override
    public void collide(MovableShape obj) {
        if (obj.getType() == Type.Ball && !isDestroyed()) {
            super.addToQueue(new Explosion(getCenter()));
            super.destroy();
        }
    }

    @Override
    public String toString() {
        return "Mine brick with"; // TODO add position info
    }

    @Override
    public MovableShape copy() {
        Brick copyBrick = SerializationUtils.clone(this);
        return copyBrick;
    }
}
