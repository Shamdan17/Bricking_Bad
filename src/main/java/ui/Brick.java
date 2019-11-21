package ui;

import domain.BrickingBad;
import domain.model.shape.MovableShape;
import utils.Position;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.font.GraphicAttribute;

public class Brick implements Drawable, MouseMotionListener, MouseListener {

    private MovableShape ms;
    private BrickingBad brickingBad;

    public Brick(MovableShape ms, BrickingBad bb){
        this.ms = ms;
        brickingBad = bb;
    }

    public void draw(Graphics g){
        int x = ms.getPosition().getX();
        int y = ms.getPosition().getY();
        int length = ms.getLength();
        int width = ms.getWidth();
        g.fillRect(x,y,length,width);
        g.drawRect(x,y,length,width);
    }


    @Override
    public void mouseClicked(MouseEvent mouseEvent) {

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

    @Override
    public void mouseDragged(MouseEvent mouseEvent) {
        if(mouseEvent.getButton() == MouseEvent.BUTTON1){
            Position oldPos = ms.getPosition();
            int x = mouseEvent.getX();
            int y = mouseEvent.getY();
            brickingBad.moveBrick(oldPos,new Position(x,y));
            ms.setPosition(new Position(x,y));
        }
    }

    @Override
    public void mouseMoved(MouseEvent mouseEvent) {

    }

}
