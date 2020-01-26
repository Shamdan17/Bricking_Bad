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

    private double angleOffset = 0;
    private double movementOffset = 0;
    private double maxLeftSpeed = -PADDLE_MOVING_SPEED;
    private double maxRightSpeed = PADDLE_MOVING_SPEED;


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
            angleOffset=-PADDLE_TURNING_SPEED;
            setAngle(getAngle()+angleOffset);
        } else if (tiltRight) {
            angleOffset=PADDLE_TURNING_SPEED;
            setAngle(getAngle()+angleOffset);
        } else {
            angleOffset*=0.87;
            if(Math.abs(angleOffset)<0.2*PADDLE_TURNING_SPEED){
                angleOffset=0;
                normalizeAngle(PADDLE_RESTORING_SPEED);
            }else{
                setAngle(getAngle()+angleOffset);
            }
        }
        if (moveLeft) {
            movementOffset = maxLeftSpeed;//Math.max(maxLeftSpeed, movementOffset-0.7*PADDLE_MOVING_SPEED);
        } else if (moveRight) {
            movementOffset = maxRightSpeed;//Math.min(maxRightSpeed, movementOffset+0.7*PADDLE_MOVING_SPEED);
        } else {
            movementOffset *= 0.87;
            if (Math.abs(movementOffset) < 1) movementOffset = 0;
        }
        setPosition(getValidPosition(super.getPosition().incrementX(movementOffset)));
        if (boundBall != null) {
            boundBall.setPosition(calculateBallPosition());
        }
        moveLeft = false;
        moveRight = false;
        tiltRight = false;
        tiltLeft = false;
    }

    private Position getValidPosition(Position pos) {
        double x = pos.getX();
        x = Math.max(x, 0);
        x = Math.min(GAME_WIDTH - L / 2, x);
        return new Position(x, pos.getY());
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
        laser_count += LASER_AMMO_COUNT;
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

    public int getLaserCount() {
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
            boundBall.setMovementBehavior(new NormalizingLinearMovement(boundBall.getPosition(), vel, GAME_WIDTH+BRICK_WIDTH+BALL_DIAMETER));
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
