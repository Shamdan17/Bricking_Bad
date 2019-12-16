package domain.game.collisionrules;

import domain.model.shape.MovableShape;

public interface CollisionRule {
    // returns true if the rule can be applied on these objects
    public boolean ruleApplies(MovableShape obj1, MovableShape obj2);

    // handles the collisions between these objects
    // @Requires: the rule applies to these objects
    public void collide(MovableShape obj1, MovableShape obj2);

    // returns true if there is a collision between these objects
    // @Requires: the rule applies to these objects
    public boolean isCollided(MovableShape obj1, MovableShape obj2);
}
