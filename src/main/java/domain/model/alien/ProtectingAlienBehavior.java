package domain.model.alien;

import domain.model.SpecificType;
import domain.model.Type;
import domain.model.brick.Brick;
import domain.model.brick.BrickFactory;
import domain.model.movement.LinearMovement;
import domain.model.movement.MovementBehavior;
import domain.model.shape.MovableShape;
import domain.model.shape.Shape;
import utils.Constants;
import utils.Position;
import utils.Velocity;

public class ProtectingAlienBehavior extends Alien {

    public ProtectingAlienBehavior(MovementBehavior movBeh, int length, int width) {
        super(movBeh, length, width);
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
    public SpecificType getSpecificType() {
        return SpecificType.ProtectingAlien;
    }


    @Override
    public String toString() {
        return "Protecting Alien at " + super.getPosition() + " with velocity " + super.getVelocity();
    }
}
