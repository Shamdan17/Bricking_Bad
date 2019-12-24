package domain.model.alien.behavior;

import java.io.Serializable;

public class ConditionalBehavior implements Serializable {
    private AlienBehavior beh;
    private double lowerBound;
    private double upperBound;

    public ConditionalBehavior(AlienBehavior alienBehavior, double lowerBound, double upperBound) {
        this.beh = alienBehavior;
        this.lowerBound = lowerBound;
        this.upperBound = upperBound;
    }

    public boolean isBehaviorValid(double percentage) {
        return percentage >= lowerBound && percentage <= upperBound;
    }

    public AlienBehavior getBehavior() {
        return beh;
    }
}
