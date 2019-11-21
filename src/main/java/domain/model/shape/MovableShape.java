package domain.model.shape;

import domain.model.Movable;
import utils.Constants;
import utils.Position;
import utils.Velocity;

import java.util.Objects;

// Shapes represent objects such as circles and rectangles
// All shapes have two dimensions for now, length and width. For circles, both of these parameters are the diameter
public abstract class MovableShape implements Movable {
    // Object dimensions
    private int length, width;
    // Parameters
    private Position position;
    private Velocity velocity;
    // A flag that signals whether a shape is destroyed due to a collision and needs to be removed
    private boolean destroyed;

    public abstract void collide(MovableShape obj);

    public abstract Type getType();
    public abstract Shape getShape();


    public enum Type {
        Ball,
        Paddle,
        Brick,
    }

    public enum Shape {
        Circle,
        Rectangle,
    }

    MovableShape(Position position, Velocity velocity, int length, int width){
        this.destroyed = false;
        this.length = length;
        this.width = width;
        this.position = position;
        this.velocity = velocity;
    }

    MovableShape(Position position, int length, int width){
        this.destroyed = false;
        this.length = length;
        this.width = width;
        this.position = position;
        this.velocity = Constants.defaultVelocity;
    }

//    @Deprecated
//    MovableShape(int length, int width){
//        this.destroyed = false;
//        this.length = length;
//        this.width = width;
//        this.position = Constants.defaultPosition;
//        this.velocity = Constants.defaultVelocity;
//    }

    public void destroy(){
        this.destroyed = false;
    }

    public boolean isDestroyed(){
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

    @Override
    public Position getPosition() {
        return position;
    }

    @Override
    public void setPosition(Position position) {
        this.position = position;
    }

    @Override
    public Velocity getVelocity() {
        return velocity;
    }

    @Override
    public void setVelocity(Velocity velocity) {
        this.velocity = velocity;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MovableShape shape = (MovableShape) o;
        return length == shape.length &&
                width == shape.width;
    }

    @Override
    public int hashCode() {
        return Objects.hash(length, width);
    }
}
