package ui.bricks;

import domain.BrickingBad;
import domain.model.shape.MovableShape;
import ui.Drawable;
import utils.Position;
import utils.physics.math.util;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MineBrick extends Brick {

    private boolean isDragged;
    private Position oldPos;
    private double dx;
    private double dy;

    public MineBrick(MovableShape ms, BrickingBad bb) {
       super(ms,bb);
       isDragged = false;
    }

    public void draw(Graphics g) {
        g.setColor(Color.BLUE);
        g.fillOval(util.round(ms.getPosition().getX()), util.round(ms.getPosition().getY()), ms.getLength(), ms.getWidth());
        g.drawOval(util.round(ms.getPosition().getX()), util.round(ms.getPosition().getY()), ms.getLength(), ms.getWidth());
    }

    @Override
    public boolean isInside(double x,double y){
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
        if(!isInside(mouseEvent.getX(),mouseEvent.getY()))
            return;
        if(!isDragged){
            isDragged = true;
            oldPos = new Position(ms.getPosition().getX(),ms.getPosition().getY());
            dx = (mouseEvent.getX() - oldPos.getX());
            dy = (mouseEvent.getY() - oldPos.getY());
        }else {
            Position newPos = new Position(mouseEvent.getX(),mouseEvent.getY());
            ms.setPosition(newPos);
        }
    }

    @Override
    public void mouseReleased(MouseEvent mouseEvent) {
        if(isDragged){
            Position newPos = new Position(mouseEvent.getX() - dx,mouseEvent.getY() - dy);
            boolean isMoved = brickingBad.moveBrick(ms.getID(),newPos);
            if(!isMoved){
                System.out.println("not moved");
                ms.setPosition(oldPos);
            }
            isDragged = false;
            dx = 0;
            dy = 0;
        }
    }

    @Override
    public void mouseEntered(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseExited(MouseEvent mouseEvent) {

    }

}
