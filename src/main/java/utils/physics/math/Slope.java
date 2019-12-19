package utils.physics.math;

import utils.Position;

public class Slope {
    private double m;
    // If the line is vertical then its slope is undefined, so we use this boolean instead
    private boolean vertical;

    public Slope(double m) {
        this.m = m;
        this.vertical = false;
    }

    public Slope(double dx, double dy) {
        if (dx == 0) {
            vertical = true;
        } else {
            m = dy / dx;
            vertical = false;
        }
    }

    public Slope() {
        this.vertical = true;
    }

    public Slope(Position a, Position b) {
        double dx = a.getX() - b.getX();
        double dy = a.getY() - b.getY();
        if (dx == 0) {
            vertical = true;
        } else {
            m = dy / dx;
            vertical = false;
        }
    }

    public static final Slope inverse(Slope slope) {
        if (slope.isVertical()) {
            //if vertical, return horizontal slope.
            return new Slope(0);
        }
        // else return inverse
        return new Slope(-1 / slope.getSlope());
    }

    public double getSlope() {
        return this.m;
    }

    public boolean isVertical() {
        return vertical;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Slope slope = (Slope) o;
        return util.equal(slope.m, m) &&
                vertical == slope.vertical;
    }

    @Override
    public String toString() {
        return "Slope{" +
                "m=" + m +
                ", vertical=" + vertical +
                '}';
    }
}
