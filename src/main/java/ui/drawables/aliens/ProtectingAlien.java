package ui.drawables.aliens;

import domain.model.shape.MovableShape;
import ui.drawables.Drawable;

import java.awt.*;

public class ProtectingAlien implements Drawable {

    private MovableShape ms;

    public ProtectingAlien(MovableShape ms){
        this.ms = ms;
    }

    @Override
    public void draw(Graphics g) {

    }
}
