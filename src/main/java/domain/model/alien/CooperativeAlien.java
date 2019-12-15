package domain.model.alien;

import domain.model.SpecificType;
import domain.model.Type;
import domain.model.movement.NoMovement;
import domain.model.shape.MovableShape;
import domain.model.shape.Shape;
import utils.Position;

public class CooperativeAlien extends Alien {
    public CooperativeAlien(Position pos, int length, int width) {
        super(new NoMovement(pos), length, width);
    }

    @Override
    public void behave() {
        //todo implementation
    }

    @Override
    public void collide(MovableShape obj) {

    }

    @Override
    public Type getType() {
        return null;
    }

    @Override
    public Shape getShape() {
        return null;
    }

    @Override
    public SpecificType getSpecificType() {
        return null;
    }

    @Override
    public String toString() {
        return null;
    }
}
