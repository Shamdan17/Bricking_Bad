package domain.model;

import domain.model.shape.MovableShape;
import utils.Velocity;
import utils.physics.PhysicsEngine;

public class CollisionRuleEngine {
    private PhysicsEngine physics = PhysicsEngine.getInstance();

    public void collide(MovableShape obj1, MovableShape obj2){
        if (physics.isCollided(obj1,obj2)){
            // Calculate the new velocities
            Velocity v1 = physics.calculateNewVelocity(obj1, obj2);
            Velocity v2 = physics.calculateNewVelocity(obj2, obj1);
            // Set the new velocities
            obj1.setVelocity(v1);
            obj2.setVelocity(v2);
        }
    }
}
