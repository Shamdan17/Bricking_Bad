package domain.game.collisionrules;

import domain.model.shape.MovableShape;
import utils.Pair;
import utils.physics.PhysicsEngine;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

// GameCollisionRule contains all rules, uses composite pattern
public class GameCollisionRule implements CollisionRule {
    private PhysicsEngine ps = PhysicsEngine.getInstance();
    private HashMap<Pair<UUID>, Integer> cache = new HashMap<>(20000);

    private ArrayList<CollisionRule> rules = new ArrayList<>();
    private CollisionRule defaultRule = new DefaultCollisionRule();
    private CollisionRule noCollisionRule = new NoCollisionRule();


    @Override
    //Always applies since it works for all object types
    public boolean ruleApplies(MovableShape obj1, MovableShape obj2) {
        return true;
    }

    @Override
    public void collide(MovableShape obj1, MovableShape obj2) {
        // if those are objects that we do not need to calculate collisions for,
        // then return
        if (noCollisionRule.ruleApplies(obj1, obj2)) {
            return;
        }

        // decrement the collision flag between those two objects
        cache.put(new Pair<>(obj1.getID(), obj2.getID()), cache.getOrDefault(new Pair<>(obj1.getID(), obj2.getID()), 1) - 1);

        // if those two objects are not colliding then ignore
        if (!ps.isCollided(obj1, obj2)) {
            return;
        }


        // if those two objects are currently under a collision, then ignore this
        // one
        if (cache.getOrDefault(new Pair<>(obj1.getID(), obj2.getID()), 0) > 0) {
            return;
        }

        // turn on the collision flag between those two objects
        cache.put(new Pair<>(obj1.getID(), obj2.getID()), 10);

        // find the appropriate collision rule and apply it
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
        return true;
    }
}
