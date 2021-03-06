package domain.game.collisionrules;

import domain.model.SpecificType;
import domain.model.Type;
import domain.model.alien.Alien;
import domain.model.shape.MovableShape;

// NoCollision does nothing to the objects as if they did not collide in the first place
// For example: balls should not collide with other balls
public class NoCollisionRule implements CollisionRule {

    @Override
    public boolean ruleApplies(MovableShape obj1, MovableShape obj2) {
        return ruleAppliesHelper(obj1, obj2) ||
                ruleAppliesHelper(obj2, obj1);
    }

    private boolean ruleAppliesHelper(MovableShape obj1, MovableShape obj2) {
        // Balls do not collide with one another
        if (obj1.getType() == Type.Ball && obj2.getType() == Type.Ball) {
            return true;
        }

        // Powerup can only collide with the paddle
        if (obj1.getType() == Type.Powerup && obj2.getType() != Type.Paddle) {
            return true;
        }

        // Aliens only collide with the ball
        if (obj1.getType() == Type.Alien && (obj2.getType() != Type.Ball || obj2.getSpecificType() == SpecificType.Laser)) {
            return true;
        }

        // Aliens that are invincible do not collide
        if (obj1.getType() == Type.Alien && ((Alien) obj1).getInvincibilityCounter() > 0) {
            return true;
        }

        return false;
    }

    @Override
    public void collide(MovableShape obj1, MovableShape obj2) {
        // do nothing
    }

    public boolean isCollided(MovableShape obj1, MovableShape obj2) {
        return false;
    }
}
