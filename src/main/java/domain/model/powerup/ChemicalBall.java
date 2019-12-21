package domain.model.powerup;

import domain.game.Board;
import domain.model.Ball;
import domain.model.SpecificType;
import domain.model.Type;
import domain.model.movement.MovementBehavior;
import domain.model.shape.MovableShape;
import utils.Position;
import utils.physics.math.util;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import static utils.Constants.POWERUP_CHEMICAL_BALL_TIME;
import static utils.Constants.PowerupSize;

public class ChemicalBall extends PowerUp {
    public ChemicalBall(MovementBehavior mb) {
        super(mb, PowerupSize, PowerupSize);
    }

    @Override
    public boolean activate(SpecificType type, Board board) {
        if (type == SpecificType.ChemicalBallPowerup) {
            List<MovableShape> shapes = board.getMovables();

            MovableShape closestBall = null;
            double closestDistance = 100000;
            Position myPos = getCenter();
            for (MovableShape ms : shapes) {
                if (!(ms instanceof Ball)) continue;
                Position objPos = ms.getCenter();
                if (util.getDistance(objPos, myPos) < closestDistance) {
                    closestDistance = util.getDistance(objPos, myPos);
                    closestBall = ms;
                }
            }

            Ball bl = ((Ball) closestBall);//((Ball) closestBall);
            bl.setSpecificType(SpecificType.ChemicalBall);

            Timer timer = new Timer();
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    bl.setSpecificType(SpecificType.Ball);
                }
            }, POWERUP_CHEMICAL_BALL_TIME * 1000);

            return true;
        }
        return false;
    }

    @Override
    public boolean isInstant() {
        return false;
    }

    @Override
    public void collide(MovableShape obj) {
        if (obj.getType() == Type.Paddle) {
            this.destroy();
        }
    }

    @Override
    public SpecificType getSpecificType() {
        return SpecificType.ChemicalBallPowerup;
    }

    @Override
    public String toString() {
        return "Chemical Ball Powerup";
    }
}