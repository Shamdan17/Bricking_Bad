package ui.panels;

import domain.BrickingBad;
import domain.game.GameData;
import domain.model.shape.MovableShape;
import ui.MKeyListener;
import ui.drawables.Drawable;
import ui.drawables.DrawableFactory;
import utils.Constants;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.List;

public class Game extends JPanel implements Runnable, KeyListener, ActionListener {

  boolean exit = false;
  private BrickingBad brickingBad;
  private JPanel contPanel;
  private CardLayout cardLayout;
  private JButton pauseButton;
  private Inventory inventory;
  private JLabel livesLeft;
  private JLabel score;
  private JLabel time;

  public Game(BrickingBad brickingBad, CardLayout cardLayout, JPanel contPanel) {
    this.contPanel = contPanel;
    this.cardLayout = cardLayout;
    this.brickingBad = brickingBad;

    this.livesLeft = new JLabel();
    this.score = new JLabel();
    this.time = new JLabel();

    this.pauseButton = new JButton(Constants.PAUSE_BUTTON);
    this.inventory = new Inventory(brickingBad, cardLayout, contPanel);
    setLayout(null);
    setBackground(new Color(204, 229, 255));
    setSize(Constants.MAX_X, Constants.MAX_Y);

    setup();
    setFocusable(true);
    brickingBad.startGame();
    (new Thread(this)).start();
  }

  void setup() {
    addKeyListener(new MKeyListener(brickingBad));
    pauseButton.setBounds(0, 0, Constants.PAUSE_BUTTON_WIDTH, Constants.PAUSE_BUTTON_LENGTH);
    inventory.setBounds(
        Constants.SIDE_BAR_X, 0, Constants.SIDE_BAR_WIDTH, Constants.SIDE_BAR_LENGTH);
    pauseButton.addActionListener(this);

    livesLeft.setBounds(
        Constants.LIVES_LEFT_X, 0, Constants.DEFAULT_WIDTH, Constants.DEFAULT_LENGTH);
    score.setBounds(Constants.SCORE_X, 0, Constants.DEFAULT_WIDTH, Constants.DEFAULT_LENGTH);
    time.setBounds(Constants.TIME_LEFT_X, 0, Constants.DEFAULT_WIDTH, Constants.DEFAULT_LENGTH);

    livesLeft.setFont(Constants.DEFAULT_FONT);
    score.setFont(Constants.DEFAULT_FONT);
    time.setFont(Constants.DEFAULT_FONT);

    add(pauseButton);
    add(time);
    add(score);
    add(livesLeft);
    add(inventory, BorderLayout.EAST);
  }

  public void run() {
    while (!exit) {
      try {
        repaint();
        Thread.sleep(Constants.SLEEP_TIME);
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
  }

  public void paintComponent(Graphics g) {
    this.requestFocus();
    brickingBad.nextStep();
    super.paintComponent(g);
    GameData gameData = brickingBad.getGameData();
    if(gameData.isGameOver()){
        terminateGame();
        goToMenu();
        exit = true;
        return;
    }
    inventory.updatePowerups(gameData.getPowerupList(), gameData.getLaserCount());
    updateLives(gameData.getRemainingLives());
    updateScore(gameData.getScore());
    updateTime(gameData.getGameTime());
    List<MovableShape> drawables = gameData.getMovables();
    for (MovableShape ms : drawables) {
      Drawable d = DrawableFactory.get(ms, brickingBad);
      d.draw(g);
    }

  }

  private void terminateGame() {
    EventQueue.invokeLater(
        new Runnable() {
          @Override
          public void run() {
            JOptionPane.showMessageDialog(null, "Game Over! you shall return to main menu now!");
          }
        });
  }

  private void goToMenu() {
    EventQueue.invokeLater(
        new Runnable() {
          @Override
          public void run() {
            cardLayout.show(contPanel, Constants.MENU_LABEL);
          }
        });
  }

  private void updateScore(double newScore) {
    score.setText("Score: " + newScore);
  }

  private void updateTime(long newTime) {
    time.setText("Time Elapsed: " + newTime);
  }

  private void updateLives(int lives) {
    livesLeft.setText(lives + " Lives Left");
  }

  @Override
  public void actionPerformed(ActionEvent actionEvent) {
    if (actionEvent.getActionCommand().equals(Constants.PAUSE_BUTTON)) {
      PauseMenu pauseMenu = new PauseMenu(brickingBad, cardLayout, contPanel);
      contPanel.add(pauseMenu, Constants.PAUSE_LABEL);
      cardLayout.show(contPanel, Constants.PAUSE_LABEL);
    }
  }

  @Override
  public void keyTyped(KeyEvent keyEvent) {}

  @Override
  public void keyPressed(KeyEvent keyEvent) {}

  @Override
  public void keyReleased(KeyEvent keyEvent) {}
}
