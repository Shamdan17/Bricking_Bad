package domain.model.misc;

import domain.model.SpecificType;
import domain.model.Type;
import domain.model.movement.NoMovement;
import domain.model.shape.MovableShape;
import domain.model.shape.Shape;
import utils.Position;
import utils.physics.math.util;

public class Laser extends MovableShape {

    Position initialSource;

    public Laser(Position source) {
        // Make the length of the laser extend from the top of the screen to the source
        super(new NoMovement(new Position(source.getX(), 0)), 5, util.round(source.getY()));
        initialSource = source;
    }

    @Override
    public void collide(MovableShape obj) {

    }

    @Override
    public Position getCenter() {
        //Return the source of the laser as the center
        return initialSource;
    }

    @Override
    public Type getType() {
        return Type.Ball;
    }

    @Override
    public Shape getShape() {
        return Shape.Rectangle;
    }

    @Override
    public SpecificType getSpecificType() {
        return SpecificType.Laser;
    }

    @Override
    public String toString() {
        return "laser at " + getPosition();
    }

    @Override
    public MovableShape copy() {
        return new Laser(initialSource);
    }
}
