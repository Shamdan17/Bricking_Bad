package domain.model;

import domain.mapbuild.MapBuildSession;
import domain.model.brick.Brick;
import domain.model.brick.BrickFactory;
import domain.model.shape.MovableShape;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import utils.Position;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static domain.model.SpecificType.SimpleBrick;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class MapEditorTest {

    private MapBuildSession mapEditor;
    private BrickFactory brickFactory;

    @BeforeEach
    void setUp() {
        mapEditor = new MapBuildSession("ssd");
        brickFactory = new BrickFactory();
    }

    @Test
    void addNotSupportedBrickTypeShouldReturnFalse() {
        assertFalse(mapEditor.addBrick(null, new Position(100, 100)));
    }

    @Test
    void addBrickToNegativePositionShouldReturnFalse() {
        assertFalse(mapEditor.addBrick(SimpleBrick, new Position(-10, -10)));
    }

    @Test
    void addSimpleBrickToAppropriatePositionShouldReturnTrue() {
        assertTrue(mapEditor.addBrick(SimpleBrick, new Position(10, 10)));
    }

    @Test
    void removeExistingBrickFromMapShouldShouldReturnTrue() {
        mapEditor.addBrick(SimpleBrick, new Position(10, 10));
        Brick brick = (Brick) mapEditor.getData().getMovables().get(0);
        assertTrue(mapEditor.removeBrick(brick.getID()));
    }

    @Test
    void removeNonExistingBrickFromMapShouldReturnFalse() {
        UUID id = UUID.randomUUID();
        assertFalse(mapEditor.removeBrick(id));
    }

    @Test
    void moveBrickCausingCollisionShouldReturnFalse() {
        mapEditor.addBrick(SimpleBrick, new Position(10, 10));
        mapEditor.addBrick(SimpleBrick, new Position(50, 50));
        List<MovableShape> list = mapEditor.getData().getMovables();
        Brick brick1 = (Brick) list.get(0);
        Brick brick2 = (Brick) list.get(1);
        assertFalse(mapEditor.moveBrick(brick1.getID(), brick2.getPosition()));

    }

    @Test
    void moveBrickToEmptyPositionShouldReturnTrue() {
        mapEditor.addBrick(SimpleBrick, new Position(500, 500));
        mapEditor.addBrick(SimpleBrick, new Position(600, 600));
        List<MovableShape> list = mapEditor.getData().getMovables();
        Brick brick1 = (Brick) list.get(0);
        Brick brick2 = (Brick) list.get(1);
        assertTrue(mapEditor.moveBrick(brick1.getID(), new Position(200, 200)));
    }

    @Test
    void testGetData() {
        List<MovableShape> list = new ArrayList<>();
        for (int i = 0; i < 3; ++i) {
            Position pos = new Position(50 + 50 * i, 50 + 50 * i);
            mapEditor.addBrick(SimpleBrick, pos);
            list.add(brickFactory.get(SimpleBrick, pos));
        }
        List<MovableShape> testedList = mapEditor.getData().getMovables();
        for (int i = 0; i < 3; ++i) {
            assertTrue(list.get(i).equals(testedList.get(i)));
        }

    }

}
