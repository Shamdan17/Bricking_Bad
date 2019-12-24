package ui.panels;

import domain.BrickingBad;
import ui.drawables.bricks.Brick;
import ui.load.MapLoadPage;
import ui.save.MapSavePage;
import utils.Constants;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;

public class MapSetup extends JPanel implements ActionListener {

  private BrickingBad brickingBad;
  private CardLayout cardLayout;
  private JPanel contPanel;

  private JCheckBox deleteBlock;
  private JButton saveButton;
  private JButton loadButton;
  private JButton addBricksButton;

  private JFormattedTextField simpleBrickField;
  private JFormattedTextField halfMetalBrickField;
  private JFormattedTextField mineBrickField;
  private JFormattedTextField wrapperBrickField;
  private JLabel simpleBrickLabel;
  private JLabel halfMetalBrickLabel;
  private JLabel mineBrickLabel;
  private JLabel wrapperBrickLabel;
  private NumberFormat numberFormat;

  public MapSetup(BrickingBad brickingBad, CardLayout cardLayout, JPanel contPanel) {
    this.brickingBad = brickingBad;
    this.cardLayout = cardLayout;
    this.contPanel = contPanel;

    this.saveButton = new JButton(Constants.SAVE_BUTTON);
    this.loadButton = new JButton(Constants.LOAD_BUTTON);
    this.addBricksButton = new JButton(Constants.ADD_BRICKS_BUTTON);
    this.deleteBlock = new JCheckBox(Constants.DELETE_BY_CLICK_LABEL);

    loadButton.addActionListener(this);
    saveButton.addActionListener(this);
    deleteBlock.addActionListener(this);
    addBricksButton.addActionListener(this);

    setLayout(null);
    setBackground(new Color(100, 100, 200));
    setPreferredSize(new Dimension(Constants.SIDE_BAR_WIDTH, Constants.SIDE_BAR_LENGTH));
    setFocusable(false);
    setup();
  }

  void setup() {
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

    simpleBrickLabel = new JLabel(Constants.SIMPLE_BRICK_LABEL);
    halfMetalBrickLabel = new JLabel(Constants.HALF_METAL_BRICK_LABEL);
    mineBrickLabel = new JLabel(Constants.MINE_BRICK_LABEL);
    wrapperBrickLabel = new JLabel(Constants.WRAPPER_BRICK_LABEL);

    addBricksButton.setBounds(Constants.POWERUP_X, 20, Constants.POWERUP_WIDTH, Constants.DEFAULT_LENGTH);
    deleteBlock.setBounds(Constants.POWERUP_X,120,Constants.POWERUP_WIDTH,Constants.DEFAULT_LENGTH);

    simpleBrickLabel.setBounds(Constants.POWERUP_X,220,Constants.POWERUP_WIDTH,Constants.DEFAULT_LENGTH);
    simpleBrickField.setBounds(Constants.POWERUP_X,250,Constants.POWERUP_WIDTH,Constants.DEFAULT_LENGTH);

    halfMetalBrickLabel.setBounds(Constants.POWERUP_X,320,Constants.POWERUP_WIDTH,Constants.DEFAULT_LENGTH);
    halfMetalBrickField.setBounds(Constants.POWERUP_X,350,Constants.POWERUP_WIDTH,Constants.DEFAULT_LENGTH);

    mineBrickLabel.setBounds(Constants.POWERUP_X,420,Constants.POWERUP_WIDTH,Constants.DEFAULT_LENGTH);
    mineBrickField.setBounds(Constants.POWERUP_X,450,Constants.POWERUP_WIDTH,Constants.DEFAULT_LENGTH);

    wrapperBrickLabel.setBounds(Constants.POWERUP_X,520,Constants.POWERUP_WIDTH,Constants.DEFAULT_LENGTH);
    wrapperBrickField.setBounds(Constants.POWERUP_X,550,Constants.POWERUP_WIDTH,Constants.DEFAULT_LENGTH);

    saveButton.setBounds(Constants.POWERUP_X,720,Constants.POWERUP_WIDTH,Constants.DEFAULT_LENGTH);
    loadButton.setBounds(Constants.POWERUP_X,770,Constants.POWERUP_WIDTH,Constants.DEFAULT_LENGTH);

    add(addBricksButton);
    add(deleteBlock);
    add(simpleBrickLabel);
    add(simpleBrickField);
    add(halfMetalBrickLabel);
    add(halfMetalBrickField);
    add(mineBrickLabel);
    add(mineBrickField);
    add(wrapperBrickLabel);
    add(wrapperBrickField);
    add(saveButton);
    add(loadButton);
  }

  @Override
  public void actionPerformed(ActionEvent actionEvent) {
    if (actionEvent.getActionCommand().equals(Constants.SAVE_BUTTON)) {
      MapSavePage savePage = new MapSavePage(brickingBad, cardLayout, contPanel);
      contPanel.add(savePage, Constants.MAP_SAVE_LABEL);
      cardLayout.show(contPanel, Constants.MAP_SAVE_LABEL);
    }
    if (actionEvent.getActionCommand().equals(Constants.LOAD_BUTTON)) {
      MapLoadPage loadPage = new MapLoadPage(brickingBad, cardLayout, contPanel);
      contPanel.add(loadPage, Constants.MAP_LOAD_LABEL);
      cardLayout.show(contPanel, Constants.MAP_LOAD_LABEL);
    }

    if (actionEvent.getActionCommand().equals(Constants.DELETE_BY_CLICK_LABEL)) {
      Brick.setRemoveFlag(deleteBlock.isSelected());
    }
    if (actionEvent.getActionCommand().equals(Constants.ADD_BRICKS_BUTTON)) {

      Object simpleVal = simpleBrickField.getValue();
      if (simpleVal == null) {
        JOptionPane.showMessageDialog(null, Constants.EMPTY_SIMPLE_BRICK_FIELD_WARNING);
      }

      Object halfMetalVal = halfMetalBrickField.getValue();
      if (halfMetalVal == null) {
        JOptionPane.showMessageDialog(null, Constants.EMPTY_HALF_METAL_BRICK_FIELD_WARNING);
        return;
      }

      Object mineVal = mineBrickField.getValue();
      if (mineVal == null) {
        JOptionPane.showMessageDialog(null, Constants.EMPTY_MINE_BRICK_FIELD_WARNING);
        return;
      }

      Object wrapperVal = wrapperBrickField.getValue();
      if (wrapperVal == null) {
        JOptionPane.showMessageDialog(null, Constants.EMPTY_WRAPPER_BRICK_FIELD_WARNING);
        return;
      }

      int simple = ((Number) simpleVal).intValue();
      int halfMetal = ((Number) halfMetalVal).intValue();
      int mineCount = ((Number) mineVal).intValue();
      int wrapper = ((Number) wrapperVal).intValue();
      boolean success = brickingBad.buildMap(simple, halfMetal, mineCount, wrapper);
      if (!success) {
        JOptionPane.showMessageDialog(null, Constants.BRICK_NUMBER_WARNING);
      }
    }
  }
}
