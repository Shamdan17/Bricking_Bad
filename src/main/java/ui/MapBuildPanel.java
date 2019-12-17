package ui;

import domain.BrickingBad;
import domain.mapbuild.MapBuildData;
import domain.model.shape.MovableShape;
import ui.bricks.*;
import utils.Constants;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.text.NumberFormat;

public class MapBuildPanel extends JPanel
    implements Runnable, ActionListener, PropertyChangeListener {

  public JButton backToMain;
  private BrickingBad brickingBad;
  private JCheckBox deleteBlock;
  private JButton saveButton;
  private JButton loadButton;
  private JButton createButton;

  private JFormattedTextField simpleBrickField;
  private JFormattedTextField halfMetalBrickField;
  private JFormattedTextField mineBrickField;
  private JFormattedTextField wrapperBrickField;
  private JLabel simpleBrickLabel;
  private JLabel halfMetalBrickLabel;
  private JLabel mineBrickLabel;
  private JLabel wrapperBrickLabel;
  private NumberFormat numberFormat;
  private JPanel bottomPanel;

  public MapBuildPanel(BrickingBad bb) {
    backToMain = new JButton("Back to Main");
    saveButton = new JButton("Save");
    loadButton = new JButton("Load");
    createButton = new JButton("Add Blocks");
    deleteBlock = new JCheckBox("delete by click");
    loadButton.addActionListener(this);
    saveButton.addActionListener(this);
    deleteBlock.addActionListener(this);
    createButton.addActionListener(this);
    brickingBad = bb;

    bottomPanel = new JPanel();
    bottomPanel.setBounds(0, 750, 750, 1500);

    numberFormat = NumberFormat.getIntegerInstance();

    simpleBrickField = new JFormattedTextField(numberFormat);
    simpleBrickField.setColumns(10);
    simpleBrickField.setValue(Constants.MIN_SIMPLE_BRICK);
    halfMetalBrickField = new JFormattedTextField(numberFormat);
    halfMetalBrickField.setColumns(10);
    halfMetalBrickField.setValue(Constants.MIN_HALF_METAL_BRICK);
    mineBrickField = new JFormattedTextField(numberFormat);
    mineBrickField.setColumns(10);
    mineBrickField.setValue(Constants.MIN_MINE_BRICK);
    wrapperBrickField = new JFormattedTextField(numberFormat);
    wrapperBrickField.setColumns(10);
    wrapperBrickField.setValue(Constants.MIN_WRAPPER_BRICK);

    simpleBrickLabel = new JLabel("Simple Brick Count");
    halfMetalBrickLabel = new JLabel("Half Metal Brick Count");
    mineBrickLabel = new JLabel("Mine Brick Count");
    wrapperBrickLabel = new JLabel("Wrapper Brick Count");

    bottomPanel.add(simpleBrickLabel);
    bottomPanel.add(simpleBrickField);

    bottomPanel.add(halfMetalBrickLabel);
    bottomPanel.add(halfMetalBrickField);

    bottomPanel.add(mineBrickLabel);
    bottomPanel.add(mineBrickField);

    bottomPanel.add(wrapperBrickLabel);
    bottomPanel.add(wrapperBrickField);

    setLayout(new FlowLayout());
    this.add(createButton);
    this.add(bottomPanel);
    this.add(backToMain);
    this.add(saveButton);
    this.add(loadButton);
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
    MouseListener[] list = this.getMouseListeners();
    for(int i = 0 ; i<list.length ; ++i){
        this.removeMouseListener(list[i]);
    }
    MapBuildData mapBuildData = brickingBad.getMapBuildData();
    for (MovableShape ms : mapBuildData.getMovables()) {
      Drawable d = BrickFactory.getDrawable(ms,brickingBad, this);
      d.draw(g);
    }
  }


  public void actionPerformed(ActionEvent e) {
    if (e.getActionCommand().equals("Save")) {
      brickingBad.saveMap();
    }
    if (e.getActionCommand().equals("delete by click")) {
      SimpleBrick.setRemoveFlag(deleteBlock.isSelected());
      HalfMetalBrick.setRemoveFlag(deleteBlock.isSelected());
      MineBrick.setRemoveFlag(deleteBlock.isSelected());
      WrapperBrick.setRemoveFlag(deleteBlock.isSelected());
    }
    if (e.getActionCommand().equals("Load")) {
      brickingBad.loadMap();
    }
    if (e.getActionCommand().equals("Add Blocks")) {
      System.out.println("checkin");

      Object simpleVal = simpleBrickField.getValue();
      if (simpleVal == null) {
        String warning = "Simple Brick Fiels is empty";
        JOptionPane.showMessageDialog(null, warning);
      }

      Object halfMetalVal = halfMetalBrickField.getValue();
      if (halfMetalVal == null) {
        String warning = "Half Metal Brick Field is empty";
        JOptionPane.showMessageDialog(null, warning);
        return;
      }

      Object mineVal = mineBrickField.getValue();
      if (mineVal == null) {
        String warning = "Mine Brick Field is empty";
        JOptionPane.showMessageDialog(null, warning);
        return;
      }

      Object wrapperVal = wrapperBrickField.getValue();
      if (wrapperVal == null) {
        String warning = "Wrapper Brick Field is empty";
        JOptionPane.showMessageDialog(null, warning);
        return;
      }

      int simple = ((Number) simpleVal).intValue();
      int halfMetal = ((Number) halfMetalVal).intValue();
      int mineCount = ((Number) mineVal).intValue();
      int wrapper = ((Number) wrapperVal).intValue();
      boolean success = brickingBad.buildMap(simple, halfMetal, mineCount, wrapper);
      if (!success) {
        String warning = "Number of Bricks does not satisfy constraints";
        JOptionPane.showMessageDialog(null, warning);
      }
    }
  }

  @Override
  public void propertyChange(PropertyChangeEvent propertyChangeEvent) {}
}
