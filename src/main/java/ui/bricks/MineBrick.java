package ui.bricks;

import domain.BrickingBad;
import domain.model.shape.MovableShape;
import ui.Drawable;
import utils.Position;
import utils.physics.math.util;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MineBrick implements Drawable, MouseListener {

    private MovableShape ms;
    private BrickingBad brickingBad;
    private static boolean removeFlag = false;

    public MineBrick(MovableShape ms, BrickingBad bb) {
        this.ms = ms;
        this.brickingBad = bb;
    }

    public void draw(Graphics g) {
        g.setColor(Color.BLUE);
        g.fillOval(util.round(ms.getPosition().getX()), util.round(ms.getPosition().getY()), ms.getLength(), ms.getWidth());
        g.drawOval(util.round(ms.getPosition().getX()), util.round(ms.getPosition().getY()), ms.getLength(), ms.getWidth());
    }

    public static void setRemoveFlag(boolean state) {
        removeFlag = state;
    }


    @Override
    public void mouseClicked(MouseEvent mouseEvent) {

        int x = mouseEvent.getX();
        int y = mouseEvent.getY();
        int myX = (int) Math.round(ms.getPosition().getX());
        int myY = (int) Math.round(ms.getPosition().getY());
        int len = ms.getLength();
        int wid = ms.getWidth();
        boolean flag = (x <= myX + len && x >= myX) && (y >= myY && y <= myY + wid);
        if (flag && removeFlag) {
            brickingBad.removeBrick(ms.getID());
        }

    }

    @Override
    public void mousePressed(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseReleased(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseEntered(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseExited(MouseEvent mouseEvent) {

    }

}
