package domain.game.collisionrules;

import domain.model.Type;
import domain.model.shape.MovableShape;
import org.apache.log4j.Logger;
import utils.Constants;
import utils.Velocity;
import utils.physics.PhysicsEngine;

// Lazy Collisions are collisions in which the new velocity of the objects need not be calculated. For example:
// bricks colliding with other bricks have fixed predetermined post collision behavior, and we do not
// need to calculate the new velocity of the collision
public class LazyCollisionRule implements CollisionRule {
    private static PhysicsEngine physics = PhysicsEngine.getInstance();
    private static final Logger logger = Logger.getLogger(LazyCollisionRule.class);

    @Override
    public boolean ruleApplies(MovableShape obj1, MovableShape obj2) {
        Type t1 = obj1.getType();
        Type t2 = obj2.getType();

        if (t1 == Type.Brick && t2 == Type.Brick) {
            return true;
        }
        return false;
    }

    @Override
    public void collide(MovableShape obj1, MovableShape obj2) {
        // Don't Calculate the new velocities
        Velocity v1 = new Velocity(0, 0);
        // Set the new velocities
        obj1.collide(obj2);
        obj2.collide(obj1);

        int cnt = 0;
        while (physics.isCollided(obj1, obj2) && (++cnt < Constants.STEP_BACK_THRESHOLD)) {
            if (obj2.getType() == Type.Paddle && obj1.getVelocity().getY() < 0
                    || obj1.getType() == Type.Paddle && obj2.getVelocity().getY() < 0) break;
            obj1.stepBack();
            obj2.stepBack();
        }
        obj1.setVelocity(v1);
        obj2.setVelocity(v1);
    }

    public boolean isCollided(MovableShape obj1, MovableShape obj2) {
        return physics.isCollided(obj1, obj2);
    }
}
