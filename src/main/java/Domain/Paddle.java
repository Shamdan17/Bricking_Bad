package Domain;
import utils.Position;

public class Paddle {

    private Position position;
    private int width;
    private int angle;

    boolean isTallerPaddle = false;

    public Paddle(Position position, int angle) {
        this.position = position;
        this.angle = angle;
        //todo width should be %10 of the screen width
        this.width = 60;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
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
