package domain.model.alien;

import domain.model.SpecificType;
import domain.model.movement.MovementBehavior;
import domain.model.shape.MovableShape;

public interface AlienBehavior {

    void behave();

    void collide(MovableShape movableShape);

    void setMovementBehavior(MovementBehavior movBeh);

    SpecificType getSpecificType();

    void move();

    void setSelf(Alien self);

    String toString();
}
