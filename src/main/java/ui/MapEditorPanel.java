package ui;

import domain.BrickingBad;
import domain.model.shape.MovableShape;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class MapEditorPanel extends JPanel implements Runnable, ActionListener {


    public JButton backToMain;
    private BrickingBad brickingBad;
    private JCheckBox deleteBlock;
    private JButton saveButton;

    public MapEditorPanel(BrickingBad bb) {
        backToMain = new JButton("Back to Main");
        saveButton = new JButton("Save");
        deleteBlock = new JCheckBox("delete by click");
        deleteBlock.addActionListener(this);
        brickingBad = bb;

        this.add(backToMain);
        this.add(saveButton);
        this.add(deleteBlock);
        (new Thread(this)).start();
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
        super.paintComponent(g);
        List<MovableShape> drawables = brickingBad.getMapEditorMovables();
        for (MovableShape ms : drawables) {
            Drawable d = getDrawable(ms);
            this.addMouseListener((Brick) d);
            d.draw(g);
        }
    }

    public Drawable getDrawable(MovableShape ms) {
        if (ms.getType() == MovableShape.Type.Brick)
            return new Brick(ms, brickingBad);
        return new Brick(ms, brickingBad);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("Save")) {
            brickingBad.saveMap();
        }
        if (e.getActionCommand().equals("delete by click")) {
            Brick.setRemoveFlag(deleteBlock.isSelected());
        }
    }

}
