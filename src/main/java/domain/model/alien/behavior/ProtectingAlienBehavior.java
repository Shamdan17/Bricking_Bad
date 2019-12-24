package domain.model.alien.behavior;

import domain.model.SpecificType;
import domain.model.movement.LinearMovement;
import domain.model.shape.MovableShape;
import utils.Position;
import utils.Velocity;

import static utils.Constants.Protecting_Alien_Speed;

public class ProtectingAlienBehavior extends AbstractBehavior {

    public ProtectingAlienBehavior(Position pos) {
        super(new LinearMovement(pos, new Velocity(Protecting_Alien_Speed, 0)));
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
    public SpecificType getSpecificType() {
        return SpecificType.ProtectingAlien;
    }

    @Override
    public void setPosition(Position pos) {
        movBeh = new LinearMovement(pos, new Velocity(Protecting_Alien_Speed, 0));
        self.initializeMovementBehavior(movBeh);
    }


    @Override
    public String toString() {
        return "Protecting Alien at " + movBeh.getCurrentPosition() + " with velocity " + movBeh.getCurrentVelocity();
    }
}
