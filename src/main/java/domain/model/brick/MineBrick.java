package domain.model.brick;

import domain.model.movement.CircularMovement;
import domain.model.movement.NoMovement;
import domain.model.shape.MovableShape;
import utils.Position;
import utils.Velocity;

public class MineBrick extends Brick {

    public MineBrick(Position position) {
        super(new CircularMovement(position, 50), 50,50);
    }

    // Did we crash into another object (to stop movement)
    private boolean crashed = false;

    @Override
    public Shape getShape() {
        return Shape.Circle;
    }

    @Override
    public void collide(MovableShape obj) {
        if(obj.getType() == Type.Ball){
            super.destroy();
        }else{
            crashed = true;
        }
    }

    @Override
    public void move() {
        //TODO: Implement
        if(!crashed) super.move();
    }

    @Override
    public void setVelocity(Velocity ps) {
        //TODO: Implement
    }

    @Override
    public String toString() {
        return "Mine brick with"; //TODO add position info
    }

}
