package domain.model.alien;

import domain.model.movement.MovementBehavior;
import domain.model.shape.MovableShape;
import utils.Position;
import utils.Velocity;

//TODO: Implement
public abstract class Alien extends MovableShape {

    public Alien(MovementBehavior movementBehavior, int length, int width) {
        super(movementBehavior, length, width);
    }


    public abstract void behave();

    @Override
    public void setVelocity(Velocity v){
        super.getMovementBehavior().inverse();
    }

    @Override
    public MovableShape copy(){
        Alien copyAlien = AlienFactory.get(this.getSpecificType(),this.getPosition());
        return copyAlien;
    }
}
