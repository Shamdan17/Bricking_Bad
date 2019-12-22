package ui.panels;

import domain.BrickingBad;
import domain.game.GameData;
import domain.model.shape.MovableShape;
import ui.MKeyListener;
import ui.PauseMenu;
import ui.drawables.*;
import ui.drawables.bricks.HalfMetalBrick;
import ui.drawables.bricks.MineBrick;
import ui.drawables.bricks.SimpleBrick;
import ui.drawables.bricks.WrapperBrick;
import ui.load.GameLoadPage;
import ui.save.GameSavePage;
import utils.Constants;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.List;

public class Game extends JPanel implements Runnable, KeyListener, ActionListener {

  private BrickingBad brickingBad;
  private JPanel contPanel;
  private CardLayout cardLayout;
  private JButton pauseButton;

  public Game(BrickingBad brickingBad, CardLayout cardLayout, JPanel contPanel) {
    this.contPanel = contPanel;
    this.cardLayout = cardLayout;
    this.brickingBad = brickingBad;


    this.pauseButton = new JButton(Constants.PAUSE_BUTTON);

    pauseButton.addActionListener(this);

    this.add(pauseButton);
    this.addKeyListener(new MKeyListener(brickingBad));

    setBackground(new Color(204, 229, 255));
    setSize(Constants.FRAME_WIDTH, Constants.FRAME_HEIGHT);

    brickingBad.startGame();

    (new Thread(this)).start();
  }

  public void run() {
    while (true) {
      try {
        repaint();
        Thread.sleep(Constants.SLEEP_TIME);
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
  }

  public void paintComponent(Graphics g) {
    brickingBad.nextStep();
    super.paintComponent(g);
    GameData gameData = brickingBad.getGameData();
    List<MovableShape> drawables = gameData.getMovables();
    for (MovableShape ms : drawables) {
      Drawable d = DrawableFactory.get(ms,brickingBad);
      d.draw(g);
    }
  }

  @Override
  public void actionPerformed(ActionEvent actionEvent) {
    if (actionEvent.getActionCommand().equals(Constants.PAUSE_BUTTON)) {
        PauseMenu pauseMenu = new PauseMenu(brickingBad,cardLayout,contPanel);
        contPanel.add(pauseMenu,Constants.PAUSE_LABEL);
        cardLayout.show(contPanel,Constants.PAUSE_LABEL);
    }
  }

  @Override
  public void keyTyped(KeyEvent keyEvent) {}

  @Override
  public void keyPressed(KeyEvent keyEvent) {}

  @Override
  public void keyReleased(KeyEvent keyEvent) {}
}
