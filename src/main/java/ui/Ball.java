package ui;

import domain.model.shape.MovableShape;

import java.awt.*;

public class Ball implements Drawable {

    MovableShape ms;

    public Ball(MovableShape ms) {
        this.ms = ms;
    }

    public void draw(Graphics g){
        g.setColor(Color.RED);
        g.fillOval(ms.getPosition().getX(),ms.getPosition().getY(),ms.getLength(),ms.getWidth());
        g.drawOval(ms.getPosition().getX(),ms.getPosition().getY(),ms.getLength(),ms.getWidth());
    }
}
