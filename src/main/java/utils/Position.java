package utils;

import utils.physics.math.util;

import java.io.Serializable;

public class Position implements Serializable {

    private double x;
    private double y;

    public Position(double x, double y) {
        // TODO: check for bounds of x and y
        this.x = x;
        this.y = y;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public Position incrementX(double dx) {
        return new Position(x + dx, y);
    }

    public Position incrementY(double dy) {
        return new Position(x, y + dy);
    }

    @Override
    public String toString() {
        return "Position{" + "x=" + x + ", y=" + y + '}';
    }

    public boolean equals(Position pos) {
        return util.equal(x, pos.getX()) && util.equal(y, pos.getY());
    }


    public boolean repOK() {
        if (this.x < 0 || this.y < 0) {
            return false;
        }

        return true;
    }
}
