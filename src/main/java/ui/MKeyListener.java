package ui;

import domain.BrickingBad;
import utils.Constants;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.HashSet;
import java.util.Set;

public class MKeyListener extends KeyAdapter {
  private final Set<Integer> pressed = new HashSet<Integer>();
  BrickingBad bb;

  public MKeyListener(BrickingBad bb) {
    this.bb = bb;
  }

  @Override
  public synchronized void keyPressed(KeyEvent event) {
    System.out.println("keyTyped: " + event.getKeyCode());
    pressed.add(event.getKeyCode());

    if (pressed.contains(KeyEvent.VK_RIGHT)) {
      bb.movePaddleRight();
    }

    if (pressed.contains(KeyEvent.VK_LEFT)) {
      bb.movePaddleLeft();
    }

    if (pressed.contains(Constants.KEY_ROTATE_PADDLE_LEFT)) {
      bb.rotatePaddleLeft();
    }

    if (pressed.contains(Constants.KEY_ROTATE_PADDLE_RIGHT)) {
      bb.rotatePaddleRight();
    }

    if (pressed.contains(Constants.KEY_ACTIVATE_TALLER_PADDLE)) {
      bb.activateTallerPaddle();
    }

    if (pressed.contains(Constants.KEY_ACTIVATE_MAGNET)) {
      bb.activateMagnet();
    }

    if (pressed.contains(Constants.KEY_THROW_BALL)) {
      bb.throwBall();
    }

    if (pressed.contains(KeyEvent.VK_SPACE)) {
      bb.shootLaser();
    }

    if (pressed.contains(Constants.KEY_ACTIVATE_CHEMICAL_BALL)) {
      bb.activateChemicalBall();
    }
  }

  @Override
  public synchronized void keyReleased(KeyEvent e) {
    pressed.remove(e.getKeyCode());
  }
}
