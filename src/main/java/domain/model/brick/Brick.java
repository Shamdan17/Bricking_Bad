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

}
