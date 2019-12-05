package domain.model.brick;

import domain.model.SpecificType;
import utils.Constants;
import utils.Position;

public class BrickFactory {


    public static Brick get(SpecificType type, Position pos) {
        switch (type) {
            case SimpleBrick:
                return new SimpleBrick(pos, Constants.LENGTH, Constants.WIDTH);
            case MineBrick:
                return new MineBrick(pos);
            case HalfMetalBrick:
                return new HalfMetalBrick(pos,Constants.LENGTH,Constants.WIDTH);
            default:
               throw new IllegalArgumentException("not brick type supplied");
        }
    }

}
