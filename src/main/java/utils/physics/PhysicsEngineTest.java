package utils.physics;

import org.junit.jupiter.api.Test;
import utils.Velocity;
import utils.physics.math.Slope;

import static org.junit.jupiter.api.Assertions.*;

class PhysicsEngineTest {

    @Test
    void testCalculateNewVelocity() {
        // Vertical wall
        assertEquals(new Velocity(10,-10), PhysicsEngine.calculatePostCollisionVelocity (new Velocity(10,10), new Slope()));
        // Slanted wall collisions
        // Wall: (y = -x)
        assertEquals(new Velocity(-1,3), PhysicsEngine.calculatePostCollisionVelocity (new Velocity(-3,1), new Slope(1,1)));
        assertEquals(new Velocity(-10,-10), PhysicsEngine.calculatePostCollisionVelocity (new Velocity(10,10), new Slope(1,1)));
        // Wall: (y = x)
        assertEquals(new Velocity(5,0), PhysicsEngine.calculatePostCollisionVelocity (new Velocity(0,5), new Slope(-1,1)));
        assertEquals(new Velocity(4,2), PhysicsEngine.calculatePostCollisionVelocity (new Velocity(2,4), new Slope(-1,1)));
        // Horizontal wall
        assertEquals(new Velocity(10,-10), PhysicsEngine.calculatePostCollisionVelocity (new Velocity(10,10), new Slope(0,1)));
        assertEquals(new Velocity(10,10), PhysicsEngine.calculatePostCollisionVelocity (new Velocity(10,-10), new Slope(0,1)));
    }


}