package ui.drawables.bricks;

import domain.BrickingBad;
import domain.model.shape.MovableShape;
import utils.Position;
import utils.physics.math.util;

import java.awt.*;
import java.awt.event.MouseEvent;

public class MineBrick extends Brick {

    private boolean isDragged;
    private Position oldPos;
    private double dx;
    private double dy;

    public MineBrick(MovableShape ms, BrickingBad bb) {
        super(ms, bb);
        isDragged = false;
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(Color.BLUE);
        g.fillOval(
                util.round(ms.getPosition().getX()),
                util.round(ms.getPosition().getY()),
                ms.getLength(),
                ms.getWidth());
        g.drawOval(
                util.round(ms.getPosition().getX()),
                util.round(ms.getPosition().getY()),
                ms.getLength(),
                ms.getWidth());
    }

    @Override
    public boolean isInside(double x, double y) {
        double myX = ms.getCenter().getX();
        double myY = ms.getCenter().getY();
        double dist = Math.sqrt((myX - x) * (myX - x) + (myY - y) * (myY - y));
        return dist <= (ms.getLength() / 2);
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
