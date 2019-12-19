package domain.model.alien;

import domain.model.SpecificType;
import domain.model.movement.LinearMovement;
import domain.model.movement.MovementBehavior;
import domain.model.shape.MovableShape;
import utils.Position;
import utils.Velocity;

import java.io.Serializable;

import static utils.Constants.Protecting_Alien_Speed;

public class ProtectingAlienBehavior implements AlienBehavior, Serializable {

    private Alien self;
    private MovementBehavior movBeh;

    public ProtectingAlienBehavior(Position pos) {
        movBeh = new LinearMovement(pos, new Velocity(Protecting_Alien_Speed, 0));
    }

    public void setSelf(Alien self) {
        this.self = self;
        self.initializeMovementBehavior(movBeh);
    }

    @Override
    public void behave() {
        //Protecting alien doesn't really do anything
    }


    @Override
    public void collide(MovableShape obj) {
        if (obj.getCenter().getY() < movBeh.getCurrentPosition().getY()) {
            self.setDestroyed(true);
        }
    }

    @Override
    public void setMovementBehavior(MovementBehavior movBeh) {
        this.movBeh = movBeh;
    }

    @Override
    public SpecificType getSpecificType() {
        return SpecificType.ProtectingAlien;
    }

    @Override
    public void move() {
        movBeh.getNextPosition();
    }


    @Override
    public String toString() {
        return "Protecting Alien at " + movBeh.getCurrentPosition() + " with velocity " + movBeh.getCurrentVelocity();
    }
}
