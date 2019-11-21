package utils.physics.math;


import utils.Position;
import utils.Velocity;

// A PolarPoint is a coordinate on a polar coordinate plane
public class PolarPoint {
    double radius;
    double angle;
    Position origin;

    public PolarPoint(double radius, double angle) {
        this.radius = radius;
        this.angle = angle;
        this.origin = new Position(0,0);
    }

    public PolarPoint(Position origin, Position point){
        double newY = point.getY() - origin.getY();
        double newX = point.getX() - origin.getX();
        this.radius = Math.sqrt(newY*newY + newX*newX);
        if(newX!=0){
            this.angle = Math.toDegrees(Math.atan(newY/newX));
            if(newX < 0){
                this.angle += 180;
            }
        }else{
            if(newY>0){
                this.angle = 90;
            }else{
                this.angle = -90;
            }
        }
        this.origin = origin;
    }

    public void rotate(double dif){
        this.angle += dif;
    }

    public Position getPosition(){
        double x = origin.getX();
        double y = origin.getY();
        y += radius * Math.sin(Math.toRadians(angle));
        x += radius * Math.cos(Math.toRadians(angle));
        return new Position((int) Math.round(x),(int) Math.round(y));
    }

    public Velocity getVelocity(){
        double x = radius * Math.sin(Math.toRadians(angle));
        double y = radius * Math.cos(Math.toRadians(angle));
        return new Velocity((int) Math.round(x),(int) Math.round(y));
    }
}
