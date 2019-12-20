package ui;

import domain.BrickingBad;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.HashSet;
import java.util.Set;

public class MKeyListener extends KeyAdapter {
    BrickingBad bb;

    private final Set<Integer> pressed = new HashSet<Integer>();

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

        if (event.getKeyCode() == KeyEvent.VK_SPACE) {
            bb.shootLaser();
        }
    }

    @Override
    public synchronized void keyReleased(KeyEvent e) {
        pressed.remove(e.getKeyCode());
    }


}