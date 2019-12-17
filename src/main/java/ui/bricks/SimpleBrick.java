package ui.bricks;

import domain.BrickingBad;
import domain.model.shape.MovableShape;
import ui.Drawable;
import utils.Position;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class SimpleBrick extends Brick {


    private boolean isDragged;
    private Position oldPos;
    private double dx;
    private double dy;

    public SimpleBrick(MovableShape ms, BrickingBad bb) {
        super(ms,bb);
        isDragged = false;
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
    @Override
    public boolean isInside(double x,double y){
        double myX = ms.getPosition().getX();
        double myY = ms.getPosition().getY();
        double len = ms.getLength();
        double wid = ms.getWidth();
        return (x >= myX && x <= myX + len && y >= myY && y <= myY + wid);
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
