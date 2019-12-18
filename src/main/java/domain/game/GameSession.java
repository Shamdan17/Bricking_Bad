package domain.game;

import domain.mapbuild.MapBuildData;
import domain.model.shape.MovableShape;
import domain.storage.BinaryStorage;
import domain.storage.StorageManager;

import java.time.Instant;
import java.util.List;

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

    public GameSession(MapBuildData data){
        this.board = new Board(data);
        this.sm = new BinaryStorage("hi");
    }

    /**
     * This function launches the next step of movement for objects in the board
     */
    public void nextStep() {
        board.animate();
    }

    /**
     * This function returns the game play data
     *
     * @return GameData instance containing game play data
     */
    public GameData getGameData() {
        return board.getData();
    }

    /**
     * moves paddle to the left
     */
    public void movePaddleLeft() {
        board.movePaddleLeft();
    }

    /**
     * moves paddle to the right
     */
    public void movePaddleRight() {
        board.movePaddleRight();
    }

    /**
     * rotates paddle to the right
     */
    public void rotatePaddleRight() {
        board.rotatePaddleRight();
    }

    /**
     * rotates paddle to the left
     */
    public void rotatePaddleLeft() {
        board.rotatePaddleLeft();
    }

    /**
     * saves current game
     */
    public void save() {
        GameData data = board.getData();
        unixTimestamp = Instant.now().getEpochSecond();
        sm.put(unixTimestamp, data);
    }

    /**
     * loads previously saved game
     */
    public void load() {
        if (sm.get(unixTimestamp) == null) {
            return;
        }

        GameData data = (GameData) sm.get(unixTimestamp);
        board = new Board(data);
    }
}
