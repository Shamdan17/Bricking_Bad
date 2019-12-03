package domain.model.shape;

import domain.model.movement.MovementBehavior;
import utils.Position;

public abstract class Rectangle extends MovableShape {
    public final Shape getShape() {
        return Shape.Rectangle;
    }

    public Rectangle(MovementBehavior movBeh, int length, int width) {
        super(movBeh, length, width);
    }
}
