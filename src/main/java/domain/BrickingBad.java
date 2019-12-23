package domain;

import domain.account.Account;
import domain.account.AccountManager;
import domain.game.GameData;
import domain.game.GameSession;
import domain.mapbuild.MapBuildData;
import domain.mapbuild.MapBuildSession;
import domain.model.SpecificType;
import domain.storage.BinaryStorage;
import utils.Position;

import java.util.List;
import java.util.UUID;

// BrickingBad is the main controller
public class BrickingBad {

    private AccountManager accountManager;
    private MapBuildSession mapBuildSession;
    private GameSession gameSession;

    public BrickingBad() {
        this.mapBuildSession = new MapBuildSession();
        this.gameSession = new GameSession("demo");
        this.accountManager = new AccountManager(new BinaryStorage("account-manager-data"));
    }

    /**
     * launches the next step in the game
     */
    public void nextStep() {
        gameSession.nextStep();
    }

    /**
     * returns game play data
     *
     * @return GameData instance containing all board data
     */
    public GameData getGameData() {
        return gameSession.getGameData();
    }

    /**
     * return map data
     *
     * @return instance of MapBuildData containing map data
     */
    public MapBuildData getMapBuildData() {

        return mapBuildSession.getData();
    }

    public boolean buildMap(int simple,int halfMetal,int mine,int wrapper){
        return mapBuildSession.buildMap(simple,halfMetal,mine,wrapper);
    }

    public void initializeMapBuild(){
        mapBuildSession = new MapBuildSession();
    }

    public void startGame(){
        MapBuildData data = mapBuildSession.getData();
        gameSession.loadMap(data);
    }

    /**
     * saves named game
     */
    public void saveGame(String name) {
        gameSession.save(name);
    }

    /**
     * loads named game
     */
    public void loadGame(String name) {
        gameSession.load(name);
    }

    /**
     * getSavedGames
     */
    public List<String> getGamesList() {
        return gameSession.getGamesList();
    }

    /**
     * saves map
     */
    public void saveMap(String name) {
        mapBuildSession.save(name);
    }

    /**
     * loads map
     */
    public void loadMap(String name) {
        mapBuildSession.load(name);
    }

    public List<String> getMapList(){
        return mapBuildSession.getMapList();
    }

    /**
     * launches an account log in operation
     *
     * @param username username of account
     * @param password password of account
     * @return true if login was successful, or false otherwise
     */
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

    /**
     * move paddle right
     */
    public void movePaddleLeft() {
        gameSession.movePaddleLeft();
    }

    /**
     * move paddle left
     */
    public void movePaddleRight() {
        gameSession.movePaddleRight();
    }

    /**
     * rotate paddle right
     */
    public void rotatePaddleRight() {
        gameSession.rotatePaddleRight();
    }

    /**
     * rotate paddle left
     */
    public void rotatePaddleLeft() {
        gameSession.rotatePaddleLeft();
    }

    public void activateChemicalBall() {
        gameSession.activateChemicalBall();
    }

    public void shootLaser() {
        gameSession.shootLaser();
    }

    public void activateTallerPaddle() {
        gameSession.activateTallerPaddle();
    }

    public void activateMagnet() {
        gameSession.activateMagnet();
    }

    public void throwBall() {
        gameSession.throwBall();
    }

    /**
     * remove a brick from building mode
     *
     * @param ID ID of brick to be removed
     */
    public void removeBrick(UUID ID) {
        boolean isRemoved = mapBuildSession.removeBrick(ID);
    }

    /**
     * changes the location of a brick in build mode
     *
     * @param ID ID of brick to be moved
     * @param to   destination of brick
     */
    public boolean moveBrick(UUID ID, Position to) {
        return mapBuildSession.moveBrick(ID, to);
    }

    public void dragBrick(UUID ID, Position to){
        mapBuildSession.dragBrick(ID,to);
    }

    /**
     * add brick to build mode
     *
     * @param pos position of brick to be added TODO: refactor this to take brick
     *            type
     */
    public void addBrick(Position pos) {
        boolean isAdded = mapBuildSession.addBrick(SpecificType.SimpleBrick, pos);
    }
}
