package domain.model.brick;

import domain.model.SpecificType;
import domain.model.Type;
import domain.model.movement.MovementBehavior;
import domain.model.shape.MovableShape;
import org.apache.commons.lang3.SerializationUtils;

public class HalfMetalBrick extends Brick {

    // TODO: Implement
    public HalfMetalBrick(MovementBehavior movBeh, int length, int width) {
        super(movBeh, length, width);
    }

    private boolean cracked = false;

    @Override
    public void collide(MovableShape obj) {
        if ((obj.getType() == Type.Ball && (cracked || obj.getCenter().getY() < this.getCenter().getY())) || obj.getSpecificType() == SpecificType.ChemicalBall) {
            this.destroy();
        }
        if (obj.getSpecificType() == SpecificType.FireBall) {
            cracked = true;
        }
    }

    @Override
    public SpecificType getSpecificType() {
        return SpecificType.HalfMetalBrick;
    }

    @Override
    public String toString() {
        return "Half Metal Brick at " + super.getPosition().toString(); //TODO add position.toString here;
    }

    private boolean isCracked() {
        return cracked;
    }

    @Override
    public MovableShape copy() {
        Brick copyBrick = SerializationUtils.clone(this);
        return copyBrick;
    }

}
