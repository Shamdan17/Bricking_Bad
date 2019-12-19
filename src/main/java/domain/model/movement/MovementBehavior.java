package domain.model.movement;

import utils.Position;
import utils.Velocity;

// MovementBehavior defines an interface which all movement behaviors of different
// objects implement
public interface MovementBehavior {
    // getNextPosition moves the object to the next location in its path and returns
    // the new position
    public Position getNextPosition();

    // getCurrentVelocity returns the current velocity
    public Velocity getCurrentVelocity();

    // getCurrentVelocity returns the current position of the object without moving it
    public Position getCurrentPosition();

    // stepBack moves the object back to a previous position
    public Position stepBack();

    // setVelocity sets the velocity of the object to the new velocity, if possible
    public void setVelocity(Velocity newVel);

    // setVelocity sets the position of the object to the new position, if possible
    public void setPosition(Position newPos);

    // reverse inverses the path of the object
    public void inverse();

    // copy returns a copy of the movement behavior
    public MovementBehavior copy();
}
