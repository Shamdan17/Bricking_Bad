package ui.drawables.aliens;

import domain.model.shape.MovableShape;
import ui.ImageFactory;
import ui.drawables.Drawable;

import java.awt.*;

public class ProtectingAlien implements Drawable {

  private MovableShape ms;
  private Image image;

  public ProtectingAlien(MovableShape ms) {
    this.ms = ms;

  }

  @Override
  public void draw(Graphics g) {
    int x = (int) Math.round(ms.getPosition().getX());
    int y = (int) Math.round(ms.getPosition().getY());
//    if (image == null)
      image = ImageFactory.get(ms.getSpecificType(), ms.getWidth(), ms.getLength());
    g.drawImage(image,x,y,null);
  }

  public void setMovable(MovableShape ms) {
    this.ms = ms;
  }
}
