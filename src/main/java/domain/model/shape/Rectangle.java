package domain.model.shape;

import utils.Position;

public class Rectangle {
    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    private Position position;
    private int length;
    private int width;

    public Rectangle(int length, int width){
        this.length = length;
        this.width = width;
    }

    public int getLength(){
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }
}
