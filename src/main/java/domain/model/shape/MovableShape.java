package domain.model.shape;

import domain.model.SpecificType;
import domain.model.Type;
import domain.model.movement.MovementBehavior;
import utils.Position;
import utils.Velocity;
import utils.physics.math.util;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.Objects;
import java.util.Queue;

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

    // This queue contains the MovableShapes that this object wants to add to the container it is in
    private Queue<MovableShape> queue = new LinkedList<>();

    public abstract void collide(MovableShape obj);

    public abstract Type getType();

    public abstract Shape getShape();

    public abstract SpecificType getSpecificType();

    public void move(){
        this.movBehavior.getNextPosition();
    }

    public MovableShape(MovementBehavior mb, int length, int width) {
        this.destroyed = false;
        this.length = length;
        this.width = width;
        this.movBehavior = mb;
    }

    public void setQueue(Queue<MovableShape> queue) {
        this.queue = queue;
    }

    protected void addToQueue(MovableShape shape){
        queue.add(shape);
    }

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

    protected MovementBehavior getMovementBehavior(){
        return this.movBehavior;
    }

    public Position stepBack(){
        return movBehavior.stepBack();
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

    // Makes the object of radius newRadius and the same center
    protected void setRadius(double newRadius){
        Position cnt = this.getCenter();
        setPosition(cnt.incrementX(-newRadius).incrementY(-newRadius));
        setWidth(util.round(2*newRadius));
        setLength(util.round(2*newRadius));
    }

    public void incrementAngle(double dif) {
        this.angle += dif;
    }

    public abstract String toString();

    public abstract MovableShape copy();

}
