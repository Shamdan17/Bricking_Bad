package domain.game.collisionrules;

public final class CollisionRuleFactory {
    public static CollisionRule getCollisionRule() {
        GameCollisionRule rule = new GameCollisionRule();

        // Add the default rules
        rule.addCollisionRule(new NoCollisionRule());
        rule.addCollisionRule(new LazyCollisionRule());
        rule.addCollisionRule(new AdvancedCollisionRule());

        return rule;
    }
}
