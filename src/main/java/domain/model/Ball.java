package domain.model;

import domain.model.shape.Circle;
import domain.model.shape.MovableShape;
import utils.Position;
import utils.Velocity;

public class Ball extends Circle {

    public Ball(int radius) {
        super(radius);

        // TODO we need to remove constants, also we need to check every place
        // where we initialize variable, we don't want null thingies
        // (for example by design any setter should not accept null, contractors
        // should not accept null, etc..)
        setVelocity(new Velocity(10, 10));
    }

    public void collide(MovableShape obj) {
       // TODO Implement this
       // if (obj.getType() == bottomWall) {
       //     destroy();
       // }
    }

    @Override
    public void move() {
        // Get parameters
        // TODO probability of future null thingy here
        Position oldPos = getPosition();

        // Calculate new position
        // Do we really need this (maybe we can provide oldPos.move(dx, dy)?
        Position newPos = oldPos.incrementX(getVelocity().getX()).incrementY(getVelocity().getY());
        setPosition(newPos);
    }

    @Override
    public final Type getType() {
        return Type.Ball;
    }
}
