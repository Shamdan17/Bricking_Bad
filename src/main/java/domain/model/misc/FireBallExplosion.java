package domain.model.misc;

import domain.model.SpecificType;
import domain.model.Type;
import domain.model.movement.NoMovement;
import domain.model.shape.MovableShape;
import domain.model.shape.Shape;
import org.apache.commons.lang3.SerializationUtils;
import utils.Constants;
import utils.Position;
import utils.physics.math.util;

import static utils.Constants.FIREBALL_EXPLOSION_RADIUS_FACTOR;

public class FireBallExplosion extends MovableShape {
    public FireBallExplosion(Position center) {
        super(
                new NoMovement(
                        center
                                .incrementX(
                                        -util.round((FIREBALL_EXPLOSION_RADIUS_FACTOR / 2.0) * Constants.BALL_DIAMETER))
                                .incrementY(
                                        -util.round(
                                                (FIREBALL_EXPLOSION_RADIUS_FACTOR / 2.0) * Constants.BALL_DIAMETER))),
                util.round(FIREBALL_EXPLOSION_RADIUS_FACTOR * Constants.BALL_DIAMETER),
                util.round(FIREBALL_EXPLOSION_RADIUS_FACTOR * Constants.BALL_DIAMETER));
        // Mark as destroyed in order for it to be removed next iteration
        super.destroy();
    }

    @Override
    public void collide(MovableShape obj) {
    }

    @Override
    public Type getType() {
        return Type.Ball;
    }

    @Override
    public Shape getShape() {
        return Shape.Circle;
    }

    @Override
    public SpecificType getSpecificType() {
        return SpecificType.FireBallExplosion;
    }

    @Override
    public String toString() {
        return "Fireball explosion at" + getPosition();
    }

    @Override
    public MovableShape copy() {
        return SerializationUtils.clone(this);
    }
}
