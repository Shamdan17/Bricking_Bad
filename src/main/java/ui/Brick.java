package ui;

import domain.model.shape.MovableShape;

import java.awt.*;
import java.awt.font.GraphicAttribute;

public class Brick implements Drawable{

    MovableShape ms;

    public Brick(MovableShape ms){
        this.ms = ms;
    }

    public void draw(Graphics g){
        int x = ms.getPosition().getX();
        int y = ms.getPosition().getY();
        int length = ms.getLength();
        int width = ms.getWidth();
        g.drawRect(x,y,length,width);
    }

}
