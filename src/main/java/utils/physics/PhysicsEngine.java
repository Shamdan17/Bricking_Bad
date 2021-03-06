package utils.physics;

import domain.model.Type;
import domain.model.shape.MovableShape;
import org.apache.log4j.Logger;
import utils.Position;
import utils.Velocity;
import utils.physics.math.Rotation;
import utils.physics.math.Slope;
import utils.physics.math.Vector;
import utils.physics.math.util;


// Overview: PhysicsEngine is responsible for handling the physics of collisions between objects.
// it has methods for calculating post collision velocities, slopes, and whether objects are collided
// or not.
public final class PhysicsEngine {

    final static Logger logger = Logger.getLogger(PhysicsEngine.class);

    private static PhysicsEngine _instance = new PhysicsEngine();

    public static PhysicsEngine getInstance() {
        return _instance;
    }

    /**
     * REQUIRES: objects in collision
     * EFFECTS: Returns new velocity of obj1 after collision with obj2, treating obj2 as a static object
     * Calculates the new Velocity of the first object if it is in collision with the second, returns the
     * same velocity if it not in collision. Assumes the other object is static
     *
     * @param obj1 The movable shape of which the new velocity will be calculated
     * @param obj2 The movable shape that obj1 collided with
     * @return The new velocity of obj1 after it collides with obj2 if it does so
     */
    public Velocity calculateNewVelocity(MovableShape obj1, MovableShape obj2) {

        if (!isCollided(obj1, obj2)) {
            return obj1.getVelocity();
        }

        // Handle possible rotated paddle collision logic
        if (obj2.getType() == Type.Paddle) {
            return calculateObjectWithPaddleVelocity(obj2, obj1);
        }

        Slope collisionWallSlope = calculateCollisionSlope(obj1, obj2);

        // Encountered unsupported object type
        if (collisionWallSlope == null) {
            return null;
        }

        return calculatePostCollisionVelocity(obj1.getVelocity(), collisionWallSlope);
    }

    /**
     * REQUIRES: objects in collision
     * EFFECTS: Returns new velocity of obj1 after collision with obj2, treating obj2 as a dynamic object
     * Calculates the new Velocity of the first object if it is in collision with the second, returns the
     * same velocity if it not in collision. Looks at the velocity of the first object relative to the other object
     *
     * @param obj1 The movable shape of which the new velocity will be calculated
     * @param obj2 The movable shape that obj1 collided with
     * @return The new velocity of obj1 after it collides with obj2 if it does so
     */
    public Velocity calculateNewRelativeVelocity(MovableShape obj1, MovableShape obj2) {

        if (!isCollided(obj1, obj2)) {
            return obj1.getVelocity();
        }

        // Handle possible rotated paddle collision logic
        //TODO: Fix paddle collisions
        if (obj2.getType() == Type.Paddle) {
            return calculateObjectWithPaddleVelocity(obj2, obj1);
        }

        Slope collisionWallSlope = calculateCollisionSlope(obj1, obj2);

        // Encountered unsupported object type
        if (collisionWallSlope == null) {
            return null;
        }

        Velocity firstInitialVelocity = obj1.getVelocity();
        Velocity secondInitialVelocity = obj2.getVelocity();
        Velocity relativeVelocity = new Velocity(firstInitialVelocity.getX() - secondInitialVelocity.getX(),
                firstInitialVelocity.getY() - secondInitialVelocity.getY());

        return calculatePostCollisionVelocity(relativeVelocity, collisionWallSlope);
    }

    private Velocity calculateObjectWithPaddleVelocity(MovableShape obj1, MovableShape obj2) {
        Position oldPos = obj2.getPosition();
        logger.debug("Old Position: " + oldPos);
        // Rotate the other object
        Position newPos = Rotation.rotate(obj1.getPosition(), oldPos, obj1.getAngle());
        logger.debug("New Position: " + newPos);
        // Set the updated (rotated position)
        obj2.setPosition(newPos);
        // Perform the calculations

        Slope collisionWallSlope = calculateCollisionSlope(obj1, obj2);

        // Encountered unsupported object type
        if (collisionWallSlope == null) {
            return null;
        }

        Velocity oldVelocity = obj2.getVelocity();
        oldVelocity = Rotation.rotate(oldVelocity, obj1.getAngle());
        Velocity newVelocity = calculatePostCollisionVelocity(oldVelocity, collisionWallSlope);
        newVelocity = Rotation.rotate(newVelocity, -obj1.getAngle());

        // Return the position back to normal
        obj2.setPosition(oldPos);

        return newVelocity;
    }

