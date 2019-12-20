package domain.model.alien;

import domain.model.SpecificType;
import domain.model.Type;
import domain.model.alien.behavior.AlienBehavior;
import domain.model.movement.MovementBehavior;
import domain.model.shape.MovableShape;
import domain.model.shape.Shape;
import org.apache.commons.lang3.SerializationUtils;
import utils.Velocity;

public class Alien extends MovableShape {

    // An alien has alienBehavior
    private AlienBehavior alienBehavior;

    public Alien(AlienBehavior beh, int length, int width) {
        super(null, length, width);
        this.alienBehavior = beh;
        beh.setSelf(this);
    }

    public void behave() {
        alienBehavior.behave();
    }

    @Override
    public void setVelocity(Velocity v){
        getMovementBehavior().inverse();
    }


    @Override
    // move and behave
    public void move(){
        alienBehavior.move();
        behave();
    }

    @Override
    public void setMovementBehavior(MovementBehavior movBeh) {
        alienBehavior.setMovementBehavior(movBeh);
    }

    public void initializeMovementBehavior(MovementBehavior movBeh) {
        super.setMovementBehavior(movBeh);
    }

    @Override
    public void addToQueue(MovableShape ms) {
        super.addToQueue(ms);
    }


    @Override
    public MovableShape copy(){
        return SerializationUtils.clone(this);
    }

    @Override
    public void collide(MovableShape obj) {
        alienBehavior.collide(obj);
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
        return alienBehavior.getSpecificType();
    }

    @Override
    public String toString() {
        return "Alien of type " + alienBehavior.getSpecificType() + " at position " + getPosition();
    }
}
