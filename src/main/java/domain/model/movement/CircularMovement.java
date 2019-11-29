package domain.model.movement;

import utils.Constants;
import utils.Position;
import utils.Velocity;
import utils.physics.math.PolarPoint;
import utils.physics.math.Rotation;

public class CircularMovement implements MovementBehavior{


    private PolarPoint position;
    private double dTheta;
    private Velocity defaultVelocityVector = new Velocity(0,Constants.Brick_Velocity);


    public CircularMovement(Position pos, int radius){
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
        position.rotate(dTheta);
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
    // TODO: Rethink whether we need to do something here
    public void setVelocity(Velocity newVel) {
        return;
    }

    @Override
    // TODO: Rethink whether we need to do something here
    public void setPosition(Position newPos) {
        return;
    }
}
