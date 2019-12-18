package ui.drawables;

import domain.BrickingBad;
import domain.model.shape.MovableShape;
import ui.drawables.bricks.HalfMetalBrick;
import ui.drawables.bricks.SimpleBrick;
import ui.drawables.bricks.WrapperBrick;

public class DrawableFactory {

    public static Drawable get(MovableShape ms, BrickingBad brickingBad){
        switch(ms.getSpecificType()){
            case SimpleBrick:
                return new SimpleBrick(ms,brickingBad);
            case HalfMetalBrick:
                return new HalfMetalBrick(ms,brickingBad);
            case MineBrick:
                return new HalfMetalBrick(ms,brickingBad);
            case WrapperBrick:
                return new WrapperBrick(ms,brickingBad);
            case Ball:
                return new Ball(ms);
            default:
                throw new IllegalArgumentException("Not supported type provided");
        }
    }

}
