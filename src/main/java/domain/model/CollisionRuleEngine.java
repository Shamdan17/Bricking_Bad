package domain.model;

import domain.model.shape.MovableShape;
import org.apache.log4j.Logger;
import utils.Velocity;
import utils.physics.PhysicsEngine;

public class CollisionRuleEngine {
    private PhysicsEngine physics = PhysicsEngine.getInstance();

    final static Logger logger = Logger.getLogger(CollisionRuleEngine.class);

    public void collide(MovableShape obj1, MovableShape obj2){
        if (physics.isCollided(obj1,obj2)){
            logger.debug("Collision detected between "+obj1+ " and "+obj2);
            // Calculate the new velocities
            Velocity v1 = physics.calculateNewVelocity(obj1, obj2);
            Velocity v2 = physics.calculateNewVelocity(obj2, obj1);
            logger.debug("obj1 new velocity: "+obj1);
            logger.debug("obj2 new velocity: "+obj2);
            // Set the new velocities
            obj1.setVelocity(v1);
            obj2.setVelocity(v2);
        }
    }
}
