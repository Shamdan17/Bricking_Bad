package ui.drawables.powerups;

import domain.model.shape.MovableShape;
import ui.drawables.Drawable;

import java.awt.*;

public class ChemicalBallPowerup implements Drawable {
    MovableShape ms;

    public ChemicalBallPowerup(MovableShape ms){
        this.ms = ms;
    }

    @Override
    public void draw(Graphics g) {
        int x = (int) Math.round(ms.getPosition().getX());
        int y = (int) Math.round(ms.getPosition().getY());
        int length = ms.getLength();
        int width = ms.getWidth();
        g.setColor(Color.cyan);
        g.fillRect(x, y, length, width);
        g.drawRect(x, y, length, width);
    }
}
