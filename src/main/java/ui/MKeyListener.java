package ui;

import domain.BrickingBad;

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

    if (pressed.contains(KeyEvent.VK_A)) {
      bb.rotatePaddleLeft();
    }

    if (pressed.contains(KeyEvent.VK_D)) {
      bb.rotatePaddleRight();
    }

    if (pressed.contains(KeyEvent.VK_T)) {
      bb.activateTallerPaddle();
    }

    if (pressed.contains(KeyEvent.VK_M)) {
      bb.activateMagnet();
    }

    if (pressed.contains(KeyEvent.VK_W)) {
      bb.throwBall();
    }

    if (pressed.contains(KeyEvent.VK_SPACE)) {
      bb.shootLaser();
    }

    if (pressed.contains(KeyEvent.VK_C)) {
      bb.activateChemicalBall();
    }
  }

  @Override
  public synchronized void keyReleased(KeyEvent e) {
    pressed.remove(e.getKeyCode());
  }
}
