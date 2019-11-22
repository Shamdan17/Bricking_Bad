package domain;

import domain.account.Account;
import domain.account.AccountManager;
import domain.model.Board;
import domain.model.MapEditor;
import domain.model.Movable;
import domain.model.shape.MovableShape;
import utils.Constants;
import utils.Position;
import domain.model.shape.MovableShape;

import java.util.ArrayList;
import java.util.List;

//BrickingBad is the main controller
public class BrickingBad {

  private AccountManager accountManager;
  private MapEditor mapEditor;
  private Board board;

  public BrickingBad() {
    this.board = new Board();
    this.mapEditor = new MapEditor();
    this.accountManager = new AccountManager();
  }

  public void animate() {
    this.board.animate();
  }

  public List<MovableShape> getGameMovables() {
    return this.board.getMovables();
  }
  public List<MovableShape> getMapEditorMovables(){
    return mapEditor.getMovables();
  }

  public boolean loginAttempt(String username, String password){
    // username and password must not be null
    if(username == null || password == null) {
      return false;
    }

    Account acc = accountManager.Authenticate(username, password);

    // if account does not exist fail
    if(acc == null){
      return false;
    }

    // otherwise confirm authintication
    return true;
  }


  // TODO: implment save map in approporiate place
  public void saveMap() {

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

  public void removeBrick(Position pos ){
      boolean isRemoved = mapEditor.removeBrick(pos);

  }

  public void moveBrick(Position from, Position to){
    mapEditor.moveBrick(from,to);
  }

  public void addBrick(Position pos){
    boolean isAdded = mapEditor.addBrick(Constants.SimpleBrick ,pos);
  }
}
