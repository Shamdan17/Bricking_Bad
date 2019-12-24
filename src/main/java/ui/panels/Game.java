package ui.panels;

import domain.BrickingBad;
import domain.game.GameData;
import domain.model.SpecificType;
import domain.model.shape.MovableShape;
import ui.drawables.effects.CachedEffect;
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
import java.util.*;
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
  private Map<UUID,Drawable> drawables;
  private Map<UUID, CachedEffect> cachedEffects;

  private JLabel gameOverLabel;
  private JLabel youWonLabel;

  public Game(BrickingBad brickingBad, CardLayout cardLayout, JPanel contPanel) {
    this.contPanel = contPanel;
    this.cardLayout = cardLayout;
    this.brickingBad = brickingBad;
    this.drawables = new HashMap<>();
    this.cachedEffects = new HashMap<>();
    this.livesLeft = new JLabel();
    this.score = new JLabel();
    this.time = new JLabel();
    this.gameOverLabel = new JLabel("GAME OVER!");
    this.youWonLabel = new JLabel("You Won !");

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

    gameOverLabel.setFont(Constants.VERY_BIG_FONT);
    youWonLabel.setFont(Constants.VERY_BIG_FONT);

    gameOverLabel.setBounds(Constants.VERDICT_X,Constants.VERDICT_Y,Constants.VERDICT_WIDTH,Constants.VERDICT_LENGTH);
    gameOverLabel.setForeground(Color.RED);

    youWonLabel.setBounds(Constants.VERDICT_X,Constants.VERDICT_Y,Constants.VERDICT_WIDTH,Constants.VERDICT_LENGTH);
    youWonLabel.setForeground(Color.GREEN);

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
        add(gameOverLabel);
    }
    if(gameData.isWin()){
        add(youWonLabel);
    }

    Map<UUID, MovableShape> IDMap = gameData.getMovablesIDMap();

    List<MovableShape> movables = gameData.getMovables();
    List<UUID> removables = new ArrayList<>();

    for (UUID ID : drawables.keySet()) {
      if (!IDMap.containsKey(ID)) removables.add(ID);
    }
    for (UUID ID : removables) {
      drawables.remove(ID);
    }
    for (MovableShape ms :movables) {
      Drawable d;
      if (drawables.containsKey(ms.getID())) {
        d = drawables.get(ms.getID());
        d.setMovable(ms);
      } else {
        d = DrawableFactory.get(ms, brickingBad);
        drawables.put(ms.getID(), d);
      }
      d.draw(g);
      if(isEffect(ms) && !cachedEffects.containsKey(ms.getID())){
        cachedEffects.put(ms.getID(),new CachedEffect(d));
      }
    }
    List<UUID> deadCaches = new ArrayList<>();
    for(UUID ID : cachedEffects.keySet()){
      CachedEffect ce = cachedEffects.get(ID);
      ce.hit();
      if(ce.isDead()) deadCaches.add(ID);
      ce.getDrawable().draw(g);
    }

    for(UUID ID : deadCaches)
      cachedEffects.remove(ID);

    inventory.updatePowerups(gameData.getPowerupList(), gameData.getLaserCount());
    updateLives(gameData.getRemainingLives());
    updateScore(gameData.getScore());
    updateTime(gameData.getGameTime());

  }

  public boolean isEffect(MovableShape ms){
    return ms.getSpecificType() == SpecificType.Explosion ||
            ms.getSpecificType() == SpecificType.FireBallExplosion ||
            ms.getSpecificType() == SpecificType.Laser ||
            ms.getSpecificType() == SpecificType.AlienBeam;

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
