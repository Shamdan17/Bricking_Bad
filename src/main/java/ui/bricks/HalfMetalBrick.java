package ui.bricks;

import domain.BrickingBad;
import domain.model.shape.MovableShape;
import ui.Drawable;
import utils.Position;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class HalfMetalBrick implements Drawable, MouseListener {

    private static boolean removeFlag = false;
    private MovableShape ms;
    private BrickingBad brickingBad;

    public HalfMetalBrick(MovableShape ms, BrickingBad bb) {
        this.ms = ms;
        this.brickingBad = bb;
    }

    public static void setRemoveFlag(boolean state) {
        removeFlag = state;
    }

    public void draw(Graphics g) {
        int x = (int) Math.round(ms.getPosition().getX());
        int y = (int) Math.round(ms.getPosition().getY());
        int length = ms.getLength();
        int width = ms.getWidth();

        g.setColor(Color.YELLOW);
        g.fillRect(x, y, length, width / 2);
        g.drawRect(x, y, length, width / 2);

        g.setColor(Color.DARK_GRAY);
        g.fillRect(x, y + (width / 2), length, width / 2);
        g.drawRect(x, y + (width / 2), length, width / 2);
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
