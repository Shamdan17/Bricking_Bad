package domain.model.brick;

import domain.model.shape.Rectangle;
import utils.Position;

public abstract class Brick extends Rectangle {

    public Brick(int length, int width) {
        super(length, width);
    }

    public final Type getType(){
        return Type.Brick;
    }

    public abstract void move();

    public Position getPosition() {
        return super.getPosition();
    }

    public void setPosition(Position position) {
        super.setPosition(position);
    }

    public abstract String toString();

    @Override
    // Bricks can't rotate so angle is always 0
    public final double getAngle() {
        return 0;
    }

    @Override
    // SetAngle meaningless
    public final void setAngle(double angle) {}

    @Override
    // increment angle meaningless
    public final void incrementAngle(double dif){}
}
