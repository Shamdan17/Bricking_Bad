package utils.physics;

import utils.Velocity;
import utils.physics.math.Slope;

import static org.junit.jupiter.api.Assertions.*;

class PhysicsEngineTest {

    @org.junit.jupiter.api.Test
    void testCalculateNewVelocity() {
        // Vertical wall
        assertEquals(new Velocity(10,-10), PhysicsEngine.calculatePostCollisionVelocity (new Velocity(10,10), new Slope()));
        // Head on collisions
        assertEquals(new Velocity(-1,3), PhysicsEngine.calculatePostCollisionVelocity (new Velocity(-3,1), new Slope(1,1)));
        assertEquals(new Velocity(-10,-10), PhysicsEngine.calculatePostCollisionVelocity (new Velocity(10,10), new Slope(1,1)));
        // Horizontal wall
        // Todo: Double check tests
        //assertEquals(new Velocity(-10,-10), PhysicsEngine.calculatePostCollisionVelocity (new Velocity(10,10), new Slope(1,1)));
        //assertEquals(new Velocity(-10,-10), PhysicsEngine.calculatePostCollisionVelocity (new Velocity(10,10), new Slope(1,0)));
    }
}