package domain.model.powerup;

import domain.game.Board;
import domain.model.Ball;
import domain.model.SpecificType;
import domain.model.Type;
import domain.model.movement.LinearMovement;
import domain.model.movement.MovementBehavior;
import domain.model.shape.MovableShape;
import org.apache.log4j.Logger;
import utils.Constants;
import utils.Position;
import utils.Velocity;
import utils.physics.math.Rotation;
import utils.physics.math.util;

import java.util.List;

import static utils.Constants.POWERUP_SIZE;
import static utils.Constants.GANG_OF_BALLS_MULTIPLIER;

public class GangOfBalls extends PowerUp {

    final static Logger logger = Logger.getLogger(GangOfBalls.class);

    public GangOfBalls(MovementBehavior movBeh) {
        super(movBeh, POWERUP_SIZE, POWERUP_SIZE);
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
                if (!(ms instanceof Ball)) continue;
                Position objPos = ms.getCenter();
                if (util.getDistance(objPos, myPos) < closestDistance) {
                    closestDistance = util.getDistance(objPos, myPos);
                    closestBall = ms;
                }
            }

            // Create 9 mirrors
            Velocity originalVel = closestBall.getVelocity().copy();
            for (int i = 1; i < GANG_OF_BALLS_MULTIPLIER; i++) {
                originalVel = Rotation.rotate(originalVel, 360 / GANG_OF_BALLS_MULTIPLIER);
                Ball newBall = new Ball(new LinearMovement(closestBall.getPosition(), originalVel), Constants.BALL_RADIUS);
                super.addToQueue(newBall);
            }
            logger.debug("Gang of Balls power-up is activated with multiplier: " + GANG_OF_BALLS_MULTIPLIER);
            return true;
        }
        return false;
    }

    @Override
    public void collide(MovableShape obj) {
        if (obj.getType() == Type.Ball) {
            super.destroy();
            logger.debug("Gang of Balls power-up is caught");
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
