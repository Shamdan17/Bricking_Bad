package ui;

import domain.model.shape.MovableShape;

import java.awt.*;

public class Paddle implements Drawable {

    MovableShape ms ;

    public Paddle(MovableShape ms){
        this.ms = ms;
    }

    public void draw(Graphics g){

        double sina = Math.sin(Math.toRadians(ms.getAngle() + 90));
        double cosa = Math.cos(Math.toRadians(ms.getAngle() + 90));

        int px = ms.getPosition().getX();
        int py = ms.getPosition().getY();
        double width = ms.getWidth();
        double length = ms.getLength();

        int[] x = {
                px,
                px - (int) Math.round(width*cosa),
                px + (int) Math.round(length*sina) - (int) Math.round(width*cosa),
                px + (int) Math.round(length*sina)
        };
        int[] y = {
                py,
                py + (int) Math.round(width*sina),
                py + (int) Math.round(width*sina) +(int) Math.round(length*cosa),
                py + (int) Math.round(length*cosa)
        };
        g.fillPolygon(x,y,4);
        g.setColor(Color.GRAY);
        g.drawPolygon(x,y,4);
    }

}
