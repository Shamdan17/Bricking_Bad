package domain.model;

import domain.model.shape.MovableShape;
import org.apache.log4j.Logger;
import utils.Velocity;
import utils.physics.PhysicsEngine;

public class CollisionRuleEngine {
    private static PhysicsEngine physics = PhysicsEngine.getInstance();
    private static final Logger logger = Logger.getLogger(CollisionRuleEngine.class);

    public static void collide(MovableShape obj1, MovableShape obj2){
        if (physics.isCollided(obj1,obj2)){
            // Calculate the new velocities
            Velocity v1 = physics.calculateNewVelocity(obj1, obj2);
            Velocity v2 = physics.calculateNewVelocity(obj2, obj1);
            // Set the new velocities
            obj1.setVelocity(v1);
            obj2.setVelocity(v2);
            obj1.collide(obj2);
            obj2.collide(obj1);
            logger.debug(obj1 + " is collided with " + obj2);
        }
    }
}
