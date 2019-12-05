package domain;

import domain.account.Account;
import domain.account.AccountManager;
import domain.game.Board;
import domain.game.GameData;
import domain.game.GameSession;
import domain.mapbuild.MapBuildData;
import domain.mapbuild.MapBuildSession;
import domain.model.SpecificType;
import utils.Position;

// BrickingBad is the main controller
public class BrickingBad {

  private AccountManager accountManager;
  private MapBuildSession mapBuildSession;
  private GameSession gameSession;

  public BrickingBad() {
    this.mapBuildSession = new MapBuildSession();
    this.gameSession = new GameSession("demo");
    this.accountManager = new AccountManager();
  }

  public void nextStep() {
    gameSession.nextStep();
  }

  public GameData getGameData() {
    return gameSession.getGameData();
  }

  public MapBuildData getMapBuildData() {

    return mapBuildSession.getData();
  }

  // TODO: this should be updated later to get user name information
  public void saveGame() {
    gameSession.save();
  }

  public void loadGame() {
    gameSession.load();
  }

  // TODO: implement
  public void saveMap() {
    mapBuildSession.save();
  }

  // TODO: implement
  public void loadMap() {}

  public boolean loginAttempt(String username, String password) {
    // username and password must not be null
    if (username == null || password == null) {
      return false;
    }

    Account acc = accountManager.Authenticate(username, password);

    // if account does not exist fail
    if (acc == null) {
      return false;
    }

    // otherwise confirm authintication
    return true;
  }

  public void movePaddleLeft() {
   gameSession.movePaddleLeft();
  }

  public void movePaddleRight() {
   gameSession.movePaddleRight();
  }

  public void rotatePaddleRight() {
    gameSession.rotatePaddleRight();
  }

  public void rotatePaddleLeft() {
    gameSession.rotatePaddleLeft();
  }

  public void removeBrick(Position pos) {
    boolean isRemoved = mapBuildSession.removeBrick(pos);
  }

  public void moveBrick(Position from, Position to) {
    mapBuildSession.moveBrick(from, to);
  }

  public void addBrick(Position pos) {
    boolean isAdded = mapBuildSession.addBrick(SpecificType.SimpleBrick, pos);
  }
}
