package domain.game.collisionrules;

import domain.model.Type;
import domain.model.shape.MovableShape;

// NoCollision does nothing to the objects as if they did not collide in the first place
// For example: balls should not collide with other balls
public class NoCollisionRule implements CollisionRule{

    @Override
    public boolean ruleApplies(MovableShape obj1, MovableShape obj2) {
        return ruleAppliesHelper(obj1, obj2) ||
                ruleAppliesHelper(obj2,obj1);
    }

    private boolean ruleAppliesHelper(MovableShape obj1, MovableShape obj2){
        // Powerup can only collide with the paddle
        if(obj1.getType() == Type.Powerup && obj2.getType() != Type.Paddle){
            return true;
        }

        // Balls do not collide with one another
        if(obj1.getType() == Type.Ball && obj2.getType() == Type.Ball){
            return true;
        }

        // Aliens only collide with the ball
        if(obj1.getType() == Type.Alien && obj2.getType() != Type.Ball){
            return true;
        }

        return false;
    }

    @Override
    public void collide(MovableShape obj1, MovableShape obj2) {
        // do nothing
    }
}
