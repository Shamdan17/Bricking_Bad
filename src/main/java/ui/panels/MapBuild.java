package ui.panels;

import domain.BrickingBad;
import domain.mapbuild.MapBuildData;
import domain.model.shape.MovableShape;
import ui.drawables.Drawable;
import ui.drawables.DrawableFactory;
import ui.drawables.bricks.Brick;
import ui.load.MapLoadPage;
import ui.save.MapSavePage;
import utils.Constants;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.text.NumberFormat;
import java.util.List;
import java.util.*;

public class MapBuild extends JPanel implements Runnable, ActionListener {

  public JButton menuButton;
  public JButton startGameButton;
  private BrickingBad brickingBad;
  private Game gamePanel;
  private CardLayout cardLayout;
  private JPanel contPanel;
  private JPanel bottomPanel;
  private JCheckBox deleteBlock;
  private JButton saveButton;
  private JButton loadButton;
  private JButton addBricksButton;
  private Map<UUID, Drawable> drawables;

  private MapSavePage savePage;
  private MapLoadPage loadPage;

  private JFormattedTextField simpleBrickField;
  private JFormattedTextField halfMetalBrickField;
  private JFormattedTextField mineBrickField;
  private JFormattedTextField wrapperBrickField;
  private JLabel simpleBrickLabel;
  private JLabel halfMetalBrickLabel;
  private JLabel mineBrickLabel;
  private JLabel wrapperBrickLabel;
  private NumberFormat numberFormat;

  public MapBuild(BrickingBad brickingBad, CardLayout cardLayout, JPanel contPanel) {
    this.drawables = new HashMap<>();
    this.menuButton = new JButton(Constants.MENU_BUTTON);
    this.startGameButton = new JButton(Constants.START_GAME_BUTTON);
    this.saveButton = new JButton(Constants.SAVE_BUTTON);
    this.loadButton = new JButton(Constants.LOAD_BUTTON);
    this.addBricksButton = new JButton(Constants.ADD_BRICKS_BUTTON);
    this.deleteBlock = new JCheckBox(Constants.DELETE_BY_CLICK_LABEL);
    this.brickingBad = brickingBad;
    this.cardLayout = cardLayout;
    this.contPanel = contPanel;

    menuButton.addActionListener(this);
    loadButton.addActionListener(this);
    saveButton.addActionListener(this);
    deleteBlock.addActionListener(this);
    addBricksButton.addActionListener(this);
    startGameButton.addActionListener(this);

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

    simpleBrickLabel = new JLabel(Constants.SIMPLE_BRICK_LABEL);
    halfMetalBrickLabel = new JLabel(Constants.HALF_METAL_BRICK_LABEL);
    mineBrickLabel = new JLabel(Constants.MINE_BRICK_LABEL);
    wrapperBrickLabel = new JLabel(Constants.WRAPPER_BRICK_LABEL);

    bottomPanel.add(simpleBrickLabel);
    bottomPanel.add(simpleBrickField);

    bottomPanel.add(halfMetalBrickLabel);
    bottomPanel.add(halfMetalBrickField);

    bottomPanel.add(mineBrickLabel);
    bottomPanel.add(mineBrickField);

    bottomPanel.add(wrapperBrickLabel);
    bottomPanel.add(wrapperBrickField);

    setLayout(new FlowLayout());
    this.add(startGameButton);
    this.add(addBricksButton);
    this.add(bottomPanel);
    this.add(menuButton);
    this.add(saveButton);
    this.add(loadButton);
    this.add(deleteBlock);

    brickingBad.initializeMapBuild();
    (new Thread(this)).start();
  }

  public void run() {
    while (true) {
      try {
        repaint();
        Thread.sleep(50);
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
  }

  public void paintComponent(Graphics g) {
    super.paintComponent(g);
    MouseListener[] list = this.getMouseListeners();
    for (int i = 0; i < list.length; ++i) {
      this.removeMouseListener(list[i]);
    }
    MouseMotionListener[] motionListeners = this.getMouseMotionListeners();
    for (int i = 0; i < motionListeners.length; ++i) {
      this.removeMouseMotionListener(motionListeners[i]);
    }
    MapBuildData mapBuildData = brickingBad.getMapBuildData();
    Map<UUID, MovableShape> IDMap = mapBuildData.getMovablesIDMap();
    List<MovableShape> movables = mapBuildData.getMovables();
    List<UUID> removables = new ArrayList<>();

    for (UUID ID : drawables.keySet()) {
      if (!IDMap.containsKey(ID)) removables.add(ID);
    }
    for (UUID ID : removables) {
      drawables.remove(ID);
    }

    for (MovableShape ms : movables) {
      if (drawables.containsKey(ms.getID())) {
        Brick d = (Brick) drawables.get(ms.getID());
        d.setMovable(ms);
        this.addMouseListener(d);
        this.addMouseMotionListener(d);
        d.draw(g);
      } else {
        Drawable d = DrawableFactory.get(ms, brickingBad);
        drawables.put(ms.getID(), d);
        d.draw(g);
        this.addMouseListener((Brick) d);
        this.addMouseMotionListener((Brick) d);
      }
    }
  }

  public void actionPerformed(ActionEvent e) {
    if (e.getActionCommand().equals(Constants.MENU_BUTTON)) {
      cardLayout.show(contPanel, Constants.MENU_LABEL);
    }
    if (e.getActionCommand().equals(Constants.START_GAME_BUTTON)) {
      gamePanel = new Game(brickingBad, cardLayout, contPanel);
      contPanel.add(gamePanel, Constants.GAME_LABEL);
      cardLayout.show(contPanel, Constants.GAME_LABEL);
    }
    if (e.getActionCommand().equals(Constants.SAVE_BUTTON)) {
      MapSavePage savePage = new MapSavePage(brickingBad, cardLayout, contPanel);
      contPanel.add(savePage, Constants.MAP_SAVE_LABEL);
      cardLayout.show(contPanel, Constants.MAP_SAVE_LABEL);
    }
    if (e.getActionCommand().equals(Constants.LOAD_BUTTON)) {
        MapLoadPage loadPage = new MapLoadPage(brickingBad,cardLayout,contPanel);
        contPanel.add(loadPage,Constants.MAP_LOAD_LABEL);
        cardLayout.show(contPanel,Constants.MAP_LOAD_LABEL);
    }

    if (e.getActionCommand().equals(Constants.DELETE_BY_CLICK_LABEL)) {
      Brick.setRemoveFlag(deleteBlock.isSelected());
    }
    if (e.getActionCommand().equals(Constants.ADD_BRICKS_BUTTON)) {

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
