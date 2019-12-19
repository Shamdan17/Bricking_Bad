package domain.model.alien;

import domain.model.SpecificType;
import domain.model.movement.MovementBehavior;
import domain.model.movement.NoMovement;
import domain.model.shape.MovableShape;
import utils.Position;

public class CooperativeAlienBehavior extends Alien {
    public CooperativeAlienBehavior(Position pos, int length, int width) {
        super(new NoMovement(pos), length, width);
    }

    public CooperativeAlienBehavior(MovementBehavior movBeh, int length, int width) {
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
    public SpecificType getSpecificType() {
        return SpecificType.CooperativeAlien;
    }

    @Override
    public String toString() {
        return "Repairing Alien at " + super.getPosition() + " with velocity " + super.getVelocity();
    }
}
