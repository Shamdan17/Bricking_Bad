package domain.model.alien;

import domain.model.SpecificType;
import domain.model.Type;
import domain.model.movement.LinearMovement;
import domain.model.shape.MovableShape;
import domain.model.shape.Shape;
import utils.Constants;
import utils.Position;
import utils.Velocity;

public class ProtectingAlien extends Alien {

    public ProtectingAlien(Position position, int length, int width) {
        super(new LinearMovement(position, new Velocity(3 * Constants.L / 150, 0)), length, width);
    }

    @Override
    public void behave() {
        //todo implementation
    }


    @Override
    public void collide(MovableShape obj) {
        if (obj.getCenter().getY() < this.getCenter().getY()) {
            this.destroy();
        }
    }

    @Override
    public Type getType() {
        return Type.Alien;
    }

    @Override
    public Shape getShape() {
        return Shape.Rectangle;
    }

    @Override
    public SpecificType getSpecificType() {
        return SpecificType.ProtectingAlien;
    }

    @Override
    // Since simple bricks don't move the method is not used
    public void setVelocity(Velocity ps) {
        return;
    }

    @Override
    public String toString() {
        return "Protecting Alien at " + super.getPosition() + " with velocity " + super.getVelocity();
    }
}
