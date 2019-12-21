package domain.model.powerup;

import domain.game.Board;
import domain.model.Ball;
import domain.model.SpecificType;
import domain.model.movement.MovementBehavior;
import domain.model.shape.MovableShape;
import utils.Position;
import utils.physics.math.util;

import java.util.List;

import static utils.Constants.PowerupSize;

public class Fireball extends PowerUp {
    public Fireball(MovementBehavior mb) {
        super(mb, PowerupSize, PowerupSize);
        super.destroy();
    }

    @Override
    public boolean activate(SpecificType type, Board board) {
        if (type == SpecificType.FireBallPowerup) {
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

            ((Ball) closestBall).setSpecificType(SpecificType.FireBall);

            return true;
        }
        return false;
    }

    @Override
    public boolean isInstant() {
        return true;
    }

    @Override
    public void collide(MovableShape obj) {
    }

    @Override
    public SpecificType getSpecificType() {
        return SpecificType.FireBallPowerup;
    }

    @Override
    public String toString() {
        return "Fireball Powerup";
    }
}