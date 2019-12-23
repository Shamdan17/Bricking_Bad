package ui.drawables;

import domain.model.shape.MovableShape;
import utils.physics.math.util;

import java.awt.*;

public class Explosion implements Drawable {

  private MovableShape ms;

  public Explosion(MovableShape ms) {
    this.ms = ms;
  }

  @Override
  public void draw(Graphics g) {
      System.out.println("Adding at: " + ms.getPosition());
    g.setColor(Color.ORANGE);
    g.fillOval(
        util.round(ms.getPosition().getX()),
        util.round(ms.getPosition().getY()),
        ms.getLength(),
        ms.getWidth());
    g.drawOval(
        util.round(ms.getPosition().getX()),
        util.round(ms.getPosition().getY()),
        ms.getLength(),
        ms.getWidth());
  }
  public void setMovable(MovableShape ms){
        this.ms = ms;
    }
}
