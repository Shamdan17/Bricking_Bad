package ui.drawables;

import domain.model.shape.MovableShape;

import java.awt.*;

public interface Drawable {

    void draw(Graphics g);

    void setMovable(MovableShape ms);
}
