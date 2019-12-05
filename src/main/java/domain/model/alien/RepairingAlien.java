package domain.model.alien;

import domain.model.SpecificType;
import domain.model.Type;
import domain.model.movement.MovementBehavior;
import domain.model.shape.MovableShape;
import domain.model.shape.Shape;

public class RepairingAlien extends Alien {
    public RepairingAlien(MovementBehavior movBeh, int length, int width) {
        super(movBeh, length, width);
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
