package domain.model;

import domain.model.movement.NoMovement;
import domain.model.shape.MovableShape;
import domain.model.shape.Rectangle;
import org.apache.commons.lang3.SerializationUtils;
import utils.Position;
import utils.Velocity;
import utils.physics.math.Rotation;
import utils.physics.math.util;

import static utils.Constants.*;

public class Paddle extends Rectangle {

  private boolean isTallerPaddle = false;
  private boolean tiltLeft = false;
  private boolean tiltRight = false;

  public Paddle(Position position) {
    super(new NoMovement(position), util.round(L), PADDLE_WIDTH);
    super.setAngle(0);
  }

  public Paddle(Position position, double angle){
      super(new NoMovement(position),util.round(L),PADDLE_WIDTH);
      super.setAngle(angle);
  }

  @Override
  public void collide(MovableShape obj) {
    return;
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
    tiltRight = false;
    tiltLeft = false;
  }

  public void moveLeft() {
    setPosition(
        new Position(super.getPosition().getX() - PADDLE_MOVING_SPEED, super.getPosition().getY()));
  }

  public void moveRight() {
    setPosition(
        new Position(super.getPosition().getX() + PADDLE_MOVING_SPEED, super.getPosition().getY()));
  }

  public void rotateRight() {
    tiltRight = true;
    // setAngle(getAngle()+10);
  }

  public void rotateLeft() {
    tiltLeft = true;
    // setAngle(getAngle()+10);
  }

  // Since paddles don't move on collision the method is not used
  public void setVelocity(Velocity ps) {
    return;
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

  public boolean isTallerPaddle() {
    return isTallerPaddle;
  }

  public void setTallerPaddle(boolean tallerPaddle) {
    isTallerPaddle = tallerPaddle;
  }

    /**
     * this method copies info of this current paddle and
     * returns a copy
     * Crucial info to copy:
     * - Position
     * - Velocity
     * - Angle
     * - state (tallerPaddle)
     * @return a copy of the current paddle
     */
    @Override
  public MovableShape copy() {
    Paddle copyPaddle = SerializationUtils.clone(this);
    return copyPaddle;
  }

  @Override
  public String toString() {
    return "Paddle with " + getPosition().toString();
  }
}
