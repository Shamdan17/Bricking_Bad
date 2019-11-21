package ui;

import javax.swing.*;
import java.util.List;
import utils.Position;
import domain.BrickingBad;
import domain.model.Movable;


public class Game extends JFrame {

  BrickingBad controller;

  public Game() {
    controller = new BrickingBad();
  }

  public void drawFrame() {
    List<Movable> movables = controller.getMovables();
    for(Movable m : movables) {
      // TODO, replace big string with .getType method, or find another solution
      drawMovable("we need getType on movable probably", m.getPosition());
    }
  }

  public void drawMovable(String type, Position p) {

      // Draw on frame
  }

}