    /**
     * EFFECTS: Calculates the slope (normal of the line) of the collision wall if it is in collision with the second
     * REQUIRES: Two objects that are collided, the method does not perform this check
     * If you want to check whether two objects are collided, use utils/physics/PhysicsEngine.java:134
     *
     * @param obj1 The first movable object
     * @param obj2 The second movable object
     * @return The slope of the collision wall between the two objects
     */
    public Slope calculateCollisionSlope(MovableShape obj1, MovableShape obj2) {
        switch (obj1.getShape()) {
            case Circle:
                return calculateCircleCollisionSlope(obj1, obj2);
            case Rectangle:
                return calculateRectCollisionSlope(obj1, obj2);
        }
        return null;
    }

    private Slope calculateCircleCollisionSlope(MovableShape obj1, MovableShape obj2) {
        switch (obj2.getShape()) {
            case Circle:
                return calculateCircleOnCircleCollisionSlope(obj1, obj2);
            case Rectangle:
                return calculateCircleOnRectCollisionSlope(obj1, obj2);
        }
        return null;
    }

    private Slope calculateRectCollisionSlope(MovableShape obj1, MovableShape obj2) {
        switch (obj2.getShape()) {
            case Circle:
                return calculateRectOnCircleCollisionSlope(obj1, obj2);
            case Rectangle:
                return calculateRectOnRectCollisionSlope(obj1, obj2);
        }
        return null;
    }

    private Slope calculateCircleOnCircleCollisionSlope(MovableShape obj1, MovableShape obj2) {
        Position center1 = obj1.getCenter();
        Position center2 = obj2.getCenter();

        // Calculate the normal of the collision wall
        Slope m = new Slope(center1, center2);
        System.out.println(m.getSlope());
        return m;//calculatePostCollisionVelocity(obj1.getVelocity(),m);
    }

    private Slope calculateCircleOnRectCollisionSlope(MovableShape obj1, MovableShape obj2) {
        Position circleCenter = obj1.getCenter();
        double cx = circleCenter.getX(), cy = circleCenter.getY();

        Position rectPos = obj2.getPosition();

        // Rectangle parameters
        int length, width;
        length = obj2.getLength();
        width = obj2.getWidth();


        // Normal vector parameters
        int dy = 0, dx = 0;

        //Identifying the normal vector
        if (cx < rectPos.getX()) {
            dy = -1;
        } else if (cx > rectPos.getX() + length) {
            dy = 1;
        }

        if (cy < rectPos.getY()) {
            dx = -1;
        } else if (cy > rectPos.getY() + width) {
            dx = 1;
        }

        return new Slope(dx, dy);//calculatePostCollisionVelocity(obj1.getVelocity(), m);
    }

    // TODO: implement this
    private Slope calculateRectOnRectCollisionSlope(MovableShape obj1, MovableShape obj2) {
        return new Slope(0, 0);
    }

    private Slope calculateRectOnCircleCollisionSlope(MovableShape obj1, MovableShape obj2) {
        // switch arguments and proceed
        return calculateCircleOnRectCollisionSlope(obj2, obj1);
    }

    /**
     * Requires: valid non-null movable shapes
     * Effects: Returns true if the two MovableShapes are in collision
     *
     * @param obj1
     * @param obj2
     * @return True if obj1 is in collision with obj2, False otherwise
     */
    public boolean isCollided(MovableShape obj1, MovableShape obj2) {
        // TODO: find a way to improve this checking
        if (obj1.getType() == Type.Paddle) {
            Position oldPos = obj2.getPosition();
            // Rotate the other object
            obj1.getPosition();
            Position newPos = Rotation.rotate(obj1.getPosition(), oldPos, obj1.getAngle());
            // Set the updated (rotated position)
            obj2.setPosition(newPos);
            // Perform the check
            boolean result = isRectCollided(obj1, obj2);
            // Return the position back to normal
            obj2.setPosition(oldPos);
            // The result
            return result;
        } else if (obj2.getType() == Type.Paddle) {
            return isCollided(obj2, obj1);
        }
        // End of block to fix

        switch (obj1.getShape()) {
            case Rectangle:
                return isRectCollided(obj1, obj2);
            case Circle:
                return isCircleCollided(obj1, obj2);
        }
        return false;
    }

    private boolean isRectCollided(MovableShape obj1, MovableShape obj2) {
        switch (obj2.getShape()) {
            case Rectangle:
                return isRectCollidedWithRect(obj1, obj2);
            case Circle:
                return isRectCollidedWithCircle(obj1, obj2);
        }
        return false;
    }

    private boolean isCircleCollided(MovableShape obj1, MovableShape obj2) {
        switch (obj2.getShape()) {
            case Rectangle:
                return isRectCollidedWithCircle(obj2, obj1);
            case Circle:
                return isCircleCollidedWithCircle(obj1, obj2);
        }
        return false;
    }

