package ui;

import domain.BrickingBad;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class MKeyListener extends KeyAdapter {
    BrickingBad bb;

    public MKeyListener(BrickingBad bb) {
        this.bb = bb;
    }

    @Override
    public void keyPressed(KeyEvent event) {
        System.out.println("keyTyped: " + event.getKeyCode());

        if (event.getKeyCode() == KeyEvent.VK_RIGHT) {
            bb.movePaddleRight();
        }

        if (event.getKeyCode() == KeyEvent.VK_LEFT) {
            bb.movePaddleLeft();
        }

        if (event.getKeyCode() == KeyEvent.VK_A) {
            bb.rotatePaddleLeft();
        }

        if (event.getKeyCode() == KeyEvent.VK_D) {
            bb.rotatePaddleRight();
        }
    }
}