package domain.model;
import domain.model.shape.MovableShape;
import domain.model.shape.Rectangle;
import utils.Constants;
import utils.Position;
import utils.Velocity;

public class Paddle extends Rectangle {

    private Position position;
    private int angle;

    @Override
    public void collide(MovableShape obj) {
        return;
    }

    public Type getType(){
        return Type.Paddle;
    }

    boolean isTallerPaddle = false;

    public Paddle(Position position) {
        super(Constants.paddleLength, Constants.paddleWidth);
        this.position = position;
        this.angle = 0;
    }

    @Override
    public void move() {
        return;
    }


    // Since paddles don't move on collision the method is not used
    public void setVelocity(Velocity ps) {
        return;
    }


    public int getAngle() {
        return angle;
    }

    public void setAngle(int angle) {
        this.angle = angle;
    }

    public boolean isTallerPaddle() {
        return isTallerPaddle;
    }

    public void setTallerPaddle(boolean tallerPaddle) {
        isTallerPaddle = tallerPaddle;
    }
}
