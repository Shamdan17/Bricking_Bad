package ui.drawables.powerups;

import domain.model.shape.MovableShape;
import ui.drawables.Drawable;
import ui.drawables.ImageFactory;

import java.awt.*;

public class TallerPaddle implements Drawable {

    private MovableShape ms;
    private Image image;

    public TallerPaddle(MovableShape ms) {
        this.ms = ms;
    }

    @Override
    public void draw(Graphics g) {
        int x = (int) Math.round(ms.getPosition().getX());
        int y = (int) Math.round(ms.getPosition().getY());
        if (image == null)
            image = ImageFactory.get(ms.getSpecificType(), ms.getWidth(), ms.getLength());
        g.drawImage(image, x, y, null);
    }

    public void setMovable(MovableShape ms) {
        this.ms = ms;
    }

}
