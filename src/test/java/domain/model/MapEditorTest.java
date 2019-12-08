package domain.model;

import domain.mapbuild.MapBuildSession;
import jdk.nashorn.internal.ir.annotations.Ignore;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import utils.Constants;
import utils.Position;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class MapEditorTest {

    private MapBuildSession mapEditor;

    @BeforeEach
    void setUp() {
        mapEditor = new MapBuildSession();
    }

    @Ignore
    void addNotSupportedBrickTypeShouldReturnFalse() {

    }

    @Test
    void addBrickToNegativePositionShouldReturnFalse() {
        assertFalse(mapEditor.addBrick(SpecificType.SimpleBrick, new Position(-10, -10)));
    }

    @Test
    void addSimpleBrickToAppropriatePositionShouldReturnTrue() {
        assertTrue(mapEditor.addBrick(SpecificType.SimpleBrick, new Position(10, 10)));
    }

    @Test
    void removeExistingBrickFromMapShouldShouldReturnTrue() {
        mapEditor.addBrick(SpecificType.SimpleBrick, new Position(10, 10));
        assertTrue(mapEditor.removeBrick(new Position(10, 10)));
    }

    @Test
    void removeNonExistingBrickFromMapShouldReturnFalse() {
        assertFalse(mapEditor.removeBrick(new Position(100, 100)));
    }

    @Test
    void moveBrickCausingCollisionShouldReturnFalse() {
        mapEditor.addBrick(SpecificType.SimpleBrick, new Position(10, 10));
        mapEditor.addBrick(SpecificType.SimpleBrick, new Position(50, 50));
        assertFalse(mapEditor.moveBrick(new Position(50, 50), new Position(10, 10)));

    }

    @Test
    void moveBrickToEmptyPositionShouldReturnTrue() {
        mapEditor.addBrick(SpecificType.SimpleBrick, new Position(10, 10));
        mapEditor.addBrick(SpecificType.SimpleBrick, new Position(50, 50));
        assertTrue(mapEditor.moveBrick(new Position(50, 50), new Position(60, 50)));
    }

}
