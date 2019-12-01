package utils.physics.math;

public final class util {
    private final static double eps = 0.000005;

    private util() {
    }

    public static int round(double a) {
        return (int) Math.round(a);
    }

    public static boolean equal(double a, double b){
        return Math.abs(a-b) < eps;
    }
}
