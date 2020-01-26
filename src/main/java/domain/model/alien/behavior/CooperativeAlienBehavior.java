package domain.model.alien.behavior;

import domain.model.SpecificType;
import domain.model.Type;
import domain.model.misc.AlienBeam;
import domain.model.movement.BoundedLinearBehavior;
import domain.model.shape.MovableShape;
import utils.Constants;
import utils.Position;
import utils.Velocity;

public class CooperativeAlienBehavior extends AbstractBehavior {
    // The reference to the last added beam
    private AlienBeam lastAddedBeam;

    public CooperativeAlienBehavior(Position pos) {
        super(
                new BoundedLinearBehavior(
                        pos,
                        new Velocity(0, Constants.Protecting_Alien_Speed),
                        0,
                        0,
                        Constants.GAME_WIDTH,
                        0,
                        Constants.GAME_HEIGHT * 0.5));
    }

    @Override
    public void behave() {
        if (lastAddedBeam != null) {
            if (lastAddedBeam.getNumDestroyedObjects() != 0) {
                self.setDestroyed(true);
            }
            if (lastAddedBeam.isDestroyed()) {
                lastAddedBeam = null;
            }
        }
        if (Math.random() < 0.0025) {
            AlienBeam bm = new AlienBeam(self.getCenter());
            lastAddedBeam = bm;
            self.addToQueue(bm);
        }
    }

    @Override
    public void collide(MovableShape obj) {
        if (obj.getType() == Type.Ball) {
            self.setDestroyed(true);
        }
    }

    @Override
    public SpecificType getSpecificType() {
        return SpecificType.CooperativeAlien;
    }

    @Override
    public void setPosition(Position pos) {
        movBeh = new BoundedLinearBehavior(
                pos,
                new Velocity(0, Constants.Protecting_Alien_Speed),
                0,
                0,
                Constants.GAME_WIDTH,
                0,
                Constants.GAME_HEIGHT * 0.5);
        self.initializeMovementBehavior(movBeh);
    }

    @Override
    public String toString() {
        return "Cooperative Alien at "
                + movBeh.getCurrentPosition()
                + " with velocity "
                + movBeh.getCurrentVelocity();
    }
}
