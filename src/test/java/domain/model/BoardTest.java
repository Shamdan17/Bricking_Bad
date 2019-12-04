package domain.model;

import domain.game.Board;
import domain.game.GameData;
import domain.model.brick.Brick;
import domain.model.brick.BrickFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import utils.Position;

import java.util.List;

import static domain.model.SpecificType.SimpleBrick;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class BoardTest {

    private Board board;
    private BrickFactory brickFactory;

    @Test
    void boardConstructorWithNullArgumentShouldThrowException() {
        assertThrows(IllegalArgumentException.class, () -> {
            board = new Board(new GameData(null, null, null));
        });
        assertThrows(IllegalArgumentException.class, () -> {
            board = new Board(null);
        });
        assertThrows(IllegalArgumentException.class, () -> {
            board = new Board(new GameData(null, new Ball(new Position(10, 10), 17), null));
        });
    }

    @BeforeEach
    void setUp() {
        board = new Board();
        brickFactory = new BrickFactory();
    }

    @Test
    void boardConstructorShouldInitializeDefaultMovables() {
        //default movables size = 65
        assertEquals(6, board.getData().getMovables().size());
    }

    @Test
    void checkCollisionsWithCollidedMovablesShouldReturn() {
    }

    @Test
    void animateShouldRemoveDestroyedMovables() {
        assertEquals(6, board.getData().getMovables().size());
        Brick simpleBrick = brickFactory.get(SimpleBrick, new Position(1000, 1000));
        simpleBrick.setDestroyed(true);
        List movableShapes = board.getData().getMovables();
        movableShapes.add(simpleBrick);
        GameData gameData = new GameData(board.getData().getPaddle(), board.getData().getBall(), movableShapes);
        board = new Board(gameData);
        assertEquals(7, board.getData().getMovables().size());
        board.animate();
        assertEquals(6, board.getData().getMovables().size());
    }

    @Test
    void addMovablesShouldAddMovablesIfThereIsNoCollision() {
        assertEquals(6, board.getData().getMovables().size());
        Brick simpleBrick = brickFactory.get(SimpleBrick, new Position(1000, 1000));
        board.addMovable(simpleBrick);
        assertEquals(7, board.getData().getMovables().size());
    }

    @Test
    void addMovablesShouldNotAddMovablesIfThereIsCollision() {
        assertEquals(6, board.getData().getMovables().size());
        Brick simpleBrick = brickFactory.get(SimpleBrick, new Position(20, 130));
        board.addMovable(simpleBrick);
        assertEquals(6, board.getData().getMovables().size());
    }

}
