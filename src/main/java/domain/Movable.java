package domain;

import utils.Position;
import utils.Velocity;

public interface Movable {

    enum Shape{
        Rectangle,
        Circle
    }

    public void collide(Movable obj);
    public void move();
    public Position getPosition();
    public void setPosition(Position ps);
    public Velocity getVelocity();
    public void setVelocity(Velocity ps);
    public Shape getShape();

}
