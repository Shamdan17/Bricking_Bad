package domain.game.collisionrules;

import domain.model.Type;
import domain.model.shape.MovableShape;
import org.apache.log4j.Logger;
import utils.Constants;
import utils.Velocity;
import utils.physics.PhysicsEngine;

// Advanced Collisions are collisions in which the new velocity of the objects needs be calculated relatively.
// For example: balls colliding with aliens need to take into consideration the relative velocities of objects
public class AdvancedCollisionRule implements CollisionRule {

    private static PhysicsEngine physics = PhysicsEngine.getInstance();
    private static final Logger logger = Logger.getLogger(AdvancedCollisionRule.class);


    @Override
    public boolean ruleApplies(MovableShape obj1, MovableShape obj2) {
        Type t1 = obj1.getType();
        Type t2 = obj2.getType();
        return (t1 == Type.Ball && t2 == Type.Alien ||
                t2 == Type.Ball && t1 == Type.Alien);
    }

    @Override
    public void collide(MovableShape obj1, MovableShape obj2) {
        if (physics.isCollided(obj1, obj2)) {
            logger.debug("Collision detected between " + obj1 + " and " + obj2);
            // Calculate the new velocities
            Velocity v1 = physics.calculateNewRelativeVelocity(obj1, obj2);
            Velocity v2 = physics.calculateNewRelativeVelocity(obj2, obj1);
            logger.debug("obj1 new velocity: " + v1);
            logger.debug("obj2 new velocity: " + v2);
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
            obj2.setVelocity(v2);
        }
    }

    public boolean isCollided(MovableShape obj1, MovableShape obj2) {
        return physics.isCollided(obj1, obj2);
    }
}
