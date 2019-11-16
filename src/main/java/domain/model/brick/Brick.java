package domain.model.brick;

import domain.Movable;
import domain.model.shape.Rectangle;
import utils.Position;

public abstract class Brick extends Rectangle implements Movable {

    public Brick(int length, int width) {
        super(length, width);
    }

    public abstract void move();

    public Position getPosition() {
        return super.getPosition();
    }

    public void setPosition(Position position) {
        super.setPosition(position);
    }
}
