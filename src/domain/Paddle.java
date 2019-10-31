package domain;
import utils.Position;

public class Paddle {

    private Position position;
    private int width;

    public Paddle(Position position) {
        this.position = position;
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
}
