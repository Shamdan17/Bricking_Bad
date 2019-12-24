package domain.model;

import domain.model.misc.Laser;
import domain.model.movement.NoMovement;
import domain.model.movement.NormalizingLinearMovement;
import domain.model.shape.MovableShape;
import domain.model.shape.Rectangle;
import org.apache.commons.lang3.SerializationUtils;
import utils.Position;
import utils.Velocity;
import utils.physics.math.Rotation;
import utils.physics.math.util;

import static utils.Constants.*;

public class Paddle extends Rectangle {

    private boolean tiltLeft = false;
    private boolean tiltRight = false;
    private boolean moveRight = false;
    private boolean moveLeft = false;
    private int laser_count;
    private Ball boundBall;
    private boolean isMagnet = false;


    public Paddle(Position position) {
        super(new NoMovement(position), util.round(L), PADDLE_WIDTH);
        laser_count = 0;
        super.setAngle(0);
    }

    public Paddle(Position position, double angle) {
        super(new NoMovement(position), util.round(L), PADDLE_WIDTH);
        laser_count = 0;
        super.setAngle(angle);
    }

    @Override
    public void collide(MovableShape obj) {
        if (isMagnet && obj instanceof Ball && boundBall == null) {
            applyMagnetPowerup((Ball) obj);
        }
    }

    public Type getType() {
        return Type.Paddle;
    }

    @Override
    public SpecificType getSpecificType() {
        return SpecificType.Paddle;
    }

    @Override
    public void move() {
        if (tiltLeft) {
            setAngle(getAngle() - PADDLE_TURNING_SPEED);
        } else if (tiltRight) {
            setAngle(getAngle() + PADDLE_TURNING_SPEED);
        } else {
            normalizeAngle(PADDLE_RESTORING_SPEED);
        }
        if (moveLeft) {
            setPosition(super.getPosition().incrementX(-PADDLE_MOVING_SPEED));
        } else if (moveRight) {
            setPosition(super.getPosition().incrementX(PADDLE_MOVING_SPEED));
        }
        if (boundBall != null) {
            boundBall.setPosition(calculateBallPosition());
        }
        moveLeft = false;
        moveRight = false;
        tiltRight = false;
        tiltLeft = false;
    }

    public void moveLeft() {
        moveLeft = true;
    }

    public void moveRight() {
        moveRight = true;
    }

    public void rotateRight() {
        tiltRight = true;
        // setAngle(getAngle()+10);
    }

    public void rotateLeft() {
        tiltLeft = true;
        // setAngle(getAngle()+10);
    }

    public void applyLaserPowerup() {
        laser_count += 5;
    }

    public void shootLaser() {
        if (laser_count <= 0) return;
        laser_count--;
        Position lpos = getPosition().incrementY(5);
        Laser leftLaser = new Laser(lpos);
        Position rpos = Rotation.rotate(lpos, lpos.incrementX(getLength() - leftLaser.getLength()), -getAngle());
        Laser rightLaser = new Laser(rpos);
        super.addToQueue(leftLaser);
        super.addToQueue(rightLaser);
    }

    public void applyMagnetPowerup(Ball ball) {
        if (ball != null) {
            this.boundBall = ball;
            ball.setMovementBehavior(new NoMovement(super.getCenter().incrementX(-ball.getRadius()).incrementY(-ball.getWidth() - 0.5 * getWidth() - 1)));
            isMagnet = false;
        }
    }

    public int getLaserCount(){
        return laser_count;
    }

    public boolean isMagnet() {
        return isMagnet;
    }

    public void setMagnet(boolean magnet) {
        isMagnet = magnet;
    }

    public void releaseBall() {
        if (boundBall != null) {
            Velocity vel = new Velocity(BALL_INITIAL_VX, BALL_INITIAL_VY);
            vel = Rotation.rotate(vel, -getAngle());
            boundBall.setMovementBehavior(new NormalizingLinearMovement(boundBall.getPosition(), vel));
            boundBall = null;
        }
    }

    // Since paddles don't move on collision the method is not used
    public void setVelocity(Velocity ps) {
    }

    @Override
    public void setAngle(double angle) {
        super.setAngle(getValidAngle(angle));
    }
    // normalizeAngle is used to return the paddle to its original length
    // it decreases the current angle by the parameter angle until it reaches 0.
    public void normalizeAngle(double angle) {
        double curAngle = super.getAngle();
        double delta = Math.abs(angle);
        double newAngle;
        if (delta > Math.abs(curAngle)) {
            newAngle = 0;
        } else {
            // If the angle is more than zero, make delta negative so it normalizes the angle back to 0
            if (super.getAngle() > 0) {
                delta *= -1;
            }
            newAngle = curAngle + delta;
        }
        setAngle(newAngle);
    }

    private double getValidAngle(double angle) {
        if (angle < -45) {
            angle = -45;
        } else if (angle > 45) {
            angle = 45;
        }
        return angle;
    }

    @Override
    // Returns the position of the top left corner of the rectangle, keeping in mind the paddle's
    // angle
    public Position getPosition() {
        if (getAngle() < 0) {
            // Need to rotate the paddle relative to the top right corner
            Position origin = super.getPosition().incrementX(super.getLength());
            Position currentTopLeft = Rotation.rotate(origin, super.getPosition(), -getAngle());
            return currentTopLeft;
        } else {
            return super.getPosition();
        }
    }

    // Returns the position that a bound ball should be in
    private Position calculateBallPosition() {
        Position ballPos = getPosition().incrementX((getLength()) / 2).incrementY(-BALL_DIAMETER / 2 - 1);
        ballPos = Rotation.rotate(getPosition(), ballPos, -getAngle());
        ballPos = ballPos.incrementX(-BALL_DIAMETER / 2).incrementY(-BALL_DIAMETER / 2);
        return ballPos;
    }

    /**
     * this method copies info of this current paddle and
     * returns a copy
     * Crucial info to copy:
     * - Position
     * - Velocity
     * - Angle
     * - state (tallerPaddle)
     *
     * @return a copy of the current paddle
     */
    @Override
    public MovableShape copy() {
        return SerializationUtils.clone(this);
    }

    @Override
    public String toString() {
        return "Paddle with " + getPosition().toString();
    }

    public boolean repOK() {
        if (getType() != Type.Paddle || getSpecificType() != SpecificType.Paddle)
            return false;
        if (getAngle() < -45 || getAngle() > 45)
            return false;
        if (getLength() < 0 || getWidth() < 0)
            return false;

        return true;
    }
}
