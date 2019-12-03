package domain.model.shape;

import domain.model.movement.MovementBehavior;

public abstract class Circle extends MovableShape {
    int radius;

    public final Shape getShape() {
        return Shape.Circle;
    }

    public Circle(MovementBehavior movBeh, int radius) {
        super(movBeh, 2 * radius, 2 * radius);
        this.radius = radius;
    }

    public int getRadius() {
        return radius;
    }

    public void setRadius(int radius) {
        this.radius = radius;
        super.setLength(2 * radius);
        super.setWidth(2 * radius);
    }

    @Override
    public void setWidth(int width) {
        setRadius(width / 2);
    }

    @Override
    public void setLength(int width) {
        setRadius(width / 2);
    }

    @Override
    public double getAngle() {
        return 0;
    }

    @Override
    // Does nothing since angles are not well defined for circles
    public void setAngle(double angle) {
    }
}
