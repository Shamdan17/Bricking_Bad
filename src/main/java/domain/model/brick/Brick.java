package domain.model.brick;

import utils.Position;

public abstract class Brick {

    private Position position;

    public abstract void onHit();

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }
}
