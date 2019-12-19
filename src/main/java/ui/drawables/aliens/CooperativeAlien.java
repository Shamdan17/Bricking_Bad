package ui.drawables.aliens;

import domain.model.shape.MovableShape;
import ui.drawables.Drawable;

import java.awt.*;

public class CooperativeAlien implements Drawable {

    private MovableShape ms;

    public CooperativeAlien(MovableShape ms){
        this.ms = ms;
    }

    @Override
    public void draw(Graphics g) {

    }
}
