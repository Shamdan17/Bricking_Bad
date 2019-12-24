package domain.model.alien.behavior;

import domain.model.SpecificType;
import domain.model.movement.NoMovement;
import domain.model.shape.MovableShape;
import utils.Position;

import java.util.Timer;
import java.util.TimerTask;

import static domain.model.SpecificType.ConfusedDrunkAlien;
import static utils.Constants.CONFUSED_ALIEN_DISAPPEAR_TIME;

public class ConfusedBehavior extends AbstractBehavior {

    public ConfusedBehavior(Position pos) {
        super(new NoMovement(pos));
    }

    public void setPosition(Position pos) {
        movBeh = new NoMovement(pos);
        self.initializeMovementBehavior(movBeh);
    }

    @Override
    public void behave() {
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                self.setDestroyed(true);
            }
        }, CONFUSED_ALIEN_DISAPPEAR_TIME);
    }

    @Override
    public void collide(MovableShape movableShape) {

    }

    @Override
    public SpecificType getSpecificType() {
        return ConfusedDrunkAlien;
    }
}
