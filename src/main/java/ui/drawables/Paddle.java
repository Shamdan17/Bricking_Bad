package ui.drawables;

import domain.BrickingBad;
import domain.model.shape.MovableShape;
import ui.drawables.Drawable;
import utils.physics.math.util;

import java.awt.*;

public class Paddle implements Drawable {

    MovableShape ms;
    BrickingBad brickingBad;

    public Paddle(MovableShape ms, BrickingBad bb) {
        this.ms = ms;
        this.brickingBad = bb;
    }

    public void draw(Graphics g) {

        double sina = Math.sin(Math.toRadians(ms.getAngle() - 90));
        double cosa = Math.cos(Math.toRadians(ms.getAngle() - 90));

        int px = util.round(ms.getPosition().getX());
        int py = util.round(ms.getPosition().getY());
        double width = ms.getWidth();
        double length = ms.getLength();

        int[] x = {
                px,
                px + util.round(width * cosa),
                px - util.round(length * sina) + util.round(width * cosa),
                px - util.round(length * sina)
        };
        int[] y = {
                py,
                py - util.round(width * sina),
                py - util.round(width * sina) - util.round(length * cosa),
                py - util.round(length * cosa)
        };
        g.setColor(Color.GRAY);
        g.fillPolygon(x, y, 4);
        g.drawPolygon(x, y, 4);
    }


}
