package domain.model.brick;

import domain.model.SpecificType;
import domain.model.Type;
import domain.model.misc.Explosion;
import domain.model.movement.CircularMovement;
import domain.model.movement.MovementBehavior;
import domain.model.movement.NoMovement;
import domain.model.shape.MovableShape;
import domain.model.shape.Shape;
import utils.Constants;
import utils.Position;
import org.apache.commons.lang3.SerializationUtils;
import utils.Velocity;

import java.io.Serializable;

public class MineBrick extends Brick {

    public MineBrick(MovementBehavior movBeh) {
        super(movBeh, 20,20);
    }

    @Override
    public Shape getShape() {
        return Shape.Circle;
    }

    @Override
    public SpecificType getSpecificType() {
        return SpecificType.MineBrick;
    }

    public Type getType(){
        if(isDestroyed()) {
            return Type.Ball;
        } else {
            return Type.Brick;
        }
    }

    @Override
    public void collide(MovableShape obj) {
        if(obj.getType() == Type.Ball){
            //super.setRadius(2*Constants.L);
            super.addToQueue(new Explosion(getPosition()));
            super.destroy();
        }
    }

    @Override
    public String toString() {
        return "Mine brick with"; //TODO add position info
    }

    @Override
    public MovableShape copy(){
        Brick copyBrick = SerializationUtils.clone(this);
        return copyBrick;
    }
}
