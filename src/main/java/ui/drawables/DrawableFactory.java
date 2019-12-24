package ui.drawables;

import domain.BrickingBad;
import domain.model.shape.MovableShape;
import ui.drawables.aliens.CooperativeAlien;
import ui.drawables.aliens.DrunkAlien;
import ui.drawables.aliens.ProtectingAlien;
import ui.drawables.aliens.RepairingAlien;
import ui.drawables.bricks.HalfMetalBrick;
import ui.drawables.bricks.MineBrick;
import ui.drawables.bricks.SimpleBrick;
import ui.drawables.bricks.WrapperBrick;
import ui.drawables.powerups.*;

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
            case ProtectingAlien:
                return new ProtectingAlien(ms);
            case CooperativeAlien:
                return new CooperativeAlien(ms);
            case RepairingAlien:
                return new RepairingAlien(ms);
            case ConfusedDrunkAlien:
                return new DrunkAlien(ms);
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
            case TallerPaddlePowerup:
                return new TallerPaddle(ms);
            case FireBall:
                return new FireBall(ms);
            case ChemicalBallPowerup:
                return new ChemicalBallPowerup(ms);
            case ChemicalBall:
                return new ChemicalBall(ms);
            case MagnetPowerup:
                return new MagnetPowerUp(ms);
            case FireBallPowerup:
                return new FireBallPowerup(ms);
            case FireBallExplosion:
                return new Explosion(ms);
            default:
                //TODO: Handle all drawables
                return new SimpleBrick(ms, brickingBad);
            //throw new IllegalArgumentException("Not supported type provided " + ms.getSpecificType());
        }
    }

}
