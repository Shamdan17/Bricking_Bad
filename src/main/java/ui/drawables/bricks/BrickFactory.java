package ui.drawables.bricks;

import domain.BrickingBad;
import domain.model.shape.MovableShape;
import ui.drawables.Drawable;

public class BrickFactory {

  public static Drawable getDrawable(MovableShape ms, BrickingBad brickingBad) {
    switch (ms.getSpecificType()) {
      case SimpleBrick:
        SimpleBrick simpleBrick = new SimpleBrick(ms, brickingBad);
        return simpleBrick;
      case MineBrick:
        MineBrick mineBrick = new MineBrick(ms, brickingBad);
        return mineBrick;
      case HalfMetalBrick:
        HalfMetalBrick halfMetalBrick = new HalfMetalBrick(ms, brickingBad);
        return halfMetalBrick;
      case WrapperBrick:
        WrapperBrick wrapperBrick = new WrapperBrick(ms, brickingBad);
        return wrapperBrick;
      default:
        throw new IllegalArgumentException("provided type not supported");
    }
  }
}
