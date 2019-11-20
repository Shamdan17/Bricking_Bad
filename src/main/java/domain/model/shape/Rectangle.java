package domain.model.shape;

import utils.Position;

public abstract class Rectangle extends MovableShape{
    public final Shape getShape(){
        return Shape.Rectangle;
    }

    private Position position;

    public Rectangle(int length, int width){
        super(length, width);
    }

}
