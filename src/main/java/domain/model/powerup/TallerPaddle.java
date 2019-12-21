package domain.model.powerup;

import domain.game.Board;
import domain.model.Paddle;
import domain.model.SpecificType;
import domain.model.Type;
import domain.model.movement.MovementBehavior;
import domain.model.shape.MovableShape;
import utils.physics.math.util;

import java.util.Timer;
import java.util.TimerTask;

import static utils.Constants.*;

public class TallerPaddle extends PowerUp {

    public TallerPaddle(MovementBehavior mb) {
        super(mb, PowerupSize, PowerupSize);
    }

    @Override
    public boolean activate(SpecificType type, Board board) {
        if (type == SpecificType.TallerPaddlePowerup) {
            Paddle paddle = board.getPaddle();
            paddle.setLength(util.round(2 * L));
            Timer timer = new Timer();
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    paddle.setLength(util.round(L));
                }
            }, POWERUP_TALLER_PADDLE_TIME * 1000);
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
        return SpecificType.TallerPaddlePowerup;
    }

    @Override
    public String toString() {
        return "Taller Paddle Powerup";
    }
}
