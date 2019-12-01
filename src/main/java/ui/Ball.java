package ui;

import domain.model.shape.MovableShape;
import utils.physics.math.util;

import java.awt.*;

public class Ball implements Drawable {

    MovableShape ms;

    public Ball(MovableShape ms) {
        this.ms = ms;
    }

    public void draw(Graphics g) {
        g.setColor(Color.RED);
        g.fillOval(util.round(ms.getPosition().getX()), util.round(ms.getPosition().getY()), ms.getLength(), ms.getWidth());
        g.drawOval(util.round(ms.getPosition().getX()), util.round(ms.getPosition().getY()), ms.getLength(), ms.getWidth());
        // Add dot in center
        g.setColor(Color.black);
        g.fillOval(util.round(ms.getCenter().getX())-2, util.round(ms.getCenter().getY())-2, 4, 4);
        g.drawOval(util.round(ms.getCenter().getX())-2, util.round(ms.getCenter().getY())-2, 4, 4);
    }
}
