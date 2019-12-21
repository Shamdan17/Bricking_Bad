package ui;

import domain.BrickingBad;
import domain.game.GameData;
import domain.model.shape.MovableShape;
import ui.bricks.HalfMetalBrick;
import ui.bricks.MineBrick;
import ui.bricks.SimpleBrick;
import ui.bricks.WrapperBrick;
import utils.Constants;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.List;

public class GamePanel extends JPanel implements Runnable, KeyListener {

  public JButton backToMain;
  private BrickingBad brickingBad;
  private JButton saveButton;
  private JButton loadButton;


  public GamePanel(BrickingBad bb) {
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
        bb.saveGame();
        saveButton.setFocusable(false);
        loadButton.setFocusable(false);
      }
    });

    loadButton.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent actionEvent) {
        bb.loadGame();
        loadButton.setFocusable(false);
        saveButton.setFocusable(false);
      }
    });
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
    Paddle paddle = new Paddle(gameData.getPaddle(), brickingBad);
    List<MovableShape> drawables = gameData.getMovables();

    for (MovableShape ms : drawables) {
      Drawable d = getDrawable(ms);
      d.draw(g);
    }
  }

  public Drawable getDrawable(MovableShape ms) {
    switch (ms.getSpecificType()) {
      case Paddle:
        return new Paddle(ms, brickingBad);
      case Ball:
        return new Ball(ms);
      case Laser:
        return new SimpleBrick(ms, brickingBad);
      case SimpleBrick:
        return new SimpleBrick(ms, brickingBad);
      case MineBrick:
        return new MineBrick(ms, brickingBad);
      case HalfMetalBrick:
        return new HalfMetalBrick(ms, brickingBad);
      case WrapperBrick:
        return new WrapperBrick(ms, brickingBad);
      default:
          return new SimpleBrick(ms, brickingBad);
    }

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
