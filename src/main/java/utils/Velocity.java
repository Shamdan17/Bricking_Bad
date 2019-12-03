package utils;

import utils.physics.math.util;

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

    public boolean equals(Velocity o) {
        if (this == o) return true;
        if (o == null) return false;
        return util.equal(o.getX(),getX()) && util.equal(getY(),o.getY());
    }

    @Override
    public boolean equals(Object o) {
        if(o == null) return false;
        if(!(o instanceof Velocity)){
            return false;
        }
        return equals((Velocity) o);
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
