package domain.model;

import domain.mapbuild.Map;
import domain.model.brick.Brick;
import domain.model.brick.BrickFactory;
import domain.model.shape.MovableShape;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import utils.Position;
import utils.physics.PhysicsEngine;

import java.util.ArrayList;
import java.util.List;

import static domain.model.SpecificType.SimpleBrick;
import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class MapTest {

    private PhysicsEngine physicsEngine;
    private BrickFactory brickFactory;
    private Map map;

    @BeforeAll
    void setPhysicsEngine() {
        physicsEngine = PhysicsEngine.getInstance();
        brickFactory = new BrickFactory();
    }

    @BeforeEach
    void setMap() {
        map = new Map();
    }

    @Test
    void mapConstructorShouldInitializeObjectContainer() {
        assertEquals(0, map.getMovables().size());
    }

    @Test
    void movableShapeShouldBeAddedIfThereIsNoCollision() {
        Brick simpleBrick = brickFactory.get(SimpleBrick, new Position(10, 10));
        map.add(simpleBrick, new Position(100, 100));
        assertEquals(1, map.getMovables().size());
        simpleBrick = brickFactory.get(SimpleBrick, new Position(100, 100));
        assertTrue(map.add(simpleBrick, new Position(100, 100)));
        assertEquals(2, map.getMovables().size());
    }

    @Test
    void movableShapeShouldNotBeAddedIfThereIsNoCollision() {
        map.add(brickFactory.get(SimpleBrick, new Position(10, 10)), new Position(10, 10));
        assertEquals(1, map.getMovables().size());
        assertFalse(map.add(brickFactory.get(SimpleBrick, new Position(11, 11)), new Position(11, 11)));
        assertEquals(1, map.getMovables().size());
    }

    @Test
    void existingMovableShapeShouldBeRemoved() {
        map.add(brickFactory.get(SimpleBrick, new Position(10, 10)), new Position(10, 10));
        assertEquals(1, map.getMovables().size());
        assertTrue(map.remove(new Position(10, 10)));
        assertEquals(0, map.getMovables().size());
    }

    @Test
    void nonExistingMovableShapeShouldNotChangeMovablesContainer() {
        map.add(brickFactory.get(SimpleBrick, new Position(10, 10)), new Position(10, 10));
        assertEquals(1, map.getMovables().size());
        assertFalse(map.remove(new Position(100, 100)));
        assertEquals(1, map.getMovables().size());
    }

    @Test
    void movableShapeShouldBeMovedIfThereIsNoCollision() {
        map.add(brickFactory.get(SimpleBrick, new Position(10, 10)), new Position(10, 10));
        assertEquals(1, map.getMovables().size());
        assertTrue(map.move(new Position(10, 10), new Position(100, 100)));
        assertEquals(1, map.getMovables().size());
    }

    @Test
    void movableShapeShouldNotBeMovedIfThereIsCollision() {
        map.add(brickFactory.get(SimpleBrick, new Position(10, 10)), new Position(10, 10));
        map.add(brickFactory.get(SimpleBrick, new Position(100, 100)), new Position(100, 100));
        assertEquals(2, map.getMovables().size());
        assertFalse(map.move(new Position(10, 10), new Position(100, 100)));
        assertEquals(2, map.getMovables().size());
    }
    @Test
    void testgetMovables(){
        List<MovableShape> list = new ArrayList<>();
        for(int i=0 ; i<3 ; ++i){
            Position pos = new Position(50 + 50 * i,50 + 50 * i);
            map.add(brickFactory.get(SimpleBrick, pos),pos);
            list.add(brickFactory.get(SimpleBrick,pos));
        }
        List<MovableShape> testedList = map.getData().getMovables();
        for(int i=0 ; i<3 ; ++i){
            assertTrue(list.get(i).equals(testedList.get(i)));
        }
    }

    @Test
    void testGetData() {
        map.add(brickFactory.get(SimpleBrick, new Position(10, 10)), new Position(10, 10));
        map.add(brickFactory.get(SimpleBrick, new Position(100, 100)), new Position(100, 100));
        List<MovableShape> movables = map.getMovables();
        List<MovableShape> testdata = map.getData().getMovables();
        for(int i=0 ; i<movables.size() ; ++i){
            assertTrue(movables.get(i).equals(testdata.get(i)));
        }
    }

}
