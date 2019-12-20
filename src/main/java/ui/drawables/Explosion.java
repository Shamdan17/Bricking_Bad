package ui.drawables;

import domain.model.shape.MovableShape;
import utils.physics.math.util;

import java.awt.*;

public class Explosion implements Drawable{

    private MovableShape ms;

    public Explosion(MovableShape ms){
        this.ms = ms;
    }

    @Override
     public void draw(Graphics g) {
        g.setColor(Color.ORANGE);
        g.fillOval(util.round(ms.getCenter().getX()), util.round(ms.getCenter().getY()), ms.getLength(), ms.getWidth());
        g.drawOval(util.round(ms.getCenter().getX()), util.round(ms.getCenter().getY()), ms.getLength(), ms.getWidth());
        // Add dot in center
        g.setColor(Color.black);
        g.fillOval(util.round(ms.getCenter().getX()) - 2, util.round(ms.getCenter().getY()) - 2, 4, 4);
        g.drawOval(util.round(ms.getCenter().getX()) - 2, util.round(ms.getCenter().getY()) - 2, 4, 4);
    }
}
