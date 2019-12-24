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
    private int invincibleTicks = 0;

    @Override
    public void collide(MovableShape obj) {
        if ((obj.getType() == Type.Ball && ((cracked && (invincibleTicks--<=0)) || obj.getCenter().getY() < this.getCenter().getY())) || obj.getSpecificType() == SpecificType.ChemicalBall) {
            this.destroy();
        }
        if (obj.getSpecificType() == SpecificType.FireBall) {
            cracked = true;
            invincibleTicks = 10;
        }
    }

    @Override
    public SpecificType getSpecificType() {
        return SpecificType.HalfMetalBrick;
    }

    @Override
    public String toString() {
        return "Half Metal Brick at " + super.getPosition().toString();
    }

    public boolean isCracked() {
        return cracked;
    }

    @Override
    public MovableShape copy() {
        Brick copyBrick = SerializationUtils.clone(this);
        return copyBrick;
    }

}
