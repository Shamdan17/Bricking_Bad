package domain.model.movement;

import utils.Constants;
import utils.Position;
import utils.Velocity;
import utils.physics.math.PolarPoint;
import utils.physics.math.Rotation;

import java.io.Serializable;

public class CircularMovement implements MovementBehavior, Serializable {


    private PolarPoint position;
    private double dTheta;
    private Velocity defaultVelocityVector = new Velocity(0,Constants.Brick_Velocity);


    public CircularMovement(Position pos, double radius){
        position = new PolarPoint(pos, radius, 0);
        // Calculate dTheta
        dTheta = Constants.Brick_Velocity/(2 * Math.PI * radius) ;//* 360;
    }

    public CircularMovement(Position pos, int radius, double initAngle){
        position = new PolarPoint(pos, radius, initAngle);
        // Calculate dTheta
        dTheta = Constants.Brick_Velocity/(2 * Math.PI * radius) ;//* 360;
    }

    @Override
    public Position getNextPosition() {
        position.rotate(-dTheta);
        return position.getPosition();
    }

    @Override
    public Velocity getCurrentVelocity() {
        return Rotation.rotate(defaultVelocityVector, position.getAngle());
    }

    @Override
    public Position getCurrentPosition() {
        return position.getPosition();
    }

    @Override
    public Position stepBack() {
        position.rotate(dTheta/Math.PI);
        return position.getPosition();
    }

    @Override
    // TODO: Rethink whether we need to do something here
    public void setVelocity(Velocity newVel) {
        return;
    }

    @Override
    public void setPosition(Position newPos) {
        double dx = newPos.getX() - getCurrentPosition().getX();
        double dy = newPos.getY() - getCurrentPosition().getY();
        Position newOrigin = this.position.getOrigin().incrementX(dx).incrementY(dy);
        this.position.setOrigin(newOrigin);
    }
}
