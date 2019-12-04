package domain.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import utils.Constants;
import utils.Position;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class MapEditorTest {

    private MapEditor mapEditor;

    @BeforeEach
    void setUp() {
        mapEditor = new MapEditor();
    }

    @Test
    void addNotSupportedBrickTypeShouldReturnFalse() {
        assertFalse(mapEditor.addBrick("unsupported", new Position(10, 10)));
    }

    @Test
    void addBrickToNegativePositionShouldReturnFalse() {
        assertFalse(mapEditor.addBrick(Constants.SimpleBrick, new Position(-10, -10)));
    }

    @Test
    void addSimpleBrickToAppropriatePositionShouldReturnTrue() {
        assertTrue(mapEditor.addBrick(Constants.SimpleBrick, new Position(10, 10)));
    }

    @Test
    void removeExistingBrickFromMapShouldShouldReturnTrue() {
        mapEditor.addBrick(Constants.SimpleBrick, new Position(10, 10));
        assertTrue(mapEditor.removeBrick(new Position(10, 10)));
    }

    @Test
    void removeNonExistingBrickFromMapShouldReturnFalse() {
        assertFalse(mapEditor.removeBrick(new Position(100, 100)));
    }

    @Test
    void moveBrickCausingCollisionShouldReturnFalse() {
        mapEditor.addBrick(Constants.SimpleBrick, new Position(10, 10));
        mapEditor.addBrick(Constants.SimpleBrick, new Position(50, 50));
        assertFalse(mapEditor.moveBrick(new Position(50, 50), new Position(10, 10)));

    }

    @Test
    void moveBrickToEmptyPositionShouldReturnTrue() {
        mapEditor.addBrick(Constants.SimpleBrick, new Position(10, 10));
        mapEditor.addBrick(Constants.SimpleBrick, new Position(50, 50));
        assertTrue(mapEditor.moveBrick(new Position(50, 50), new Position(60, 50)));
    }

}
