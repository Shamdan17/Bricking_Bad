package ui.drawables.bricks;

import domain.BrickingBad;
import domain.model.shape.MovableShape;
import ui.drawables.Drawable;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.UUID;

public abstract class Brick implements Drawable, MouseListener, MouseMotionListener {


    protected MovableShape ms;
    protected BrickingBad brickingBad;
    public static boolean removeFlag = false;

    public Brick(MovableShape ms, BrickingBad bb) {
        this.ms = ms;
        this.brickingBad = bb;
    }

    public UUID getID() {
        return ms.getID();
    }

    public void setMovable(MovableShape ms) {
        this.ms = ms;
    }

    public static void setRemoveFlag(boolean state) {
        removeFlag = state;

    }

    @Override
    public abstract void draw(Graphics g);

    @Override
    public abstract void mouseClicked(MouseEvent mouseEvent);

    @Override
    public abstract void mousePressed(MouseEvent mouseEvent);

    @Override
    public abstract void mouseReleased(MouseEvent mouseEvent);

    @Override
    public abstract void mouseEntered(MouseEvent mouseEvent);

    @Override
    public abstract void mouseExited(MouseEvent mouseEvent);

    @Override
    public abstract void mouseDragged(MouseEvent mouseEvent);

    @Override
    public abstract void mouseMoved(MouseEvent mouseEvent);

    public abstract boolean isInside(double x, double y);
}
