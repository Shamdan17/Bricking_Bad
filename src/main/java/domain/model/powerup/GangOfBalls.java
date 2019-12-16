package domain.model.powerup;

import domain.game.Board;
import domain.model.Ball;
import domain.model.SpecificType;
import domain.model.Type;
import domain.model.movement.LinearMovement;
import domain.model.movement.MovementBehavior;
import domain.model.shape.MovableShape;
import utils.Constants;
import utils.Position;
import utils.Velocity;
import utils.physics.math.Rotation;
import utils.physics.math.util;

import java.util.List;

import static utils.Constants.PowerupSize;
import static utils.Constants.gangofballs_multiplier;

public class GangOfBalls extends PowerUp {

    public GangOfBalls(MovementBehavior movBeh) {
        super(movBeh, PowerupSize, PowerupSize);
        super.destroy();
    }

    @Override
    public boolean activate(SpecificType type, Board board) {
        if (type == SpecificType.GangOfBallsPowerup) {
            List<MovableShape> shapes = board.getData().getMovables();
            // Find the closest ball and multiply it by 10
            MovableShape closestBall = null;
            double closestDistance = 100000;
            Position myPos = getCenter();
            for (MovableShape ms : shapes) {
                if (ms.getSpecificType() != SpecificType.Ball) continue;
                Position objPos = ms.getCenter();
                if (util.getDistance(objPos, myPos) < closestDistance) {
                    closestDistance = util.getDistance(objPos, myPos);
                    closestBall = ms;
                }
            }

            // Create 9 mirrors
            Velocity originalVel = closestBall.getVelocity().copy();
            for (int i = 1; i < gangofballs_multiplier; i++) {
                originalVel = Rotation.rotate(originalVel, 360 / gangofballs_multiplier);
                Ball newBall = new Ball(new LinearMovement(closestBall.getPosition(), originalVel), Constants.RADIUS);
                super.addToQueue(newBall);
            }
            return true;
        }
        return false;
    }

    @Override
    public void collide(MovableShape obj) {
        if (obj.getType() == Type.Ball) {
            super.destroy();
        }
    }

    @Override
    public boolean isInstant() {
        return true;
    }

    @Override
    public SpecificType getSpecificType() {
        return SpecificType.GangOfBallsPowerup;
    }

    @Override
    public String toString() {
        return "Gang of balls Powerup";
    }
}
