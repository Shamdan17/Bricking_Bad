package domain.game.collisionrules;

import domain.model.Type;
import domain.model.shape.MovableShape;
import org.apache.log4j.Logger;
import utils.Constants;
import utils.Velocity;
import utils.physics.PhysicsEngine;

public class DefaultCollisionRule implements CollisionRule{

    private static PhysicsEngine physics = PhysicsEngine.getInstance();
    private static final Logger logger = Logger.getLogger(DefaultCollisionRule.class);

    @Override
    public boolean ruleApplies(MovableShape obj1, MovableShape obj2) {
        return true;
    }

    @Override
    public void collide(MovableShape obj1, MovableShape obj2) {
        if (physics.isCollided(obj1, obj2)) {
            logger.debug("Collision detected between " + obj1 + " and " + obj2);
            // Calculate the new velocities
            //obj1.setVelocity(new Velocity(5,-200));
            Velocity v1 = physics.calculateNewVelocity(obj1, obj2);
            Velocity v2 = physics.calculateNewVelocity(obj2, obj1);
            logger.debug("obj1 new velocity: " + v1);
            logger.debug("obj2 new velocity: " + v2);
            // Set the new velocities
            obj1.collide(obj2);
            obj2.collide(obj1);

            int cnt = 0;
            while(physics.isCollided(obj1, obj2) && (++cnt < Constants.STEP_BACK_THRESHOLD)){
                if(obj2.getType() == Type.Paddle && obj1.getVelocity().getY()<0
                        ||obj1.getType() == Type.Paddle && obj2.getVelocity().getY()<0) break;
                obj1.stepBack();
                obj2.stepBack();
            }
//            if(obj2.getType()!= MovableShape.Type.Paddle || obj1.getVelocity().getY()>0)obj1.stepBack();
//            if(obj1.getType()!= MovableShape.Type.Paddle || obj2.getVelocity().getY()>0)obj2.stepBack();
            obj1.setVelocity(v1);
            obj2.setVelocity(v2);
        }
    }
}
