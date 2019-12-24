package ui.panels;

import domain.BrickingBad;
import domain.mapbuild.MapBuildData;
import domain.model.shape.MovableShape;
import ui.drawables.Drawable;
import ui.drawables.DrawableFactory;
import ui.drawables.bricks.Brick;
import utils.Constants;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.List;
import java.util.*;

public class MapBuild extends JPanel implements Runnable, ActionListener {

  public JButton menuButton;
  public JButton startGameButton;
  private BrickingBad brickingBad;
  private Game gamePanel;
  private CardLayout cardLayout;
  private JPanel contPanel;
  private Map<UUID, Drawable> drawables;
  private MapSetup mapSetup;

  public MapBuild(BrickingBad brickingBad, CardLayout cardLayout, JPanel contPanel) {
    this.drawables = new HashMap<>();
    this.brickingBad = brickingBad;
    this.menuButton = new JButton(Constants.MENU_BUTTON);
    this.cardLayout = cardLayout;
    this.contPanel = contPanel;
    this.startGameButton = new JButton(Constants.START_GAME_BUTTON);
    this.mapSetup = new MapSetup(brickingBad, cardLayout, contPanel);

    setBackground(new Color(204, 229, 255));
    setLayout(null);
    setFocusable(true);
    setup();
    setSize(Constants.MAX_X, Constants.MAX_Y);
    brickingBad.initializeMapBuild();
    (new Thread(this)).start();
  }

  private void setup() {
    mapSetup.setBounds(
        Constants.SIDE_BAR_X, 0, Constants.SIDE_BAR_WIDTH, Constants.SIDE_BAR_LENGTH);
    menuButton.setBounds(
        0,
        0,
        200, Constants.BRICK_UPPER_BOUND);
    startGameButton.setBounds(
        200, 0, 1300, Constants.BRICK_UPPER_BOUND);
    menuButton.addActionListener(this);
    startGameButton.addActionListener(this);

    add(mapSetup);
    add(startGameButton);
    add(menuButton);
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
    drawLines(g);
  }

  private void drawLines(Graphics g) {
    g.setColor(Color.RED);
    g.drawLine(
        0,
        Constants.BRICK_LOWER_BOUND + Constants.BRICK_WIDTH,
        Constants.BRICK_RIGHT_BOUND + Constants.BRICK_LENGTH,
        Constants.BRICK_LOWER_BOUND + Constants.BRICK_WIDTH);
    g.drawLine(
        Constants.BRICK_RIGHT_BOUND + Constants.BRICK_LENGTH,
        Constants.BRICK_UPPER_BOUND,
        Constants.BRICK_RIGHT_BOUND + Constants.BRICK_LENGTH,
        Constants.BRICK_LOWER_BOUND + Constants.BRICK_WIDTH);

    g.drawLine(
        0,
        Constants.BRICK_UPPER_BOUND,
        Constants.BRICK_RIGHT_BOUND + Constants.BRICK_LENGTH,
        Constants.BRICK_UPPER_BOUND);
  }

  public void actionPerformed(ActionEvent e) {
    if (e.getActionCommand().equals(Constants.MENU_BUTTON)) {
      cardLayout.show(contPanel, Constants.MENU_LABEL);
    }
    if (e.getActionCommand().equals(Constants.START_GAME_BUTTON)) {
      if (!brickingBad.validMap()) {
        JOptionPane.showMessageDialog(null, Constants.NOT_VALID_MAP_WARNING);
        return;
      }
      gamePanel = new Game(brickingBad, cardLayout, contPanel);
      contPanel.add(gamePanel, Constants.GAME_LABEL);
      cardLayout.show(contPanel, Constants.GAME_LABEL);
    }
  }
}
