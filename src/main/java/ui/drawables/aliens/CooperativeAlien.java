package ui.drawables.aliens;

import domain.model.shape.MovableShape;
import ui.drawables.Drawable;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

public class CooperativeAlien implements Drawable {

  private MovableShape ms;
  private String iconURL = "/home/nazirnayal/Desktop/302_2019_shawarma/icons/CooperativeAlien.jpeg";
  private BufferedImage bufferedImage;
  private Image image;

  public CooperativeAlien(MovableShape ms) {
    this.ms = ms;
    try {
      this.bufferedImage = ImageIO.read(new File(iconURL));
      image = bufferedImage.getScaledInstance(ms.getLength(), ms.getWidth(), Image.SCALE_DEFAULT);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  @Override
  public void draw(Graphics g) {
    int x = (int) Math.round(ms.getPosition().getX());
    int y = (int) Math.round(ms.getPosition().getY());
    g.drawImage(image, x, y, null);
    //        int length = ms.getLength();
    //        int width = ms.getWidth();
    //        g.setColor(Color.orange);
    //        g.fillRect(x, y, length, width);
    //        g.drawRect(x, y, length, width);
  }

  public void setMovable(MovableShape ms) {
    this.ms = ms;
  }
}
