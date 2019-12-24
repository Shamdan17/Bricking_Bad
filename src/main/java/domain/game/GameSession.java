package domain.game;

import domain.mapbuild.MapBuildData;
import domain.model.shape.MovableShape;
import domain.storage.StorageManager;
import domain.storage.StorageManagerFactory;

import java.time.Instant;
import java.util.List;
import java.util.ArrayList;

public class GameSession {

    private String username;
    private StorageManager sm;
    private Board board;
    private String unixTimestamp;

    public GameSession(String username) {
        if (username == null) {
            throw new IllegalArgumentException();
        }
        this.username = username;
        this.sm = StorageManagerFactory.get(username + "games");
        this.board = new Board();
    }

    /**
     * This function launches the next step of movement for objects in the board
     */
    public void nextStep() {
        board.animate();
    }

    public void loadMap(MapBuildData data){
        board = new Board(data);
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
     * shoots a laser from the paddle
     */
    public void shootLaser() {
        board.shootLaser();
    }

    public void activateTallerPaddle() {
        board.activateTallerPaddle();
    }

    public void activateMagnet() {
        board.activateMagnet();
    }

    public void activateChemicalBall() {
        board.activateChemicalBall();
    }

    public void throwBall() {
        board.releaseBall();
    }

    /**
     * saves current game
     */
    public void save() {
        GameData data = board.getData();
        unixTimestamp = String.valueOf(Instant.now().getEpochSecond());
        sm.put(unixTimestamp, data);
    }

    /**
     * saves current game with name
     */
    public void save(String name) {
        GameData data = board.getDataCopy();
        sm.put(name, data);
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

    /**
     * loads previously saved game with name
     */
    public void load(String name) {
        if (sm.get(name) == null) {
            return;
        }

        GameData data = (GameData) sm.get(name);
        board = new Board(data);
    }

    /**
     * loads previously saved game with name
     */
    public List<String> getGamesList() {
        return new ArrayList<>(sm.keySet());
    }
}
