package ui;

import javax.swing.*;
import java.util.List;
import domain.model.shape.MovableShape;
import utils.Position;
import domain.BrickingBad;
import domain.model.Movable;


public class Game extends JFrame {

  BrickingBad controller;

  public Game() {
    controller = new BrickingBad();
  }

  public void drawFrame() {
    List<MovableShape> movables = controller.getMovables();
    for(MovableShape m : movables) {
      // TODO, replace big string with .getType method, or find another solution
      drawMovable("we need getType on movable probably", m);
    }
  }

  public void drawMovable(String type, MovableShape mshape) {

      // Draw on frame
  }

}
