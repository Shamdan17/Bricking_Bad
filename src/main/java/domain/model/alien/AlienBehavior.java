package domain.model.alien;

import domain.model.SpecificType;
import domain.model.shape.MovableShape;

public interface AlienBehavior {

    void behave();

    void collide(MovableShape movableShape);

    SpecificType getSpecificType();

    String toString();
}
