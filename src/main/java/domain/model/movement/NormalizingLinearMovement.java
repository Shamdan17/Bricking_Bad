package domain.model.movement;

import utils.Position;
import utils.Velocity;
import utils.physics.math.util;

import static utils.Constants.DAMPING_FACTOR;

public class NormalizingLinearMovement extends LinearMovement {
    private Velocity initialVelocity;

    public NormalizingLinearMovement(Position initial, Velocity vel) {
        super(initial, vel);
        initialVelocity = vel.copy();
    }

    public Position getNextPosition() {
        Velocity vel = getCurrentVelocity();
        if (util.getDifference(vel, initialVelocity) > 0.2) {
            double curspeed = vel.getSpeed();
            double initialspeed = initialVelocity.getSpeed();
            double factor = DAMPING_FACTOR * curspeed / initialspeed;
            setVelocity(new Velocity((1 + factor) * vel.getX(), (1 + factor) * vel.getY()));
        }
        return super.getNextPosition();
    }
}
