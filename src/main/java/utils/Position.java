package utils;

import java.io.Serializable;

public class Position implements Serializable {

    private double x;
    private double y;

    public Position(double x, double y) {
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
        int difx = (int) Math.round(dx);
        return new Position(x + difx, y);
    }

    public Position incrementY(double dy) {
        int dify = (int) Math.round(dy);
        return new Position(x, y + dify);
    }

    @Override
    public String toString() {
        return "Position{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }

    public boolean equals(Position pos) {
        return x == pos.getX() && y == pos.getY();
    }
}
