package domain.model.alien;

import utils.Position;

public abstract class Alien {

    private Position position;

    public abstract void behave();

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

}
