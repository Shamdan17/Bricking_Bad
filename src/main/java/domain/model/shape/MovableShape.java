package domain.model.shape;

import domain.model.movement.MovementBehavior;
import utils.Position;
import utils.Velocity;

import java.io.Serializable;
import java.util.Objects;

// Shapes represent objects such as circles and rectangles
// All shapes have two dimensions for now, length and width. For circles, both of these parameters are the diameter
public abstract class MovableShape implements Serializable {
    // Object dimensions
    private int length, width;
    private double angle;
    // Parameters
    private MovementBehavior movBehavior;
    // A flag that signals whether a shape is destroyed due to a collision and needs to be removed
    private boolean destroyed;

    public abstract void collide(MovableShape obj);

    public abstract Type getType();

    public abstract Shape getShape();

    public void move(){
        this.movBehavior.getNextPosition();
    }

    public enum Type {
        Ball,
        Paddle,
        Brick,
    }

    public enum Shape {
        Circle,
        Rectangle,
    }

    public MovableShape(MovementBehavior mb, int length, int width) {
        this.destroyed = false;
        this.length = length;
        this.width = width;
        this.movBehavior = mb;
    }

//    @Deprecated
//    MovableShape(int length, int width){
//        this.destroyed = false;
//        this.length = length;
//        this.width = width;
//        this.position = Constants.defaultPosition;
//        this.velocity = Constants.defaultVelocity;
//    }

    protected void destroy() {
        if (!this.destroyed)
            this.destroyed = true;
    }

    public boolean isDestroyed() {
        return destroyed;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public void setMovementBehavior(MovementBehavior movBeh){
        this.movBehavior = movBeh;
    }


    public Position getCenter(){
        return getPosition().incrementX(getLength()/2.0).incrementY(getWidth()/2.0);
    }

    public Position getPosition() {
        return movBehavior.getCurrentPosition();
    }

    public void setPosition(Position position) {
        movBehavior.setPosition(position);
    }

    public Velocity getVelocity() {
        return movBehavior.getCurrentVelocity();
    }

    public void setVelocity(Velocity velocity) {
        movBehavior.setVelocity(velocity);
    }


    public boolean equals(MovableShape shape) {
        if (this == shape) return true;
        if (shape == null) return false;
        return length == shape.length &&
                width == shape.width &&
                this.getPosition().equals(shape.getPosition());
    }

    @Override
    public int hashCode() {
        return Objects.hash(length, width, getPosition());
    }

    public double getAngle() {
        return angle;
    }

    public void setAngle(double angle) {
        this.angle = angle;
    }

    public void incrementAngle(double dif) {
        this.angle += dif;
    }

    public abstract String toString();

}
