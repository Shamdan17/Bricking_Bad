package domain.model.alien.behavior;

import domain.model.SpecificType;
import domain.model.movement.MovementBehavior;
import domain.model.shape.MovableShape;
import utils.Position;

import java.util.ArrayList;

public class DrunkAlienBehavior extends AbstractBehavior {
    ArrayList<ConditionalBehavior> behaviors = new ArrayList<>();
    private int lastIndex = -1;


    public DrunkAlienBehavior(MovementBehavior beh) {
        super(beh);
    }

    public void addBehavior(AlienBehavior ab, double lower, double upper) {
        behaviors.add(new ConditionalBehavior(ab, lower, upper));
    }

    @Override
    public void behave() {
        AlienBehavior curBeh = getCurrentBehavior();
        int curIndex = indexOf(curBeh);
        curBeh.setSelf(self);
        curBeh.behave();
        Position lastPosition = self.getPosition();
        if (curIndex != lastIndex) {
            getCurrentBehavior().setPosition(lastPosition);
        }
        lastIndex = curIndex;
    }

    @Override
    public void collide(MovableShape movableShape) {
        getCurrentBehavior().collide(movableShape);
    }

    @Override
    public SpecificType getSpecificType() {
        return getCurrentBehavior().getSpecificType();
    }

    public AlienBehavior getCurrentBehavior() {
        double currentPercentage = self.getCurrentPercentage();
        for (ConditionalBehavior ab : behaviors) {
            if (ab.isBehaviorValid(currentPercentage)) {
                return ab.getBehavior();
            }
        }
        return behaviors.get(0).getBehavior();
    }

    private int indexOf(AlienBehavior albeh) {
        for (int i = 0; i < behaviors.size(); i++) {
            if (behaviors.get(i).equals(albeh)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    //TODO: Check this
    public void setPosition(Position pos) {
        for (ConditionalBehavior ab : behaviors) {
            ab.getBehavior().setSelf(self);
            ab.getBehavior().setPosition(pos);
        }
        getCurrentBehavior().setPosition(pos);
    }
}