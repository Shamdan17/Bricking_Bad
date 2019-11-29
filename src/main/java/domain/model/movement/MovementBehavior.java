package domain.model.movement;

import utils.Position;
import utils.Velocity;

public interface MovementBehavior {
    public Position getNextPosition();
    public Velocity getCurrentVelocity();
    public Position getCurrentPosition();
    public void setVelocity(Velocity newVel);
    public void setPosition(Position newPos);
}
