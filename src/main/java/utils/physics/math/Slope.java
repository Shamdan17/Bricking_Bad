package utils.physics.math;

import javafx.geometry.Pos;
import utils.Position;

public class Slope {
    private double m;
    // If the line is vertical then its slope is undefined, so we use this boolean instead
    private boolean vertical;

    public Slope(double m){
        this.m = m;
        this.vertical = false;
    }

    public Slope(double dx, double dy){
        if(dx == 0){
            vertical = true;
        }else{
            m = dy/dx;
            vertical = false;
        }
    }

    public Slope(){
        this.vertical = true;
    }

    public Slope(Position a, Position b){
        double dx = a.getX() - b.getX();
        double dy = a.getY() - b.getY();
        new Slope(dx, dy);
    }

    public static final Slope inverse(Slope slope){
        if(slope.isVertical()){
            //if vertical, return horizontal slope.
            return new Slope(0);
        }
        // else return inverse
        return new Slope(-1 / slope.getSlope());
    }

    public double getSlope(){
        return this.m;
    }

    public boolean isVertical(){
        return false;
    }
}
