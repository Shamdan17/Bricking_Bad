package ui.bricks;

import domain.BrickingBad;
import domain.model.shape.MovableShape;
import ui.Drawable;

import javax.swing.*;

public class BrickFactory {

  public static Drawable getDrawable(MovableShape ms, BrickingBad brickingBad, JPanel panel) {
    switch (ms.getSpecificType()) {
      case SimpleBrick:
          SimpleBrick simpleBrick = new SimpleBrick(ms, brickingBad);
          panel.addMouseListener(simpleBrick);
          return simpleBrick;
      case MineBrick:
          MineBrick mineBrick = new MineBrick(ms, brickingBad);
          panel.addMouseListener(mineBrick);
          return mineBrick;
      case HalfMetalBrick:
        HalfMetalBrick halfMetalBrick = new HalfMetalBrick(ms, brickingBad);
        panel.addMouseListener(halfMetalBrick);
        return halfMetalBrick;
      case WrapperBrick:
        WrapperBrick wrapperBrick = new WrapperBrick(ms, brickingBad);
        panel.addMouseListener(wrapperBrick);
        return wrapperBrick;
      default:
        throw new IllegalArgumentException("provided type not supported");
    }
  }
}
