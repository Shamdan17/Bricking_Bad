package domain.model;

import domain.Movable;
import utils.Position;
import utils.Velocity;
import utils.physics.PhysicsEngine;

public class Ball implements Movable {

    @Override
    public void collide(Movable obj) {
        setVelocity(PhysicsEngine.calculateNewVelocity(this, obj));
    }

    @Override
    public void move() {

    }

    @Override
    public Position getPosition() {
        return null;
    }

    @Override
    public void setPosition(Position ps) {

    }

    @Override
    public Velocity getVelocity() {
        return null;
    }

    @Override
    public void setVelocity(Velocity ps) {

    }

    @Override
    public Shape getShape() {
        return Shape.Circle;
    }
}