    private boolean isRectCollidedWithRect(MovableShape obj1, MovableShape obj2) {
        Position pos1 = obj1.getPosition();
        Position pos2 = obj2.getPosition();


        double x1, x2;
        double y1, y2;

        x1 = pos1.getX();
        y1 = pos1.getY();

        x2 = pos2.getX();
        y2 = pos2.getY();

        double len1, len2;
        double wid1, wid2;

        len1 = obj1.getLength();
        len2 = obj2.getLength();

        wid1 = obj1.getWidth();
        wid2 = obj2.getWidth();

        return (x2 + len2 > x1 && x1 + len1 > x2) &&
                (y2 + wid2 > y1 && y1 + wid1 > y2);
    }

    private boolean isRectCollidedWithCircle(MovableShape obj1, MovableShape obj2) {
        Position rect = obj1.getPosition();
        int len, wid;
        len = obj1.getLength();
        wid = obj1.getWidth();

        Position cnt = obj2.getCenter();
        int radius = getRadius(obj2);

        // if the circle center is between the X bounds of the rect
        // This means the circle is either above or below the rectangle
        if (cnt.getX() >= rect.getX() && cnt.getX() <= rect.getX() + len) {
            return (rect.getY() <= cnt.getY() + radius) && (rect.getY() + wid >= cnt.getY() - radius);
        }

        // if the circle center is between the Y bounds of the rect
        // This means the circle is on either side of the rectangle
        if (cnt.getY() >= rect.getY() && cnt.getY() <= rect.getY() + wid) {
            return (rect.getX() <= cnt.getX() + radius) && (rect.getX() + len >= cnt.getX() - radius);
        }

        // Otherwise, check if the distance between the circle and the corners of the rectangle is less than r
        boolean result =
                util.getDistance(cnt, rect) <= radius //top left
                        || util.getDistance(cnt, rect.incrementX(len)) <= radius //top right
                        || util.getDistance(cnt, rect.incrementY(wid)) <= radius //bottom left
                        || util.getDistance(cnt, rect.incrementX(len).incrementY(wid)) <= radius; //bottom right

        return result;

    }

    private boolean isCircleCollidedWithCircle(MovableShape obj1, MovableShape obj2) {
        Position cnt1 = obj1.getCenter();
        Position cnt2 = obj2.getCenter();

        int radius1 = getRadius(obj1);
        int radius2 = getRadius(obj2);

        return util.getDistance(cnt1, cnt2) < (radius1 + radius2);
    }


    public enum direction {
        Left,
        Right,
        Down,
        Up,
    }

    //Collision direction functions

    /**
     * Requires: valid non-null movable shapes
     * Effects: returns the relative Y direction of obj1 with respect to obj2
     * if function returns Down, then that means that obj1 is under obj2
     * objects are compared relative to their centers
     *
     * @param obj1
     * @param obj2
     * @return Up if the center of obj1 is above the center of obj2, Down otherwise
     */
    public direction relativeYDirection(MovableShape obj1, MovableShape obj2) {
        Position c1 = obj1.getCenter();
        Position c2 = obj2.getCenter();
        Vector v = new Vector(c1, c2);
        if (v.pointsUp()) {
            return direction.Up;
        } else {
            return direction.Down;
        }
    }

    /**
     * Requires: valid non-null movable shapes
     * Effects: returns the relative X direction of obj1 with respect to obj2
     * if function returns Left, then that means that obj1 is to the left of (x1 is less than x2) of obj2
     * objects are compared relative to their centers
     *
     * @param obj1
     * @param obj2
     * @return Left if the center of obj1 is to the left of the center of obj2, Right otherwise
     */
    public direction relativeXDirection(MovableShape obj1, MovableShape obj2) {
        Position c1 = obj1.getCenter();
        Position c2 = obj2.getCenter();
        Vector v = new Vector(c1, c2);
        if (v.pointsLeft()) {
            return direction.Left;
        } else {
            return direction.Right;
        }
    }


    // Since circles have their length and width as their diameters, this returns the radius
    private int getRadius(MovableShape obj) {
        return obj.getLength() / 2;
    }

    // Takes the old velocity and the angle of the normal of the wall that the object collided
    // into and calculates the new velocity after the collision
    // Uses the formula: (x-x1)/m = (y-y1) = -2(m*x1 + y1) / (1 + m^2)
    protected Velocity calculatePostCollisionVelocity(Velocity oldVelocity, Slope normalSlope) {
        if (normalSlope.isVertical()) {
            //Simply invert the y velocity
            return new Velocity(-oldVelocity.getX(), oldVelocity.getY());
        }
        double oldX = oldVelocity.getX();
        double oldY = oldVelocity.getY();
        double m = normalSlope.getSlope();
        double tmp = (-2 * (oldY + m * oldX)) / (1 + m * m);
        double newX = (m) * tmp + oldX;
        double newY = tmp + oldY;
        return new Velocity(newX, newY);
    }


}

