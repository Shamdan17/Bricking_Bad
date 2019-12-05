package ui;

import domain.BrickingBad;
import domain.model.shape.MovableShape;

import java.awt.*;

public class Alien implements Drawable {


    private MovableShape ms;
    private BrickingBad brickingBad;
    private static boolean removeFlag = false;

    public Alien(MovableShape ms, BrickingBad bb) {
        this.ms = ms;
        brickingBad = bb;
    }

    public void draw(Graphics g) {
        int x = (int) Math.round(ms.getPosition().getX());
        int y = (int) Math.round(ms.getPosition().getY());
        int length = ms.getLength();
        int width = ms.getWidth();
        g.setColor(Color.red);
        g.fillRect(x, y, length, width);
        g.drawRect(x, y, length, width);
    }

    public static void setRemoveFlag(boolean state) {
        removeFlag = state;
    }


}
