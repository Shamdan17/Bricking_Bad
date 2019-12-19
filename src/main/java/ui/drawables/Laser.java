package ui.drawables;

import domain.model.shape.MovableShape;

import java.awt.*;

public class Laser implements Drawable {

    MovableShape ms;

    public Laser(MovableShape ms){
        this.ms = ms;
    }

    @Override
    public void draw(Graphics g) {

    }
}
