package domain.model.powerup;

import domain.game.Board;
import domain.model.Paddle;
import domain.model.SpecificType;
import domain.model.Type;
import domain.model.movement.MovementBehavior;
import domain.model.shape.MovableShape;

import static utils.Constants.PowerupSize;

public class Magnet extends PowerUp  {
    public Magnet(MovementBehavior mb) {
        super(mb, PowerupSize, PowerupSize);
    }

    @Override
    public boolean activate(SpecificType type, Board board) {
        if (type == SpecificType.MagnetPowerup) {
            Paddle paddle = board.getPaddle();
            paddle.setMagnet(true);
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
        return SpecificType.MagnetPowerup;
    }

    @Override
    public String toString() {
        return "Magnet Powerup";
    }
}
