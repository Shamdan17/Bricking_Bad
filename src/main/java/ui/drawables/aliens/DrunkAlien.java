package ui.drawables.aliens;

import domain.model.shape.MovableShape;
import ui.drawables.Drawable;

import java.awt.*;

public class DrunkAlien implements Drawable {

    private MovableShape ms;

    public DrunkAlien(MovableShape ms){
        this.ms = ms;
    }

    @Override
    public void draw(Graphics g) {
        int x = (int) Math.round(ms.getPosition().getX());
        int y = (int) Math.round(ms.getPosition().getY());
        int length = ms.getLength();
        int width = ms.getWidth();

        g.setColor(Color.orange);
        g.fillRect(x, y, length, width / 2);
        g.drawRect(x, y, length, width / 2);

        g.setColor(Color.GREEN);
        g.fillRect(x, y + (width / 2), length, width / 2);
        g.drawRect(x, y + (width / 2), length, width / 2);
    }

    public void setMovable(MovableShape ms){
        this.ms = ms;
    }
}
