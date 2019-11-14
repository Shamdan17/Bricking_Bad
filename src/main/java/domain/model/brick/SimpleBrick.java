package domain.model.brick;

import utils.Position;

import java.util.List;

public class SimpleBrick extends Brick {

    //Length and width of the brick
    private int length;
    private int width;

    public SimpleBrick(int length, int width) {
        this.length = length;
        this.width = width;
    }

    @Override
    public void collide() {

    }

    @Override
    public void move() {
        return;
    }

    @Override
    public List<Position> getPerimeterPoints() {
        return null;
    }
}
