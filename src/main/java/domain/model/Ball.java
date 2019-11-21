package domain.model;

import domain.model.shape.Circle;
import domain.model.shape.MovableShape;
import utils.Position;
import utils.Velocity;

public class Ball extends Circle {

    public Ball(Position position, int radius) {
        super(position, radius);
    }

    public void collide(MovableShape obj) {
        //TODO Implement this
//        if (obj.getType() == bottomWall) {
//            destroy();
//        }
    }

    @Override
    public void move() {
        // Get parameters
        Position oldPos = getPosition();
        Velocity velocity = getVelocity();

        // Calculate new position
        Position newPos = oldPos.incrementX(velocity.getX()).incrementY(velocity.getY());
        setPosition(newPos);
    }

    @Override
    public final Type getType() {
        return Type.Ball;
    }
}
