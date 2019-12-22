package domain.model;

import domain.game.Board;
import domain.game.GameData;
import domain.model.brick.Brick;
import domain.model.brick.BrickFactory;
import domain.model.shape.MovableShape;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import utils.Constants;
import utils.Position;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static domain.model.SpecificType.MineBrick;
import static domain.model.SpecificType.SimpleBrick;
import static org.junit.jupiter.api.Assertions.*;

public class BoardTest {

    private Board board;
    private Paddle paddle;
    private Ball ball;
    private BrickFactory brickFactory;

    @Test
    void boardConstructorWithNullArgumentShouldThrowException() {
        assertThrows(
                NullPointerException.class,
                () -> {
                    board = new Board(new GameData(null, Constants.COPY_MODE));
                });
        assertThrows(
                IllegalArgumentException.class,
                () -> {
                    board = new Board((GameData) null);
                });
    }

    @BeforeEach
    void setUp() {
        brickFactory = new BrickFactory();
        paddle = new Paddle(new Position(300, 700));
        ball = new Ball(new Position(310, 300), Constants.BALL_DIAMETER / 2);
    }

    @Test
    void checkCollisionsWithCollidedMovablesShouldReturn() {
    }

    @Test
    void removeDestroyedMovables()
            throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        List<MovableShape> movables = new ArrayList<>();
        movables.add(paddle);
        for (int i = 0; i < 3; ++i) {
            Brick brick = brickFactory.get(SimpleBrick, new Position(1, 2));
            if (i % 2 == 0) brick.setDestroyed(true);
            movables.add(brick);
        }
        GameData data = new GameData(movables, true);
        Board board = new Board(data);
        Class cls = board.getClass();
        Method destroyMovables = cls.getDeclaredMethod("removeDestroyedMovables");
        destroyMovables.setAccessible(true);
        destroyMovables.invoke(board);
        assertEquals(2, board.getData().getMovables().size(), "not correct movables size");
    }

    @Test
    void testGetData() {
        List<MovableShape> movables = new ArrayList<>();
        movables.add(paddle);
        for (int i = 0; i < 3; ++i) {
            Brick brick = brickFactory.get(SimpleBrick, new Position(1, 1));
            movables.add(brick);
        }
        GameData data = new GameData(movables, true);
        Board board = new Board(data);

        GameData testedData = board.getData();

        assertTrue(testedData.equals(data), "wrong paddle returned");
    }

    @Test
    void testMoveAllMovables()
            throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        List<MovableShape> movables = new ArrayList<>();
        List<MovableShape> list = new ArrayList<>();
        Random rand = new Random();
        for (int i = 0; i < 3; ++i) {
            int x = 50 + rand.nextInt(100);
            int y = 50 + rand.nextInt(100);
            Brick brick = brickFactory.get(MineBrick, new Position(x, y));
            movables.add(brick.copy());
            list.add(brick);
        }
        GameData data = new GameData(movables, true);
        Board board = new Board(data);

        Class cls = board.getClass();
        Method moveAllMovables = cls.getDeclaredMethod("moveAllMovables");
        moveAllMovables.setAccessible(true);
        moveAllMovables.invoke(board);

        List<MovableShape> testedList = board.getData().getMovables();

        for (int i = 0; i < list.size(); ++i) {
            list.get(i).move();
            assertTrue(list.get(i).equals(testedList.get(i)));
        }
    }

    @Test
    void testPaddleMovement() {
        List<MovableShape> movables = new ArrayList<>();
        movables.add(paddle);
        movables.add(ball);
        GameData data = new GameData(movables, true, 0, 1, 0, 1);
        Board board = new Board(data);

        board.movePaddleLeft();
        board.animate();
        paddle.moveLeft();
        paddle.move();

        assertTrue(paddle.equals(board.getPaddle()));

        board.movePaddleRight();
        board.animate();
        paddle.moveRight();
        paddle.move();
        assertTrue(paddle.equals(board.getPaddle()));

        board.rotatePaddleLeft();
        board.animate();
        paddle.rotateLeft();
        paddle.move();
        assertTrue(paddle.equals(board.getPaddle()));

        board.rotatePaddleRight();
        board.animate();
        paddle.rotateRight();
        paddle.move();
        assertTrue(paddle.equals(board.getPaddle()));


        board.movePaddleRight();
        board.animate();
        board.movePaddleRight();
        board.animate();
        paddle.moveRight();
        paddle.move();

        assertFalse(paddle.equals(board.getPaddle()));

    }
}
