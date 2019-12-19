package domain.game.collisionrules;

import domain.model.shape.MovableShape;

import java.util.ArrayList;

// GameCollisionRule contains all rules, uses composite pattern
public class GameCollisionRule implements CollisionRule {
    ArrayList<CollisionRule> rules = new ArrayList<>();
    CollisionRule defaultRule = new DefaultCollisionRule();

    @Override
    //Always applies since it works for all object types
    public boolean ruleApplies(MovableShape obj1, MovableShape obj2) {
        return true;
    }

    @Override
    public void collide(MovableShape obj1, MovableShape obj2) {
        for (CollisionRule rule : rules) {
            if (rule.ruleApplies(obj1, obj2)) {
                rule.collide(obj1, obj2);
                return;
            }
        }
        defaultRule.collide(obj1, obj2);
    }

    public void addCollisionRule(CollisionRule rule) {
        rules.add(rule);
    }

    public boolean isCollided(MovableShape obj1, MovableShape obj2) {
        for (CollisionRule rule : rules) {
            if (rule.ruleApplies(obj1, obj2)) {
                return rule.isCollided(obj1, obj2);
            }
        }
        return defaultRule.isCollided(obj1, obj2);
    }
}
