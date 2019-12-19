package domain.model.powerup;

import domain.game.Board;
import domain.model.SpecificType;
import domain.model.Type;
import domain.model.movement.MovementBehavior;
import domain.model.shape.MovableShape;

import static utils.Constants.PowerupSize;

public class DestructiveLaserGun extends PowerUp {

    public DestructiveLaserGun(MovementBehavior movbeh) {
        super(movbeh, PowerupSize, PowerupSize);
    }

    @Override
    public boolean activate(SpecificType type, Board board) {
        if (type == SpecificType.DestructiveLaserGun) {
            board.getPaddle().applyLaserPowerup();
            return true;
        }
        return false;
    }

    @Override
    // when powerups are destroyed they are added to the inventory
    public void collide(MovableShape obj) {
        if (obj.getType() == Type.Paddle) {
            this.destroy();
        }
    }

    @Override
    public boolean isInstant() {
        return true;
    }

    @Override
    public SpecificType getSpecificType() {
        return SpecificType.DestructiveLaserGun;
    }

    @Override
    public String toString() {
        return "Destructive laser gun powerup";
    }
}
