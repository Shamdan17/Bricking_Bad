package ui.drawables.bricks;

import domain.BrickingBad;
import domain.model.shape.MovableShape;
import utils.Position;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

public class SimpleBrick extends Brick implements MouseMotionListener {

    private boolean isDragged;
    private Position oldPos;
    private double dx;
    private double dy;

    public SimpleBrick(MovableShape ms, BrickingBad bb) {
        super(ms, bb);
        isDragged = false;
    }

    public void draw(Graphics g) {
        int x = (int) Math.round(ms.getPosition().getX());
        int y = (int) Math.round(ms.getPosition().getY());

        int length = ms.getLength();
        int width = ms.getWidth();
        g.setColor(Color.black);
        g.fillRect(x, y, length, width);
        g.drawRect(x, y, length, width);
    }

    @Override
    public boolean isInside(double x, double y) {
        double myX = ms.getPosition().getX();
        double myY = ms.getPosition().getY();
        double len = ms.getLength();
        double wid = ms.getWidth();
        return (x >= myX && x <= myX + len && y >= myY && y <= myY + wid);
    }

    @Override
    public void mouseClicked(MouseEvent mouseEvent) {
        double x = mouseEvent.getX();
        double y = mouseEvent.getY();
        if (isInside(x, y) && removeFlag) {
            brickingBad.removeBrick(ms.getID());
        }
    }

    @Override
    public void mousePressed(MouseEvent mouseEvent) {
        if (!isInside(mouseEvent.getX(), mouseEvent.getY())) return;
        if (!isDragged) {
            double x = ms.getPosition().getX();
            double y = ms.getPosition().getY();
            dx = mouseEvent.getX() - x;
            dy = mouseEvent.getY() - y;
            oldPos = new Position(ms.getPosition().getX(), ms.getPosition().getY());
            isDragged = true;
        }
    }

    @Override
    public void mouseReleased(MouseEvent mouseEvent) {
        if (isDragged) {
            Position newPos = new Position(mouseEvent.getX() - dx, mouseEvent.getY() - dy);
            boolean isMoved = brickingBad.moveBrick(ms.getID(), newPos);
            if (!isMoved) {
                brickingBad.moveBrick(ms.getID(), oldPos);
            }
            isDragged = false;
        }
    }

    @Override
    public void mouseEntered(MouseEvent mouseEvent) {
    }

    @Override
    public void mouseExited(MouseEvent mouseEvent) {
    }

    @Override
    public void mouseDragged(MouseEvent mouseEvent) {
        if (!isDragged) return;
        int x = mouseEvent.getX(), y = mouseEvent.getY();
        brickingBad.dragBrick(ms.getID(), new Position(x - dx, y - dy));
    }

    @Override
    public void mouseMoved(MouseEvent mouseEvent) {
    }
}
