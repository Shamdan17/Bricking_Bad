package domain.model.brick;

import domain.model.Movable;
import domain.model.shape.MovableShape;
import utils.Position;
import utils.Velocity;

public class MineBrick extends Brick {

    public MineBrick(Position position, int length, int width) {
        super(position, length, width);
    }

    @Override
    public void collide(MovableShape obj) {
        //TODO: Implement
    }

    public void collide(Movable obj) {
        //TODO: Implement
    }

    @Override
    public void move() {
        //TODO: Implement
    }

    @Override
    public void setVelocity(Velocity ps) {
        //TODO: Implement
    }
}
