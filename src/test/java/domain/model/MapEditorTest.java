package domain.model;

import domain.mapbuild.MapBuildSession;
import domain.model.brick.BrickFactory;
import domain.model.shape.MovableShape;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import utils.Position;

import java.util.ArrayList;
import java.util.List;

import static domain.model.SpecificType.SimpleBrick;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class MapEditorTest {

    private MapBuildSession mapEditor;
    private BrickFactory brickFactory;
    @BeforeEach
    void setUp() {
        mapEditor = new MapBuildSession();
        brickFactory = new BrickFactory();
    }

    @Test
    void addNotSupportedBrickTypeShouldReturnFalse() {
        assertFalse(mapEditor.addBrick(null, new Position(100,100)));
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
        assertTrue(mapEditor.removeBrick(new Position(10, 10)));
    }

    @Test
    void removeNonExistingBrickFromMapShouldReturnFalse() {
        assertFalse(mapEditor.removeBrick(new Position(100, 100)));
    }

    @Test
    void moveBrickCausingCollisionShouldReturnFalse() {
        mapEditor.addBrick(SimpleBrick, new Position(10, 10));
        mapEditor.addBrick(SimpleBrick, new Position(50, 50));
        assertFalse(mapEditor.moveBrick(new Position(50, 50), new Position(10, 10)));

    }

    @Test
    void moveBrickToEmptyPositionShouldReturnTrue() {
        mapEditor.addBrick(SimpleBrick, new Position(10, 10));
        mapEditor.addBrick(SimpleBrick, new Position(50, 50));
        assertTrue(mapEditor.moveBrick(new Position(50, 50), new Position(60, 50)));
    }

    @Test
    void testGetData(){
        List<MovableShape> list = new ArrayList<>();
        for(int i=0 ; i<3 ; ++i){
            Position pos = new Position(50 + 50 * i,50 + 50 * i);
            mapEditor.addBrick(SimpleBrick, pos);
            list.add(brickFactory.get(SimpleBrick,pos));
        }
        List<MovableShape> testedList = mapEditor.getData().getMovables();
        for(int i=0 ; i<3 ; ++i){
            assertTrue(list.get(i).equals(testedList.get(i)));
        }

    }

}
