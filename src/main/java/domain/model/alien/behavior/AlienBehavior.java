package domain.model.alien.behavior;

import domain.model.SpecificType;
import domain.model.alien.Alien;
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
