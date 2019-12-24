package domain.model.movement;

import org.apache.log4j.Logger;
import utils.Position;
import utils.Velocity;
import utils.physics.math.Rotation;
import utils.physics.math.util;

import static utils.Constants.DAMPING_FACTOR;

public class NormalizingLinearMovement extends LinearMovement {
    private Velocity initialVelocity;
    private int threshold = 100;
    private int curCount = 0;
    static final Logger logger = Logger.getLogger(NormalizingLinearMovement.class);

    public NormalizingLinearMovement(Position initial, Velocity vel) {
        super(initial, vel);
        initialVelocity = vel.copy();
    }

    public Position getNextPosition() {
        Velocity vel = getCurrentVelocity();
        if (util.getDifference(vel, initialVelocity) > 0.2) {
            double curspeed = vel.getSpeed();
            double initialspeed = initialVelocity.getSpeed();
            double factor = DAMPING_FACTOR * (initialspeed - curspeed) / initialspeed;
            setVelocity(new Velocity((1 + factor) * vel.getX(), (1 + factor) * vel.getY()));
        }
        // handle semi 0 y's
        if (Math.abs(getCurrentVelocity().getY()) < 0.1) {
            logger.error(curCount);
            curCount++;
            if (curCount > threshold) {
                curCount = 0;
                setVelocity(Rotation.rotate(getCurrentVelocity(), 10));
            }
        }
        return super.getNextPosition();
    }
}
