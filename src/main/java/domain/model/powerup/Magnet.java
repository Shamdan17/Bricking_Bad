package domain.model.powerup;

import domain.game.Board;
import domain.model.Paddle;
import domain.model.SpecificType;
import domain.model.Type;
import domain.model.movement.MovementBehavior;
import domain.model.shape.MovableShape;
import org.apache.log4j.Logger;

import static utils.Constants.PowerupSize;

public class Magnet extends PowerUp {

    final static Logger logger = Logger.getLogger(Magnet.class);

    public Magnet(MovementBehavior mb) {
        super(mb, PowerupSize, PowerupSize);
    }

    @Override
    public boolean activate(SpecificType type, Board board) {
        if (type == SpecificType.MagnetPowerup) {
            Paddle paddle = board.getPaddle();
            paddle.setMagnet(true);
            logger.debug("Magnet power-up is activated");
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
            logger.debug("Magnet power-up is caught");
        }
    }

    @Override
    public SpecificType getSpecificType() {
        return SpecificType.MagnetPowerup;
    }

    @Override
    public String toString() {
        return "Magnet Powerup";
    }
}
