package domain.model.brick;

import domain.model.movement.NoMovement;
import domain.model.shape.MovableShape;
import utils.Position;
import utils.Velocity;

public class WrapperBrick extends Brick {
    // TODO: Implement
    public WrapperBrick(Position position, int length, int width) {
        super(new NoMovement(position), length, width);
    }


    public void collide(MovableShape obj) {
        //TODO: Implement
    }

    @Override
    public void move() {
        //TODO: Implement
    }

    @Override
    public void setVelocity(Velocity ps) {
        //TODO: Implement
    }

    @Override
    public SpecificType getSpecificType() {
        return SpecificType.WrapperBrick;
    }

    @Override
    public String toString() {
        return "Wrapper brick with "; //TODO add position info
    }


}
