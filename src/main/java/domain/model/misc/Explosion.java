package domain.model.misc;

import domain.model.SpecificType;
import domain.model.Type;
import domain.model.movement.NoMovement;
import domain.model.shape.MovableShape;
import domain.model.shape.Shape;
import utils.Constants;
import utils.Position;
import utils.physics.math.util;

import static utils.Constants.explosion_radius_factor;

// An explosion is a collidable object that results when a minebrick explodes
public class Explosion extends MovableShape {
    public Explosion(Position center){
        super(new NoMovement(center.incrementX(-util.round((explosion_radius_factor/2)*Constants.L)).incrementY(-util.round((explosion_radius_factor/2)*Constants.L))),
                util.round(explosion_radius_factor* Constants.L),
                util.round(explosion_radius_factor* Constants.L));
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
        return SpecificType.Explosion;
    }

    @Override
    public String toString() {
        return "Explosion at "+getPosition();
    }

    @Override
    public MovableShape copy() {
        return new Explosion(getPosition());
    }
}
