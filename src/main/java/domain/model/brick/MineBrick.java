package domain.model.brick;

import domain.model.SpecificType;
import domain.model.Type;
import domain.model.movement.CircularMovement;
import domain.model.movement.NoMovement;
import domain.model.shape.MovableShape;
import domain.model.shape.Shape;
import utils.Constants;
import utils.Position;

public class MineBrick extends Brick {

    public MineBrick(Position position) {
        super(new CircularMovement(position, 30), 20,20);
    }

    public MineBrick(Position position, double angle){
        super(new CircularMovement(position,1.5 * Constants.L, angle),20,20);
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

    @Override
    public MovableShape copy(){
        Brick copyBrick = BrickFactory.get(SpecificType.MineBrick, super.getPosition());
        copyBrick.setPosition(super.getPosition());
        return copyBrick;
    }
}
