package utils.physics;

import domain.Movable;
import domain.model.shape.Circle;
import domain.model.shape.Rectangle;
import utils.Position;
import utils.Velocity;
import utils.physics.math.Slope;

public final class PhysicsEngine {
    //Calculates the new velocity of the first object if it is in collision with the second
    public static Velocity calculateNewVelocity(Movable obj1, Movable obj2){
        switch (obj1.getShape()){
            case Circle:
                return calculateCircleVelocity(obj1, obj2);
            case Rectangle:
                return calculateRectVelocity(obj1, obj2);
        }
        return null;
    }

    private static Velocity calculateCircleVelocity(Movable obj1, Movable obj2){
        switch(obj2.getShape()){
            case Circle:
                return calculateCircleOnCircleVelocity(obj1, obj2);
            case Rectangle:
                return calculateCircleOnRectVelocity(obj1, obj2);
        }
        return null;
    }

    private static Velocity calculateRectVelocity(Movable obj1, Movable obj2){
        switch(obj2.getShape()){
            case Circle:
                return calculateRectOnCircleVelocity(obj1, obj2);
            case Rectangle:
                return calculateRectOnRectVelocity(obj1, obj2);
        }
        return null;
    }

    private static Velocity calculateCircleOnCircleVelocity(Movable obj1, Movable obj2){
        Position center1 = getCircleCenter(obj1);
        Position center2 = getCircleCenter(obj2);

        // Calculate the normal of the collision wall
        Slope m = new Slope(center1, center2);

        return calculatePostCollisionVelocity(obj1.getVelocity(),m);
    }

    private static Velocity calculateCircleOnRectVelocity(Movable obj1, Movable obj2){
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

        Slope m = new Slope(dx, dy);
        return calculatePostCollisionVelocity(obj1.getVelocity(), m);
    }

    // TODO: implement this
    private static Velocity calculateRectOnRectVelocity(Movable obj1, Movable obj2){
        return new Velocity(0,0);
    }

    // TODO: implement this
    private static Velocity calculateRectOnCircleVelocity(Movable obj1, Movable obj2){
        return new Velocity(0,0);
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
                return isRectCollidedWithCircle(obj1, obj2);
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

    // TODO: develop this
    private static boolean isRectCollidedWithCircle(Movable obj1, Movable obj2){
        return false;
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
    // Uses the formula: -(x-x1)/m = (y-y1) = -2(-m*x1 + y1) / (1 + m^2)
    protected static Velocity calculatePostCollisionVelocity(Velocity oldVelocity, Slope normalSlope){
        if(normalSlope.isVertical()){
            //Simply invert the y velocity
            return new Velocity(oldVelocity.getX(), -oldVelocity.getY());
        }
        double oldX = oldVelocity.getX();
        double oldY = oldVelocity.getY();
        double m = normalSlope.getSlope();
        double tmp = -2*(oldY - m * oldX)/(1 + m * m);
        double newX = (-m) * tmp + oldX;
        double newY = tmp + oldY;
        return new Velocity(newX, newY);
    }


}

