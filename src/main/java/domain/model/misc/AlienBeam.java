package domain.model.misc;

import domain.model.SpecificType;
import domain.model.Type;
import domain.model.movement.NoMovement;
import domain.model.shape.MovableShape;
import domain.model.shape.Shape;
import org.apache.commons.lang3.SerializationUtils;
import utils.Constants;
import utils.Position;

public class AlienBeam extends MovableShape {

    private Position initialSource;
    // How many ticks until self destruction
    private int ticksLeft;
    private int numDestroyedObjects = 0;

    public AlienBeam(Position source) {
        // Make the length of the bream extend from the left of the screen to the right
        super(new NoMovement(new Position(0, source.getY())), Constants.maxY, 5);
        initialSource = source;
        this.ticksLeft = 2;
    }

    @Override
    public void move() {
        this.ticksLeft--;
        if (this.ticksLeft <= 0) {
            super.destroy();
        }
    }

    @Override
    public void collide(MovableShape obj) {
        numDestroyedObjects++;
    }

    public int getNumDestroyedObjects() {
        return numDestroyedObjects;
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
        return "beam at " + getPosition();
    }

    @Override
    public MovableShape copy() {
        return SerializationUtils.clone(this);
    }
}
