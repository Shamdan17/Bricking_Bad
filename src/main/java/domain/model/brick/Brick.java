package domain.model.brick;

import domain.model.shape.Rectangle;
import utils.Position;

public abstract class Brick extends Rectangle {

    public Brick(Position position, int length, int width) {
        super(position,length, width);
    }

    public final Type getType(){
        return Type.Brick;
    }

}
