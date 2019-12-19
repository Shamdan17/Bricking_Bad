package utils.physics.math;

import utils.Position;

public final class util {
    private final static double eps = 0.000005;

    public static int round(double a) {
        return (int) Math.round(a);
    }

    public static boolean equal(double a, double b) {
        return Math.abs(a - b) < eps;
    }

    public static double getDistance(Position pt1, Position pt2) {
        return Math.sqrt(Math.pow(pt2.getX() - pt1.getX(), 2) + Math.pow(pt2.getY() - pt1.getY(), 2));
    }
}
