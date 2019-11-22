package domain.model;
import domain.model.shape.MovableShape;
import domain.model.shape.Rectangle;
import utils.Constants;
import utils.Position;
import utils.Velocity;
import utils.physics.math.Rotation;

public class Paddle extends Rectangle {

    @Override
    public void collide(MovableShape obj) {
        return;
    }

    public Type getType(){
        return Type.Paddle;
    }

    boolean isTallerPaddle = false;

    public Paddle(Position position) {
        super(position, Constants.paddleLength, Constants.paddleWidth);
        super.setAngle(0);
    }

    @Override
    public void move() {
        return;
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
        if (delta > Math.abs(curAngle)){
            newAngle=0;
        }else{
            //If the angle is more than zero, make delta negative so it normalizes the angle back to 0
            if(super.getAngle()>0){
                delta*=-1;
            }
            newAngle = curAngle + delta;
        }
        setAngle(newAngle);
    }

    private double getValidAngle(double angle){
        if(angle<-45){
            angle = -45;
        }else if(angle > 45){
            angle = 45;
        }
        return angle;
    }

    @Override
    //Returns the position of the top left corner of the rectangle, keeping in mind the paddle's angle
    public Position getPosition(){
        if(getAngle()<0){
            //Need to rotate the paddle relative to the top right corner
            Position origin = super.getPosition().incrementX(super.getLength());
            Position currentTopLeft = Rotation.rotate(origin, super.getPosition(), getAngle());
            return currentTopLeft;
        }else{
            return super.getPosition();
        }
    }

    public boolean isTallerPaddle() {
        return isTallerPaddle;
    }

    public void setTallerPaddle(boolean tallerPaddle) {
        isTallerPaddle = tallerPaddle;
    }

    @Override
    public String toString() {
        return "Paddle with " + getPosition().toString();
    }

}
