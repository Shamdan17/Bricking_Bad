package domain.model.brick;

import domain.model.SpecificType;
import domain.model.movement.NoMovement;
import domain.model.shape.MovableShape;
import org.apache.commons.lang3.SerializationUtils;
import utils.Position;
import utils.Velocity;

import java.io.Serializable;

public class HalfMetalBrick extends Brick {

    // TODO: Implement
    public HalfMetalBrick(Position position, int length, int width) {
        super(new NoMovement(position), length, width);
    }

    @Override
    public void collide(MovableShape obj) {
        if(obj.getCenter().getY()<this.getCenter().getY()){
            this.destroy();
        }
    }

    @Override
    public void move() {
        //TODO: Implement
        return;
    }

    @Override
    public void setVelocity(Velocity ps) {
        //TODO: Implement
        return;
    }

    @Override
    public SpecificType getSpecificType() {
        return SpecificType.HalfMetalBrick;
    }

    @Override
    public String toString() {
        return "Half Metal Brick at " + super.getPosition().toString(); //TODO add position.toString here;
    }
    @Override
    public MovableShape copy(){
        Brick copyBrick = SerializationUtils.clone(this);
        return copyBrick;
    }

}
