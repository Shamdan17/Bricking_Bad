package utils.physics.math;

import utils.Position;
import utils.Velocity;

// This package provides static methods that can be used to rotate and transform points (rotate relative to origin, etc..)
public class Rotation {
    // angle: from the x-axis
    public static Position rotate(Position origin, Position point, double angle) {
        PolarPoint ppt = new PolarPoint(origin, point);
        ppt.rotate(angle);
        return ppt.getPosition();
    }

    public static Velocity rotate(Velocity vc, double angle) {
        PolarPoint ppt = new PolarPoint(vc);
        ppt.rotate(angle);
        return ppt.getVelocity();
    }
}