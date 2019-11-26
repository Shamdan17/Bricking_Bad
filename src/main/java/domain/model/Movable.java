package domain.model;

import utils.Position;
import utils.Velocity;

public interface Movable {
    public void move();

    public Position getPosition();

    public void setPosition(Position ps);

    public Velocity getVelocity();

    public void setVelocity(Velocity ps);
}
