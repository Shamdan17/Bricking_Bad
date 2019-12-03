package domain.model.brick;

import domain.model.movement.CircularMovement;
import domain.model.movement.NoMovement;
import domain.model.shape.MovableShape;
import utils.Constants;
import utils.Position;
import utils.Velocity;

public class MineBrick extends Brick {

    public MineBrick(Position position) {
        super(new CircularMovement(position, 1.5*Constants.L), 20,20);
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
        super.setMovementBehavior(new NoMovement(super.getPosition()));

        if(obj.getType() == Type.Ball){
            super.setRadius(2*Constants.L);
            super.destroy();
        }
    }

    @Override
    public String toString() {
        return "Mine brick with"; //TODO add position info
    }
}
