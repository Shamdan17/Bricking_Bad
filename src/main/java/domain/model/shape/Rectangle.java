package domain.model.shape;

import utils.Position;

public abstract class Rectangle extends MovableShape {
    public final Shape getShape() {
        return Shape.Rectangle;
    }

    public Rectangle(Position position, int length, int width) {
        super(position, length, width);
    }
}
