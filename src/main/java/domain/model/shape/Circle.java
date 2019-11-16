package domain.model.shape;

import utils.Position;

public class Circle {
    int radius;

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    private Position position;

    public Circle(int radius){
        this.radius = radius;
    }

    public int getRadius() {
        return radius;
    }

    public void setRadius(int radius) {
        this.radius = radius;
    }
}
