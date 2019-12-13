package domain.model.alien;

import domain.model.SpecificType;
import domain.model.Type;
import domain.model.brick.Brick;
import domain.model.brick.BrickFactory;
import domain.model.brick.SimpleBrick;
import domain.model.movement.MovementBehavior;
import domain.model.shape.MovableShape;
import domain.model.shape.Shape;

public class RepairingAlien extends Alien {
    //TODO change constructor
    public RepairingAlien(MovementBehavior movementBehavior, int length, int width) {
        super(movementBehavior, length, width);
    }

    @Override
    public void behave() {
        //todo implementation
        BrickFactory bf = new BrickFactory();
        Brick br = bf.get(SpecificType.SimpleBrick, getPosition().incrementY(25));
        if(Math.random()<0.05){
            super.addToQueue(br);
        }
    }

    @Override
    public void collide(MovableShape obj) {

    }

    @Override
    public SpecificType getSpecificType() {
        return SpecificType.RepairingAlien;
    }

    @Override
    public String toString() {
        return "Repairing alien with " + getPosition();
    }
}
