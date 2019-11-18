package utils.physics;

import domain.Movable;
import domain.model.shape.Circle;
import domain.model.shape.Rectangle;
import utils.Position;
import utils.Velocity;
import utils.physics.math.Slope;

public final class PhysicsEngine {

    private static PhysicsEngine _instance = new PhysicsEngine();

    public static PhysicsEngine getInstance(){return _instance;}

    // Calculates the new Velocity of the first object if it is in collision with the second, returns the
    // same velocity if it not in collision
    public static Velocity calculateNewVelocity(Movable obj1, Movable obj2){
        if(!isCollided(obj1, obj2)){
            return obj1.getVelocity();
        }
        Slope collisionWallSlope = calculateCollisionSlope(obj1, obj2);

        // Encountered unsupported object type
        if(collisionWallSlope == null){
            return null;
        }

        return calculatePostCollisionVelocity(obj1.getVelocity(), collisionWallSlope);
    }

    // Calculates the slope (normal of the line) of the collision wall if it is in collision with the second
    // Required: Two objects that are collided
    public static Slope calculateCollisionSlope(Movable obj1, Movable obj2){
        switch (obj1.getShape()){
            case Circle:
                return calculateCircleCollisionSlope(obj1, obj2);
            case Rectangle:
                return calculateRectVelocity(obj1, obj2);
        }
        return null;
    }

    private static Slope calculateCircleCollisionSlope(Movable obj1, Movable obj2){
        switch(obj2.getShape()){
            case Circle:
                return calculateCircleOnCircleCollisionSlope(obj1, obj2);
            case Rectangle:
                return calculateCircleOnRectCollisionSlope(obj1, obj2);
        }
        return null;
    }

    private static Slope calculateRectVelocity(Movable obj1, Movable obj2){
        switch(obj2.getShape()){
            case Circle:
                return calculateRectOnCircleCollisionSlope(obj1, obj2);
            case Rectangle:
                return calculateRectOnRectCollisionSlope(obj1, obj2);
        }
        return null;
    }

    private static Slope calculateCircleOnCircleCollisionSlope(Movable obj1, Movable obj2){
        Position center1 = getCircleCenter(obj1);
        Position center2 = getCircleCenter(obj2);

        // Calculate the normal of the collision wall
        Slope m = new Slope(center1, center2);

        return m;//calculatePostCollisionVelocity(obj1.getVelocity(),m);
    }

    private static Slope calculateCircleOnRectCollisionSlope(Movable obj1, Movable obj2){
        Position circleCenter = getCircleCenter(obj1);
        int cx = circleCenter.getX(), cy = circleCenter.getY();

        Position rectPos = obj2.getPosition();

        // Rectangle parameters
        int length, width;
        Rectangle rect = (Rectangle)obj2;
        length = rect.getLength();
        width = rect.getWidth();


        // Normal vector parameters
        int dy = 0, dx = 0;

        //Identifying the normal vector
        if(cx < rectPos.getX()){
            dx = -1;
        }else if (cx > rectPos.getX()+length){
            dx = 1;
        }

        if(cy < rectPos.getY()){
            dy = -1;
        }else if (cy > rectPos.getY()+width){
            dy = 1;
        }

        return new Slope(dx, dy);//calculatePostCollisionVelocity(obj1.getVelocity(), m);
    }

    // TODO: implement this
    private static Slope calculateRectOnRectCollisionSlope(Movable obj1, Movable obj2){
        return new Slope(0,0);
    }

    // TODO: implement this
    private static Slope calculateRectOnCircleCollisionSlope(Movable obj1, Movable obj2){
        // switch arguments and proceed
        return calculateCircleOnRectCollisionSlope(obj2, obj1);
    }

    public static boolean isCollided(Movable obj1, Movable obj2){
        switch(obj1.getShape()){
            case Rectangle:
                return isRectCollided(obj1, obj2);
            case Circle:
                return isCircleCollided(obj1, obj2);
        }
        return false;
    }

    private static boolean isRectCollided(Movable obj1, Movable obj2){
        switch(obj2.getShape()){
            case Rectangle:
                return isRectCollidedWithRect(obj1, obj2);
            case Circle:
                return isRectCollidedWithCircle(obj1, obj2);
        }
        return false;
    }

