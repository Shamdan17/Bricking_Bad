package ui.drawables;

import domain.BrickingBad;
import domain.model.shape.MovableShape;
import ui.drawables.bricks.HalfMetalBrick;
import ui.drawables.bricks.MineBrick;
import ui.drawables.bricks.SimpleBrick;
import ui.drawables.bricks.WrapperBrick;
import ui.drawables.powerups.DestructiveLaserGun;
import ui.drawables.powerups.GangOfBalls;

public class DrawableFactory {

    public static Drawable get(MovableShape ms, BrickingBad brickingBad){
        switch(ms.getSpecificType()){
            case Paddle:
                return new Paddle(ms,brickingBad);
            case SimpleBrick:
                return new SimpleBrick(ms,brickingBad);
            case HalfMetalBrick:
                return new HalfMetalBrick(ms,brickingBad);
            case MineBrick:
                return new MineBrick(ms,brickingBad);
            case WrapperBrick:
                return new WrapperBrick(ms,brickingBad);
            case Ball:
                return new Ball(ms);
            case Laser:
                return new Laser(ms);
            case Explosion:
                return new Explosion(ms);
            case DestructiveLaserGun:
                return new DestructiveLaserGun(ms);
            case GangOfBallsPowerup:
                return new GangOfBalls(ms);
            default:
                throw new IllegalArgumentException("Not supported type provided " + ms.getSpecificType());
        }
    }

}
