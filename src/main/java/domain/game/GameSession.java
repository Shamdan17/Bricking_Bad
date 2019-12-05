package domain.game;

import domain.storage.BinaryStorage;
import domain.storage.StorageManager;

import java.time.Instant;

public class GameSession {

  private String username;
  private StorageManager sm;
  private Board board;
  private long unixTimestamp;

  public GameSession(String username) {
    if (username == null) {
      throw new IllegalArgumentException();
    }
    this.username = username;
    this.sm = new BinaryStorage(username);
    this.board = new Board();
  }

  public void nextStep() {
    board.animate();
  }

  public GameData getGameData() {

    return board.getData();
  }

  public void movePaddleLeft() {
    board.movePaddleLeft();
  }

  public void movePaddleRight() {
    board.movePaddleRight();
  }

  public void rotatePaddleRight() {
    board.rotatePaddleRight();
  }

  public void rotatePaddleLeft() {
    board.rotatePaddleLeft();
  }

  public void save() {
    GameData data = board.getData();
    unixTimestamp = Instant.now().getEpochSecond();
    sm.put(unixTimestamp, data);
  }

  public void load() {
    if (sm.get(unixTimestamp) == null) {
      return;
    }

    GameData data = (GameData) sm.get(unixTimestamp);
    board = new Board(data);
  }
}
