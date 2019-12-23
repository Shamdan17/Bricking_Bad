package domain.game;

import domain.model.powerup.PowerUp;
import domain.model.shape.MovableShape;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class GameData implements Serializable {

  private static final long serialVersionUID = 1L;
  // This class wraps all information that can be saved or sent to UI
  private final List<MovableShape> movables;
  boolean isCopy;
  private double score;
  private int remainingLives;
  private long gameTime;
  private long maxGameTime;
  private List<PowerUp> powerupList;
  private int laserCount;
  private boolean gameOver;
  private boolean win;
  public GameData(List<MovableShape> ms, boolean isCopy) {
    this.movables = ms;
    this.isCopy = isCopy;
  }

  public GameData(
      List<MovableShape> ms,
      List<PowerUp> powerupList,
      boolean isCopy,
      double score,
      int remainingLives,
      long gameTime,
      long maxGameTime,
      int laserCount,
      boolean gameOver,
      boolean win) {

    this.movables = ms;
    this.powerupList = powerupList;
    this.isCopy = isCopy;
    this.score = score;
    this.remainingLives = remainingLives;
    this.gameTime = gameTime;
    this.maxGameTime = maxGameTime;
    this.laserCount = laserCount;
    this.gameOver = gameOver;
    this.win = win;
  }

  public List<MovableShape> getMovables() {
    if (!isCopy) return movables;
    List<MovableShape> list = new ArrayList<>();
    for (MovableShape ms : movables) list.add(ms.copy());
    return list;
  }

  public double getScore() {
    return score;
  }

  public int getRemainingLives() {
    return remainingLives;
  }

  public long getGameTime() {
    return gameTime;
  }

  public long getMaxGameTime() {
    return maxGameTime;
  }

  public List<PowerUp> getPowerupList() {
    return powerupList;
  }

  public int getLaserCount() {
    return laserCount;
  }

  public boolean isGameOver() {
    return gameOver;
  }

  public boolean isWin(){
      return win;
  }
  public boolean equals(GameData data) {
    if (movables.size() != data.getMovables().size()) return false;
    for (int i = 0; i < movables.size(); ++i)
      if (!movables.get(i).equals(data.getMovables().get(i))) return false;
    return true;
  }
}