    private static boolean isCircleCollided(Movable obj1, Movable obj2){
        switch(obj2.getShape()){
            case Rectangle:
                return isRectCollidedWithCircle(obj2, obj1);
            case Circle:
                return isCircleCollidedWithCircle(obj1, obj2);
        }
        return false;
    }

    private static boolean isRectCollidedWithRect(Movable obj1, Movable obj2){
        Position pos1 = obj1.getPosition();
        Position pos2 = obj2.getPosition();
        Rectangle rec1 = (Rectangle) obj1;
        Rectangle rec2 = (Rectangle) obj2;


        int x1, x2;
        int y1, y2;

        x1 = pos1.getX();
        y1 = pos1.getY();

        x2 = pos2.getX();
        y2 = pos2.getY();

        int len1, len2;
        int wid1, wid2;

        len1 = rec1.getLength();
        len2 = rec2.getLength();

        wid1 = rec1.getWidth();
        wid2 = rec2.getWidth();

        return  (x2 + len2 > x1 && x1 + len1 > x2) &&
                (y2 + wid2 > y1 && y1 + len1 > y2);
    }

    private static boolean isRectCollidedWithCircle(Movable obj1, Movable obj2){
        Position rect = obj1.getPosition();
        int len, wid;
        len = ((Rectangle) obj2).getLength();
        wid = ((Rectangle) obj2).getWidth();

        Position cnt = getCircleCenter(obj2);
        int radius = ((Circle) obj2).getRadius();

        // if the circle center is between the X bounds of the rect
        // This means the circle is either above or below the rectangle
        if(cnt.getX() >= rect.getX() && cnt.getX()<= rect.getX() + len){
            //check if the center differs from the y bounds by less than R
           return (Math.abs(rect.getY()-cnt.getY())<=radius) || (Math.abs(rect.getY()+wid-cnt.getY())<=radius);
        }

        // if the circle center is between the Y bounds of the rect
        // This means the circle is on either side of the rectangle
        if(cnt.getY() >= rect.getY() && cnt.getY() <= rect.getY()+wid){
            //check if the center differs from the x bounds by less than R
            return (Math.abs(rect.getX()-cnt.getX())<=radius) || (Math.abs(rect.getX()+len-cnt.getX())<= radius);
        }

        // Otherwise, check if the distance between the circle and the corners of the rectangle is less than r
        boolean result =
                  getDistance(cnt, rect) <= radius //top left
                ||getDistance(cnt, rect.incrementX(len))<=radius //top right
                ||getDistance(cnt, rect.incrementY(wid))<=radius //bottom left
                ||getDistance(cnt, rect.incrementX(len).incrementY(wid))<=radius; //bottom right

        return result;

    }

    private static boolean isCircleCollidedWithCircle(Movable obj1, Movable obj2){
        Position cnt1 = getCircleCenter(obj1);
        Position cnt2 = getCircleCenter(obj2);

        int radius1 = ((Circle) obj1).getRadius();
        int radius2 = ((Circle) obj2).getRadius();

        return getDistance(cnt1, cnt2) < (radius1 + radius2);
    }

    // Helper functions
    private static double getDistance(Position pt1, Position pt2){
        return Math.sqrt(Math.pow(pt2.getX()-pt1.getX(), 2) + Math.pow(pt2.getY()-pt1.getY(), 2));
    }


    private static Position getCircleCenter(Movable obj){
        // Cast the movable objects to circles to get access to their radii
        Circle circle = (Circle)obj;

        // Get the radius
        int radius = circle.getRadius();

        // Get the top left corner and add the offset (radius) to get the center
        Position center = obj.getPosition().incrementX(radius).incrementY(radius);

        return center;
    }

    // Takes the old velocity and the angle of the normal of the wall that the object collided
    // into and calculates the new velocity after the collision
    // Uses the formula: (x-x1)/m = (y-y1) = -2(m*x1 + y1) / (1 + m^2)
    protected static Velocity calculatePostCollisionVelocity(Velocity oldVelocity, Slope normalSlope){
        if(normalSlope.isVertical()){
            //Simply invert the y velocity
            return new Velocity(oldVelocity.getX(), -oldVelocity.getY());
        }
        double oldX = oldVelocity.getX();
        double oldY = oldVelocity.getY();
        double m = normalSlope.getSlope();
        double tmp = (-2*(oldY + m * oldX))/(1 + m * m);
        double newX = (m) * tmp + oldX;
        double newY = tmp + oldY;
        return new Velocity(newX, newY);
    }


}
