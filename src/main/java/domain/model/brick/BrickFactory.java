package domain.model.brick;

import utils.Constants;
import utils.Position;

public class BrickFactory {

    private static final int LENGTH = Constants.PADDLE_LENGTH / 2;
    private static final int WIDTH = 20;
    private static final int RADIUS = 10;

    public static Brick get(String type, Position pos) {
        switch (type) {
            case Constants.SimpleBrick:
                return new SimpleBrick(pos, LENGTH, WIDTH);
            default:
                return null;
        }
    }

}
