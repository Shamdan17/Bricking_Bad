package utils.physics.math;

import utils.Position;
import utils.Velocity;

public final class util {
    private final static double eps = 0.000005;

    public static int round(double a) {
        return (int) Math.round(a);
    }

    public static boolean equal(double a, double b) {
        return Math.abs(a - b) < eps;
    }

    // returns the distance between two points
    public static double getDistance(Position pt1, Position pt2) {
        return Math.sqrt(Math.pow(pt2.getX() - pt1.getX(), 2) + Math.pow(pt2.getY() - pt1.getY(), 2));
    }

    // returns the difference between the speeds
    public static double getDifference(Velocity v1, Velocity v2) {
        double speed1 = v1.getSpeed();
        double speed2 = v2.getSpeed();
        return Math.abs(speed1 - speed2);
    }
}
