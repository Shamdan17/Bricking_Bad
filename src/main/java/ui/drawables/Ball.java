package ui.drawables;

import domain.model.shape.MovableShape;
import utils.physics.math.util;

import java.awt.*;

public class Ball implements Drawable {

  private MovableShape ms;
  private Image image;

  public Ball(MovableShape ms) {
    this.ms = ms;
  }

  @Override
  public void draw(Graphics g) {
    int x = util.round(ms.getPosition().getX());
    int y = util.round(ms.getPosition().getY());
    image = ImageFactory.get(ms.getSpecificType(), ms.getWidth(), ms.getLength());
    g.drawImage(image,x,y,null);
  }

  public void setMovable(MovableShape ms) {
    this.ms = ms;
  }
}
