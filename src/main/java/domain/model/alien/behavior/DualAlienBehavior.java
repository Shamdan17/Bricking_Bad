package domain.model.alien.behavior;

import domain.model.SpecificType;
import domain.model.movement.MovementBehavior;
import domain.model.movement.NoMovement;
import domain.model.shape.MovableShape;
import utils.Position;

import static utils.Constants.DUAL_ALIEN_BRICK_THRESHOLD;

public class DualAlienBehavior extends AbstractBehavior {
    private ProtectingAlienBehavior prot;
    private RepairingAlienBehavior rep;
    private state curState;
    private state lastState;
    private Position lastPosition;

    public DualAlienBehavior(Position pos) {
        super(new NoMovement(pos));
        prot = new ProtectingAlienBehavior(pos);
        rep = new RepairingAlienBehavior(pos);
        curState = state.Repairing;
        lastState = state.Repairing;
    }

    private enum state {
        Protecting,
        Repairing,
    }

    @Override
    public void setMovementBehavior(MovementBehavior movBeh) {
        this.movBeh = movBeh;
        prot.setMovementBehavior(movBeh);
        rep.setMovementBehavior(movBeh);
    }

    public void move() {
        getCurrentBehavior().move();
    }

    @Override
    public void behave() {
        AlienBehavior curBeh = getCurrentBehavior();
        curBeh.setSelf(self);
        curBeh.behave();
        if (System.currentTimeMillis() - rep.getFirstBrickTimeStamp() > DUAL_ALIEN_BRICK_THRESHOLD) {
            lastPosition = self.getPosition();
            curState = state.Protecting;
        }
        if (curState != lastState) {
            getCurrentBehavior().setPosition(lastPosition);
        }
        lastState = curState;
    }

    private AlienBehavior getCurrentBehavior() {
        switch (curState) {
            case Protecting:
                return prot;
            case Repairing:
                return rep;
        }
        return null;
    }

    @Override
    public void collide(MovableShape movableShape) {
        getCurrentBehavior().collide(movableShape);
    }

    @Override
    public SpecificType getSpecificType() {
        return getCurrentBehavior().getSpecificType();
    }

    @Override
    //TODO: Check this
    public void setPosition(Position pos) {
        prot.setSelf(self);
        prot.setPosition(pos);
        rep.setSelf(self);
        rep.setPosition(pos);
        getCurrentBehavior().setPosition(pos);
    }
}
