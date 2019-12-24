package domain.model.alien.behavior;

import domain.model.SpecificType;
import domain.model.alien.Alien;
import domain.model.movement.MovementBehavior;
import domain.model.shape.MovableShape;

import java.io.Serializable;

public abstract class AbstractBehavior implements AlienBehavior, Serializable {

    // Reference to the alien containing this behavior for specific operations
    protected Alien self;
    protected MovementBehavior movBeh;

    public AbstractBehavior(MovementBehavior movBeh) {
        this.movBeh = movBeh;
    }

    public abstract void behave();

    public abstract void collide(MovableShape movableShape);

    @Override
    public void setMovementBehavior(MovementBehavior movBeh) {
        this.movBeh = movBeh;
    }

    public abstract SpecificType getSpecificType();

    @Override
    public void move() {
        self.getMovBeh().getNextPosition();
    }

    @Override
    public void setSelf(Alien self) {
        this.self = self;
        self.initializeMovementBehavior(movBeh);
    }
}
