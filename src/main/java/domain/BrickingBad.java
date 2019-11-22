package domain;

import domain.account.Account;
import domain.account.AccountManager;
import domain.model.Board;
import domain.model.shape.MovableShape;

import java.util.List;

//BrickingBad is the main controller
public class BrickingBad {

    private AccountManager accountManager;
    private Board board;

    public BrickingBad() {
        this.board = new Board();
        this.accountManager = new AccountManager();
    }

    public void animate() {
        this.board.advance();
    }

    public List<MovableShape> getMovables() {
        return this.board.getMovables();
    }

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
}
