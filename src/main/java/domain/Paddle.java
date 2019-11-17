package domain;
import com.sun.org.apache.bcel.internal.Const;
import domain.model.shape.Rectangle;
import utils.Constants;
import utils.Position;

public class Paddle extends Rectangle {

    private Position position;
    private int angle;

    boolean isTallerPaddle = false;

    public Paddle(Position position) {
        super(Constants.paddleLength, Constants.paddleWidth);
        this.position = position;
        this.angle = 0;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
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
