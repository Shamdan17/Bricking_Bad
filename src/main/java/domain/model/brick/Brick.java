package domain.model.brick;

import domain.Movable;
import utils.Position;

public abstract class Brick implements Movable {

    private Position position;

    public abstract void collide();

    public abstract void move();

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }
}
