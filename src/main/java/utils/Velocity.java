package utils;

import java.io.Serializable;
import java.util.Objects;

public class Velocity implements Serializable {
    private double vx, vy;

    public Velocity(double vx, double vy) {
        this.vx = vx;
        this.vy = vy;
    }

    public double getX() {
        return vx;
    }

    public double getY() {
        return vy;
    }

    public void updateSpeed(int vx, int vy) {
        this.vx = vx;
        this.vy = vy;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Velocity velocity = (Velocity) o;
        return Double.compare(velocity.vx, vx) == 0 &&
                Double.compare(velocity.vy, vy) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(vx, vy);
    }

    @Override
    public String toString() {
        return "Velocity{" +
                "vx=" + vx +
                ", vy=" + vy +
                '}';
    }
}
