package ui;

import domain.BrickingBad;
import domain.model.shape.MovableShape;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.List;

public class GamePlayPanel extends JPanel implements Runnable, KeyListener {

    public JButton backToMain;
    private BrickingBad brickingBad;
    private JButton saveButton;
    private JButton loadButton;


    public GamePlayPanel(BrickingBad bb) {
        backToMain = new JButton("Back to Main");
        brickingBad = bb;
        saveButton = new JButton("save");
        loadButton = new JButton("load");
        this.add(backToMain);
        this.add(saveButton);
        this.add(loadButton);
        (new Thread(this)).start();

        saveButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                bb.save();
                saveButton.setFocusable(false);
                loadButton.setFocusable(false);
            }
        });

        loadButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent actionEvent) {
                bb.load();
                loadButton.setFocusable(false);
                saveButton.setFocusable(false);
            }
        });
    }

    public void run() {
        while (true) {
            try {
                repaint();
                Thread.sleep(30);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void paintComponent(Graphics g) {
        brickingBad.animate();
        super.paintComponent(g);
        List<MovableShape> drawables = brickingBad.getGameMovables();
        for (MovableShape ms : drawables) {
            Drawable d = getDrawable(ms);
            d.draw(g);
        }
    }

    public Drawable getDrawable(MovableShape ms) {
        if (ms.getType() == MovableShape.Type.Brick)
            return new Brick(ms, brickingBad);
        if (ms.getType() == MovableShape.Type.Paddle)
            return new Paddle(ms);
        if (ms.getType() == MovableShape.Type.Ball)
            return new Ball(ms);
        // TODO: add a dummy default value
        // default return value
        return new Brick(ms, brickingBad);
    }


    @Override
    public void keyTyped(KeyEvent keyEvent) {

    }

    @Override
    public void keyPressed(KeyEvent keyEvent) {

    }

    @Override
    public void keyReleased(KeyEvent keyEvent) {

    }
}
