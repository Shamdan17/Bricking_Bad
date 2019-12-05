package ui.bricks;

import domain.BrickingBad;
import domain.model.shape.MovableShape;
import ui.Drawable;
import utils.Position;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class SimpleBrick implements Drawable, MouseListener {

    private MovableShape ms;
    private BrickingBad brickingBad;
    private static boolean removeFlag = false;

    public SimpleBrick(MovableShape ms, BrickingBad bb) {
        this.ms = ms;
        brickingBad = bb;
    }

    public void draw(Graphics g) {
        int x = (int) Math.round(ms.getPosition().getX());
        int y = (int) Math.round(ms.getPosition().getY());
        int length = ms.getLength();
        int width = ms.getWidth();
        g.setColor(Color.darkGray);
        g.fillRect(x, y, length, width);
        g.drawRect(x, y, length, width);
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
            brickingBad.removeBrick(new Position(myX, myY));
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
