package utils.physics;

import domain.model.Ball;
import domain.model.Paddle;
import domain.model.brick.Brick;
import domain.model.brick.SimpleBrick;
import domain.model.shape.Circle;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import utils.Position;
import utils.Velocity;
import utils.physics.math.Slope;

import static org.junit.jupiter.api.Assertions.*;

class PhysicsEngineTest {

    private static PhysicsEngine physicsEngine;

    @BeforeAll
    static void setUp() {
        physicsEngine = PhysicsEngine.getInstance();
    }

//    @Test
//    void testCalculateNewVelocity(){
//        //Paddle with ball
//        Position p1 = new Position(0,-8);
//        Circle b1 = new Ball(p1,5);
//        Position p2 = new Position(0,0);
//        Paddle pd = new Paddle(p2);
//        pd.setLength(100);
//        Rectangle brck = new SimpleBrick(p2,100,5);
//        Velocity v1 = new Velocity(0,-10);
//        b1.setVelocity(v1);
//
//        //physicsEngine.isCollided(b1,pd);
//        pd.setAngle(45);
//        physicsEngine.calculateNewVelocity(b1, pd);
//        //logger.debug("Current angle: " + i);
//        logger.debug("new velocity: "+ physicsEngine.calculateNewVelocity(b1, pd) + physicsEngine.isCollided(b1,pd));
//
//        //logger.debug("new velocity: "+ physicsEngine.calculateNewVelocity(b1, pd) + physicsEngine.isCollided(b1,pd) + physicsEngine.isCollided(b1,brck));
//    }

    @Test
    void testCalculatePostCollisionVelocity() {
        // Horizontal wall
        assertEquals(new Velocity(-10, 10), physicsEngine.calculatePostCollisionVelocity(new Velocity(10, 10), new Slope()));
        // Slanted wall collisions
        // Wall: (y = -x)
        assertEquals(new Velocity(-1, 3), physicsEngine.calculatePostCollisionVelocity(new Velocity(-3, 1), new Slope(1, 1)));
        assertEquals(new Velocity(-10, -10), physicsEngine.calculatePostCollisionVelocity(new Velocity(10, 10), new Slope(1, 1)));
        // Wall: (y = x)
        assertEquals(new Velocity(5, 0), physicsEngine.calculatePostCollisionVelocity(new Velocity(0, 5), new Slope(-1, 1)));
        assertEquals(new Velocity(4, 2), physicsEngine.calculatePostCollisionVelocity(new Velocity(2, 4), new Slope(-1, 1)));
        // Vertical wall
        assertEquals(new Velocity(10, -10), physicsEngine.calculatePostCollisionVelocity(new Velocity(10, 10), new Slope(1, 0)));
        assertEquals(new Velocity(10, 10), physicsEngine.calculatePostCollisionVelocity(new Velocity(10, -10), new Slope(1, 0)));
    }

    @Test
    void testIsCollided() {
        // Ball with ball
        // Balls that are collided
        Position p1 = new Position(10, 10);
        Circle b1 = new Ball(p1, 5);
        Position p2 = new Position(15, 15);
        Circle b2 = new Ball(p2, 5);
        assertTrue(physicsEngine.isCollided(b1, b2));
        // Balls that are not collided
        p1 = new Position(10, 10);
        b1 = new Ball(p1, 5);
        p2 = new Position(20, 20);
        b2 = new Ball(p2, 5);
        assertFalse(physicsEngine.isCollided(b1, b2));

        // Ball with rectangle
        // Collided
        // Test 1
        p1 = new Position(10, 10);
        Brick r1 = new SimpleBrick(p1, 5, 5);
        p2 = new Position(10, 10);
        b1 = new Ball(p2, 10);
        assertTrue(physicsEngine.isCollided(b1, r1));
        assertTrue(physicsEngine.isCollided(r1, b1));
        // Test 2
        p1 = new Position(5, 5);
        r1 = new SimpleBrick(p1, 8, 8);
        p2 = new Position(10, 10);
        b1 = new Ball(p2, 10);
        assertTrue(physicsEngine.isCollided(b1, r1));
        assertTrue(physicsEngine.isCollided(r1, b1));
        // Not Collided
        // Test 1
        p1 = new Position(10, 10);
        r1 = new SimpleBrick(p1, 5, 5);
        p2 = new Position(15, 15);
        b1 = new Ball(p2, 100);
        assertFalse(physicsEngine.isCollided(b1, r1));
        assertFalse(physicsEngine.isCollided(r1, b1));
        // Test 2
        p1 = new Position(10, 10);
        r1 = new SimpleBrick(p1, 5, 5);
        p2 = new Position(0, 0);
        b1 = new Ball(p2, 5);
        assertFalse(physicsEngine.isCollided(b1, r1));
        assertFalse(physicsEngine.isCollided(r1, b1));

        // Rectangle with Rectangle
        // Collided
        p1 = new Position(10, 10);
        r1 = new SimpleBrick(p1, 5, 5);
        p2 = new Position(14, 14);
        Brick r2 = new SimpleBrick(p2, 5, 5);
        assertTrue(physicsEngine.isCollided(r1, r2));
        assertTrue(physicsEngine.isCollided(r2, r1));

        // Not collided
        p1 = new Position(10, 10);
        r1 = new SimpleBrick(p1, 5, 5);
        p2 = new Position(16, 16);
        r2 = new SimpleBrick(p2, 5, 5);
        assertFalse(physicsEngine.isCollided(r1, r2));
        assertFalse(physicsEngine.isCollided(r2, r1));

        // Objects should always be in collision with themselves
        assertTrue(physicsEngine.isCollided(r1, r1));
        assertTrue(physicsEngine.isCollided(r2, r2));
        assertTrue(physicsEngine.isCollided(b1, b1));
        assertTrue(physicsEngine.isCollided(b2, b2));
    }

    @Test
    void testIsCollidedWithRotation() {
        //Paddle with ball
        Position p1 = new Position(10, 0);
        Circle b1 = new Ball(p1, 5);
        Position p2 = new Position(0, 0);
        Paddle pd = new Paddle(p2);
        pd.setLength(20);

        for (int i = -45; i <= 45; i++) {
            pd.setAngle(i);
        }
    }


}
