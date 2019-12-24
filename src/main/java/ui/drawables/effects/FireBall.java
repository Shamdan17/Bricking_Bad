package ui.drawables.effects;

import domain.model.SpecificType;
import domain.model.shape.MovableShape;
import ui.drawables.Drawable;
import ui.drawables.ImageFactory;
import utils.physics.math.util;
import java.awt.*;

public class FireBall implements Drawable {

  private MovableShape ms;
  private Image image;

  public FireBall(MovableShape ms) {
    this.ms = ms;
  }

  @Override
  public void draw(Graphics g) {
    int x = util.round(ms.getPosition().getX());
    int y = util.round(ms.getPosition().getY());
    if (image == null)
      image = ImageFactory.get(SpecificType.FireBall, ms.getWidth(), ms.getLength());
    g.drawImage(image,x,y,null);

//    g.setColor(Color.pink);
//    g.fillOval(
//        util.round(ms.getPosition().getX()),
//        util.round(ms.getPosition().getY()),
//        ms.getLength(),
//        ms.getWidth());
//    g.drawOval(
//        util.round(ms.getPosition().getX()),
//        util.round(ms.getPosition().getY()),
//        ms.getLength(),
//        ms.getWidth());
//    // Add dot in center
//    g.setColor(Color.black);
//    g.fillOval(util.round(ms.getCenter().getX()) - 2, util.round(ms.getCenter().getY()) - 2, 4, 4);
//    g.drawOval(util.round(ms.getCenter().getX()) - 2, util.round(ms.getCenter().getY()) - 2, 4, 4);
  }
  public void setMovable(MovableShape ms){
        this.ms = ms;
    }
}
