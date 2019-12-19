package ui;

import domain.BrickingBad;
import domain.mapbuild.MapBuildData;
import domain.model.shape.MovableShape;
import ui.bricks.HalfMetalBrick;
import ui.bricks.MineBrick;
import ui.bricks.SimpleBrick;
import ui.bricks.WrapperBrick;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MapBuildPanel extends JPanel implements Runnable, ActionListener {


    public JButton backToMain;
    private BrickingBad brickingBad;
    private JCheckBox deleteBlock;
    private JButton saveButton;

    public MapBuildPanel(BrickingBad bb) {
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
        MapBuildData mapBuildData = brickingBad.getMapBuildData();
        for (MovableShape ms : mapBuildData.getMovables()) {
            Drawable d = getDrawable(ms);
            this.addMouseListener((SimpleBrick) d);
            d.draw(g);
        }
    }

    // TODO: this code fragment is repeated, define a brick factory to shortcut it.
    public Drawable getDrawable(MovableShape ms) {
        switch (ms.getSpecificType()) {
            case SimpleBrick:
                return new SimpleBrick(ms, brickingBad);
            case MineBrick:
                return new MineBrick(ms, brickingBad);
            case HalfMetalBrick:
                return new HalfMetalBrick(ms, brickingBad);
            case WrapperBrick:
                return new WrapperBrick(ms, brickingBad);
            default:
                throw new IllegalArgumentException("provided type not supported");
        }
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("Save")) {
            brickingBad.saveMap();
        }
        if (e.getActionCommand().equals("delete by click")) {
            SimpleBrick.setRemoveFlag(deleteBlock.isSelected());
        }
    }

}
